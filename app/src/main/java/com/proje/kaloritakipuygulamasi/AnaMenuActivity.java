package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.proje.kaloritakipuygulamasi.databinding.ActivityAnaMenuBinding;

public class AnaMenuActivity extends AppCompatActivity{

    ActivityAnaMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Layout, fragment);
        fragmentTransaction.commit();
    }

}