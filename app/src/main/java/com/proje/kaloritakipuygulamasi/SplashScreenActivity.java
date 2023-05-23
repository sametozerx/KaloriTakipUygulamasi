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
                    // TODO: Ana menüye bağla.
                }
                else
                {
                    // Kullaniciyi kayit ekranina götürür.
                Intent i = new Intent(SplashScreenActivity.this, IlkKayitActivity.class);
                startActivity(i);
                finish();
                }
            }
        },5000);
    }
}