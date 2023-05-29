package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import com.proje.kaloritakipuygulamasi.databinding.ActivityAnaMenuBinding;

import java.util.List;

public class AnaMenuActivity extends AppCompatActivity{

    KaloriTakipDatabase ktdb = KaloriTakipDatabase.getKaloriTakipDatabase(AnaMenuActivity.this);

    String device_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    Kullanici kullanici = ktdb.kullaniciDao().loadKullaniciByDeviceId(device_id);
    String kullaniciAdi;
    String kullaniciCinsiyeti;
    Integer kullaniciYasi;
    Integer kullaniciBoyu;
    Integer kullaniciKilosu;
    ActivityAnaMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kullaniciAdi= kullanici.getKullaniciAdi();
        kullaniciCinsiyeti= kullanici.getCinsiyet();
        kullaniciYasi= kullanici.getKullaniciYas();
        kullaniciBoyu= kullanici.getKullaniciBoy();
        kullaniciKilosu= kullanici.getKullaniciKilo();

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
    public void DataGuncelle(String hangisi, String data)
    {
        if (hangisi == "isim")
        {
            kullanici.setKullaniciAdi(hangisi);
        }
        else if (hangisi =="boy")
        {
            kullanici.setKullaniciBoy(Integer.parseInt(hangisi));
        }
        else if (hangisi =="kilo")
        {
            kullanici.setKullaniciKilo(Integer.parseInt(hangisi));
        }
        else
        {
            kullanici.setKullaniciYas(Integer.parseInt(hangisi));
        }
    }
}