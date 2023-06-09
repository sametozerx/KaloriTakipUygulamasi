package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import java.util.List;

public class IlkKayitActivity extends AppCompatActivity {

    AutoCompleteTextView cinsBilgisi;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_kayit);
        cinsBilgisi = findViewById(R.id.AutoCompleteCinsiyet);

        String[] Cinsiyetler = new String[]{getString(R.string.erkek), getString(R.string.kadin)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, Cinsiyetler);
        cinsBilgisi.setAdapter(adapter);
        //Devam Et'e basıldığında SQL içine bilgileri alır ve ana menüye geçer.
        // Bilgiler için elementler hazırlandı.
        TextInputEditText adBilgisi = (TextInputEditText) findViewById(R.id.inputBesinAdi);
        TextInputEditText yasBilgisi = (TextInputEditText) findViewById(R.id.inputYas);
        TextInputEditText kiloBilgisi = (TextInputEditText) findViewById(R.id.inputKilo);
        TextInputEditText boyBilgisi = (TextInputEditText) findViewById(R.id.inputBoy);
        cinsBilgisi = (AutoCompleteTextView) findViewById(R.id.AutoCompleteCinsiyet);
        MaterialButton button = (MaterialButton) findViewById(R.id.btnIsimDegistir);

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