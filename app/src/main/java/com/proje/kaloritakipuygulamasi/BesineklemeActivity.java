package com.proje.kaloritakipuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import com.proje.kaloritakipuygulamasi.database.firebase_entity.Ogun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BesineklemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besinekleme);
        List<Integer> lockedItems = new ArrayList<>();
        KaloriTakipDatabase ktdb = KaloriTakipDatabase.getKaloriTakipDatabase(this);
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference().child("ogunler");

        ListView yemeklistesi = (ListView) findViewById(R.id.yemeklistesi);
        List<String> datalist = new ArrayList<>();
        Button ekleButonu = (Button) findViewById(R.id.eklemebuton);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BesineklemeActivity.this, android.R.layout.simple_list_item_1, datalist)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                if (lockedItems.contains(position)) {
                    // Kilitlenmiş öğeleri etkisiz hale getirin.
                    view.setEnabled(false);
                    view.setClickable(false);
                }
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
                lockedItems.add(position);
            }
        });

        EditText searchEditText = findViewById(R.id.inputAd);

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
            @Override
            public void onClick(View v)
            {
                Kullanici kullanici = ktdb.kullaniciDao().loadFirstKullanici();
            }
        });
    }


}