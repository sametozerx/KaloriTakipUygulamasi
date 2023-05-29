package com.proje.kaloritakipuygulamasi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;

import java.util.List;

public class UserFragment extends Fragment {

    AnaMenuActivity anaMenuActivity = new AnaMenuActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        TextView tv = (TextView) view.findViewById(R.id.varIsimAlani);
        TextView tv2 = (TextView) view.findViewById(R.id.varBoyAlani);
        TextView tv3 = (TextView) view.findViewById(R.id.varKiloAlani);
        TextView tv4 = (TextView) view.findViewById(R.id.varYasAlani);

        tv.setText(anaMenuActivity.kullaniciAdi);
        tv2.setText(anaMenuActivity.kullaniciBoyu.toString());
        tv3.setText(anaMenuActivity.kullaniciKilosu.toString());
        tv4.setText(anaMenuActivity.kullaniciYasi.toString());

        // Kullanici verileri ekrana yüklenecek.

        Button bt = (Button) view.findViewById(R.id.btnIsimDegistir);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.isimGirin, tv, "isim");
            }
        });
        // boy güncellemesi.
        Button bt2 = (Button) view.findViewById(R.id.btnBoyDegis);
        bt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.boyGirin, tv2, "boy");
            }
        });
        // kilo güncellemesi.
        Button bt3 = (Button) view.findViewById(R.id.btnKiloDegis);
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.kiloGirin, tv3, "kilo");
            }
        });
        // yaş güncellemesi.
        Button bt4 = (Button) view.findViewById(R.id.btnYasDegis);
        bt4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.yasGirin, tv4, "yas");
            }
        });
        return view;
    }

    public void DegistirmeIslemi(int AlarmStringi, TextView tv, String hangisi)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(AlarmStringi);

        final EditText input = new EditText(getActivity());
        builder.setView(input);

        builder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = input.getText().toString();
                userStringCevirme(user, tv, hangisi);
            }
        });

        builder.setNegativeButton(R.string.iptal, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void userStringCevirme(String data, TextView tv, String hangisi){
        tv.setText(data);
        if (hangisi == "isim")
        {
            anaMenuActivity.DataGuncelle("isim", data);
        }
        else if (hangisi =="boy")
        {
            anaMenuActivity.DataGuncelle("boy", data);
        }
        else if (hangisi =="kilo")
        {
            anaMenuActivity.DataGuncelle("kilo", data);
        }
        else
        {
            anaMenuActivity.DataGuncelle("yas", data);
        }
    }
}