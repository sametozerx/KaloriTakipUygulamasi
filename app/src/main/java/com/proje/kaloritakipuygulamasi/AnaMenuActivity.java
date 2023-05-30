package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import com.proje.kaloritakipuygulamasi.databinding.ActivityAnaMenuBinding;

import java.util.List;

public class AnaMenuActivity extends AppCompatActivity{

    KaloriTakipDatabase ktdb;
    Kullanici kullanici;
    String kullaniciAdi;
    String kullaniciCinsiyeti;
    Integer kullaniciYasi;
    Integer kullaniciBoyu;
    Integer kullaniciKilosu;
    ActivityAnaMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ktdb = KaloriTakipDatabase.getKaloriTakipDatabase(AnaMenuActivity.this);
        Kullanici kullanici = ktdb.kullaniciDao().loadFirstKullanici();


        kullaniciAdi= kullanici.getKullaniciAdi();
        final String Tag = AnaMenuActivity.class.getName();
        Log.i(Tag, kullaniciAdi);
        kullaniciCinsiyeti= kullanici.getCinsiyet();
        Log.i(Tag, kullaniciCinsiyeti);
        kullaniciYasi= kullanici.getKullaniciYas();
        Log.i(Tag, kullaniciYasi.toString());
        kullaniciBoyu= kullanici.getKullaniciBoy();
        Log.i(Tag, kullaniciBoyu.toString());
        kullaniciKilosu= kullanici.getKullaniciKilo();
        Log.i(Tag, kullaniciKilosu.toString());

        setContentView(R.layout.activity_ana_menu);
        binding = ActivityAnaMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ReplaceFragment(new AnaMenuFragment());
        binding.bottomNavigation.setBackground(null);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId())
            {
                case R.id.page1:
                    ReplaceFragment(new AnaMenuFragment());
                    break;
                case R.id.page2:
                    ReplaceFragment(new TakvimFragment());
                    break;
                case R.id.page3:
                    ReplaceFragment(new UserFragment());
                    break;
            }

            return true;
        });

    }

    private void ReplaceFragment(Fragment fragment)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Layout, fragment);
        fragmentTransaction.commit();
    }


}