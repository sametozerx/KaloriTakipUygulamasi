package com.proje.kaloritakipuygulamasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.dao.KullaniciDao;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class IlkKayitActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_kayit);
        autoCompleteTextView = findViewById(R.id.AutoCompleteCinsiyet);

        String[] Cinsiyetler = new String[]{getString(R.string.erkek), getString(R.string.kadin)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, Cinsiyetler);
        autoCompleteTextView.setAdapter(adapter);
        //Devam Et'e basıldığında SQL içine bilgileri alır ve ana menüye geçer.
        // Bilgiler için elementler hazırlandı.
        TextInputEditText adBilgisi = (TextInputEditText) findViewById(R.id.inputAd);
        TextInputEditText yasBilgisi = (TextInputEditText) findViewById(R.id.inputYas);
        TextInputEditText kiloBilgisi = (TextInputEditText) findViewById(R.id.inputKilo);
        TextInputEditText boyBilgisi = (TextInputEditText) findViewById(R.id.inputBoy);
        AutoCompleteTextView cinsBilgisi = (AutoCompleteTextView) findViewById(R.id.AutoCompleteCinsiyet);
        MaterialButton button = (MaterialButton) findViewById(R.id.btnIlkKayit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Girişler SQL'e yapılıyor.
                String ad = adBilgisi.getText().toString();
                Integer yas = Integer.parseInt(yasBilgisi.getText().toString());
                Integer kilo = Integer.parseInt(kiloBilgisi.getText().toString());
                Integer boy = Integer.parseInt(boyBilgisi.getText().toString());
                String cinsiyet = cinsBilgisi.getText().toString();
                Kullanici kullanici = new Kullanici();
                kullanici.setKullaniciAdi(ad);
                kullanici.setKullaniciBoy(boy);
                kullanici.setKullaniciKilo(kilo);
                kullanici.setCinsiyet(cinsiyet);
                kullanici.setKullaniciYas(yas);
                KaloriTakipDatabase ktdb = KaloriTakipDatabase.getKaloriTakipDatabase(IlkKayitActivity.this);
                ktdb.kullaniciDao().insertKullanici(kullanici);
                List<Kullanici> kullaniciList = ktdb.kullaniciDao().loadAllKullanicis();
                startActivity(new Intent(IlkKayitActivity.this, AnaMenuActivity.class));
            }
        });

    }
}