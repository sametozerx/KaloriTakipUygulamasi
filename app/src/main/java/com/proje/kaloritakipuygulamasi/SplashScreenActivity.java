package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    // Aşağıdaki komut satırları, splash screen'den IlkKayitAcitivity'ye geçişi sağlamaktadır.
    Handler h = new Handler();
    boolean kullaniciKayitliMi = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

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