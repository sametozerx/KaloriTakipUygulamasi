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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);


        TextView tv = (TextView) view.findViewById(R.id.varIsimAlani);
        TextView tv2 = (TextView) view.findViewById(R.id.varBoyAlani);
        TextView tv3 = (TextView) view.findViewById(R.id.varKiloAlani);
        TextView tv4 = (TextView) view.findViewById(R.id.varYasAlani);

        if (getArguments()!= null)
        {
            String kullaniciAdi = getArguments().getString("isim");
            Integer kullaniciYasi = getArguments().getInt("yas");
            Integer kullaniciBoyu = getArguments().getInt("boy");
            Integer kullaniciKilosu = getArguments().getInt("kilo");

            tv.setText(kullaniciAdi);
            tv2.setText(kullaniciYasi.toString());
            tv3.setText(kullaniciKilosu.toString());
            tv4.setText(kullaniciBoyu.toString());
        }

        // Kullanici verileri ekrana yüklenecek.

        Button bt = (Button) view.findViewById(R.id.btnIsimDegistir);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.isimGirin, tv);
            }
        });
        // boy güncellemesi.
        Button bt2 = (Button) view.findViewById(R.id.btnBoyDegis);
        bt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.boyGirin, tv2);
            }
        });
        // kilo güncellemesi.
        Button bt3 = (Button) view.findViewById(R.id.btnKiloDegis);
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.kiloGirin, tv3);
            }
        });
        // yaş güncellemesi.
        Button bt4 = (Button) view.findViewById(R.id.btnYasDegis);
        bt4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DegistirmeIslemi(R.string.yasGirin, tv4);
            }
        });
        return view;
    }

    public void DegistirmeIslemi(int AlarmStringi, TextView tv)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(AlarmStringi);

        final EditText input = new EditText(getActivity());
        builder.setView(input);

        builder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = input.getText().toString();
                userStringCevirme(user, tv);
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

    public void userStringCevirme(String st, TextView tv){
        //TODO: Buradaki st string'i database'e de gidecek.
        tv.setText(st);
    }
}