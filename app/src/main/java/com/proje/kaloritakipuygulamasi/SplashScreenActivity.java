package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;

import java.util.List;

public class    SplashScreenActivity extends AppCompatActivity {

    // Aşağıdaki komut satırları, splash screen'den IlkKayitAcitivity'ye geçişi sağlamaktadır.
    Handler h = new Handler();

    KaloriTakipDatabase kaloriTakipDatabase = KaloriTakipDatabase.getKaloriTakipDatabase(this);
    List<Kullanici> kullanicilar = kaloriTakipDatabase.kullaniciDao().loadAllKullanicis();
    boolean kullaniciKayitliMi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        for (Kullanici kullanici : kullanicilar){
            if (kullanici.getDeviceId().equals(android_id)){
                kullaniciKayitliMi = true;
                break;
            }
        }
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (kullaniciKayitliMi)
                {
                    Intent iTrue = new Intent(SplashScreenActivity.this, AnaMenuActivity.class);
                    startActivity(iTrue);
                    finish();
                }
                else
                {
                    // Kullaniciyi kayit ekranina götürür.
                    Intent iFalse = new Intent(SplashScreenActivity.this, IlkKayitActivity.class);
                    startActivity(iFalse);
                    finish();
                }
            }
        },5000);
    }
}