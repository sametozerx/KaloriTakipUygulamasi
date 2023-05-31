package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proje.kaloritakipuygulamasi.database.firebase_entity.Ogun;

public class OgunCrudActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogun_crud);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference();

        EditText ogunisim = (EditText) findViewById(R.id.ogunisim);
        EditText ogunbirim = (EditText) findViewById(R.id.ogunbirim);
        EditText ogunkalori = (EditText) findViewById(R.id.kalori);

        Button buton = (Button) findViewById(R.id.button);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isim = ogunisim.getText().toString();
                String birim = ogunbirim.getText().toString();

                int kalori = Integer.parseInt(ogunkalori.getText().toString());

                Ogun ogun = new Ogun();
                ogun.setOgunAd(isim);
                ogun.setOgunBirim(birim);
                ogun.setOgunKalori(kalori);

                dbref.child("ogunler").child(isim).setValue(ogun);
            }
        });

    }





}