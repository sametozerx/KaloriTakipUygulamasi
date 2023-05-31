package com.proje.kaloritakipuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.proje.kaloritakipuygulamasi.database.firebase_entity.Ogun;

import java.util.ArrayList;
import java.util.List;

public class BesineklemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besinekleme);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference().child("ogunler");

        ListView yemeklistesi = (ListView) findViewById(R.id.yemeklistesi);
        List<String> datalist = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(BesineklemeActivity.this, android.R.layout.simple_list_item_1, datalist);

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
    }


}