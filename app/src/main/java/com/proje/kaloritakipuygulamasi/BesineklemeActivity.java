package com.proje.kaloritakipuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.proje.kaloritakipuygulamasi.database.entities.OgunKayit;
import com.proje.kaloritakipuygulamasi.database.dao.OgunKayitDao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import com.proje.kaloritakipuygulamasi.util.TarihUtil;

import java.util.ArrayList;
import java.util.List;

public class BesineklemeActivity extends AppCompatActivity {

    int indis = -1;
    int cal;
    List<String> datalist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KaloriTakipDatabase ktdb = KaloriTakipDatabase.getKaloriTakipDatabase(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besinekleme);
        FirebaseApp.initializeApp(this);
        TextInputEditText tfieldBesinArama = (TextInputEditText) findViewById(R.id.inputBesinAdi);
        ListView yemeklistesi = (ListView) findViewById(R.id.yemeklistesi);
        Button ekleButonu = (Button) findViewById(R.id.eklemebuton);
        yemeklistesi.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference().child("ogunler");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BesineklemeActivity.this, android.R.layout.simple_list_item_1, datalist)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                return view;
            }
        };

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ogunsnapshot:snapshot.getChildren()){
                    String yemekismi = ogunsnapshot.child("ogunAd").getValue(String.class);
                    String yemekbirim = ogunsnapshot.child("ogunBirim").getValue(String.class);
                    if(yemekbirim.equals("Gram")){
                        yemekbirim = "100 Gram";
                    } else if (yemekbirim.equals("Tane")) {
                        yemekbirim = "1 Tane";

                    }
                    String kalori = Integer.toString(ogunsnapshot.child("ogunKalori").getValue(Integer.class));
                    datalist.add(yemekismi+"  |  "+yemekbirim+"  |  "+"Kalorisi: "+kalori);
                    Log.d("TAG", "Kullanıcı adı: " + yemekismi);
                }

                yemeklistesi.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        yemeklistesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (indis == position) {
                    // Zaten seçili olan öğe tıklandığında seçimini kaldır
                    indis = -1;
                    view.setBackgroundColor(Color.TRANSPARENT);
                }
                else
                {
                    // Yeni bir öğe seçildiğinde seçimi güncelle
                    if (indis != -1)
                    {
                        // Önceki seçimi kaldır
                        View previousSelectedView = yemeklistesi.getChildAt(indis);
                        if (previousSelectedView != null)
                        {
                            previousSelectedView.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                    indis = position;
                    Log.i("Deneme","Seçilen indis:"+indis);
                    view.setBackgroundColor(ContextCompat.getColor(BesineklemeActivity.this, R.color.selected_color));
                }

            }
        });

        EditText searchEditText = findViewById(R.id.inputBesinAdi);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        Button geribtn = (Button) findViewById(R.id.backbutton);

        geribtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button arabuton= (Button) findViewById(R.id.aramabuton);

        arabuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        adapter.getFilter().filter(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        ekleButonu.setOnClickListener(new View.OnClickListener() {

            /* Aşağıdaki kod şunları yapar:
            * 1) seçilen index'in başını keser, ve hangi yiyeceğin olduğunun adını alır.
            * 2) bu adı database'e gönderir, ve bu yiyeceğin kalorisini alır.
            * 3) bu kaloriyi tarihiyle beraber database'e ekler.
            * */
            @Override
            public void onClick(View v)
            {
                Kullanici kullanici = ktdb.kullaniciDao().loadFirstKullanici();
                String yiyecek = datalist.get(indis);
                String[] parts = yiyecek.split("\\|");
                String kalori = parts[2].replaceAll("[^0-9]", "");
                Log.i("Kalori", "kalori: "+kalori);
                cal = Integer.parseInt(kalori);
                Log.i("Kalori", "cal: "+cal);
                ktdb.ogunKayitDao().insertOgunKayitKaloris(cal, TarihUtil.getGun(),TarihUtil.getAy(),TarihUtil.getYil());
                }
        });
    }


}