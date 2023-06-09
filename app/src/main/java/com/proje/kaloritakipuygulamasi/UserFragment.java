package com.proje.kaloritakipuygulamasi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
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







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        KaloriTakipDatabase kaloridb = KaloriTakipDatabase.getKaloriTakipDatabase(requireContext());
        Kullanici kullanici = kaloridb.kullaniciDao().loadFirstKullanici();

        TextView tv = (TextView) view.findViewById(R.id.varIsimAlani);
        TextView tv2 = (TextView) view.findViewById(R.id.varBoyAlani);
        TextView tv3 = (TextView) view.findViewById(R.id.varKiloAlani);
        TextView tv4 = (TextView) view.findViewById(R.id.varYasAlani);
        TextView tv5 = (TextView) view.findViewById(R.id.varcinsiyet);


        tv.setText(kullanici.getKullaniciAdi().toString());
        tv2.setText(Integer.toString(kullanici.getKullaniciBoy()));
        tv3.setText(Integer.toString((kullanici.getKullaniciKilo())));
        tv4.setText(Integer.toString((kullanici.getKullaniciYas())));
        tv5.setText(kullanici.getCinsiyet().toString().substring(0,1));


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

        //cinsiyet güncellemesi

        Button bt5 = (Button) view.findViewById(R.id.btnerkek);
        Button bt6 = (Button) view.findViewById(R.id.btnkadin);

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kaloridb.kullaniciDao().updateFirstKullaniciCinsiyet("Erkek");
                tv5.setText("E");


            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kaloridb.kullaniciDao().updateFirstKullaniciCinsiyet("Kadın");
                tv5.setText("K");


            }
        });

        return view;
    }




    public void DegistirmeIslemi(int AlarmStringi, TextView tv, String hangisi)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(AlarmStringi);

        final EditText input = new EditText(getActivity());

        if(hangisi == "isim") {

            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
        }
        else{

            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);
        }



        builder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = input.getText().toString();
                userStringCevirme(user, tv, hangisi);
                KaloriTakipDatabase kaloridb = KaloriTakipDatabase.getKaloriTakipDatabase(requireContext());
                if(hangisi=="isim") {
                    kaloridb.kullaniciDao().updateFirstKullaniciAd(user);
                } else if (hangisi=="boy") {
                    kaloridb.kullaniciDao().updateFirstKullaniciBoy(Integer.parseInt(user));
                }
                else if (hangisi=="kilo") {
                    kaloridb.kullaniciDao().updateFirstKullaniciKilo(Integer.parseInt(user));

                }
                else if (hangisi=="yas") {
                    kaloridb.kullaniciDao().updateFirstKullaniciYas(Integer.parseInt(user));



                }



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
        Intent intent = new Intent(getActivity(),AnaMenuActivity.class);
    }
}