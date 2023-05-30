package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    // Aşağıdaki komut satırları, splash screen'den IlkKayitAcitivity'ye geçişi sağlamaktadır.
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {
            setContentView(R.layout.activity_splash_screen_night);
        } else {
            setContentView(R.layout.activity_splash_screen);
        }

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (KullaniciKaydiVarMi())
                {
                    // Kullanici Kaydi var, ana menüye gider.
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

    private boolean KullaniciKaydiVarMi(){
        // Kullanicinin kaydi varsa true, yoksa false döner.
        KaloriTakipDatabase ktdb = KaloriTakipDatabase.getKaloriTakipDatabase(SplashScreenActivity.this);
        List<Kullanici> kullaniciList = ktdb.kullaniciDao().loadAllKullanicis();
        if (kullaniciList.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}