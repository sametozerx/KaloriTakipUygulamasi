package com.proje.kaloritakipuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import com.proje.kaloritakipuygulamasi.util.TarihUtil;

import org.w3c.dom.Text;


public class AnaMenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        KaloriTakipDatabase kaloridb = KaloriTakipDatabase.getKaloriTakipDatabase(requireContext());
        Kullanici kullanici = kaloridb.kullaniciDao().loadFirstKullanici();
        int alinanCal = kaloridb.ogunKayitDao().loadAllKaloriByDailies(TarihUtil.getGun(),TarihUtil.getAy(),TarihUtil.getYil());
        float gerekenCal = 0;
        View view = inflater.inflate(R.layout.fragment_ana_menu, container, false);
        TextView textYuzdeKalori = (TextView) view.findViewById(R.id.txtYuzdeKalori);
        ProgressBar pb = (ProgressBar) view.findViewById(R.id.progressBar);
        TextView gerekenkal = (TextView) view.findViewById(R.id.txtVarGerekenCal);
        float progress;

        TextView tv2 = (TextView) view.findViewById(R.id.txtSelamlama);
        tv2.setText("İyi Günler, " + kullanici.getKullaniciAdi());

        TextView tvalinancal = (TextView) view.findViewById(R.id.txtVarAlinanCal);
        tvalinancal.setText(Integer.toString(alinanCal));


        Button kahvaltibtn = (Button) view.findViewById(R.id.btnKahvaltiEkle);

        Button butongecis = (Button) view.findViewById(R.id.button2);

        butongecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), OgunCrudActivity.class);
                startActivity(intent);

            }
        });


        kahvaltibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), BesineklemeActivity.class);
                startActivity(intent);

            }
        });

        if("Erkek".equals(kullanici.getCinsiyet())){
            gerekenCal = (float) (66.5 + (13.75* kullanici.getKullaniciKilo()) + (5*kullanici.getKullaniciBoy()) - (6.77* kullanici.getKullaniciYas()));
            gerekenkal.setText(Integer.toString((int) gerekenCal));

        }

        else if ("Kadın".equals(kullanici.getCinsiyet())) {
            gerekenCal = (float)(655.1 + (9.56* kullanici.getKullaniciKilo()) + (1.85*kullanici.getKullaniciBoy()) - (4.67* kullanici.getKullaniciYas()));
            gerekenkal.setText(Integer.toString((int) gerekenCal));

        }

        progress = (alinanCal / gerekenCal) * 100;
        Log.i("progress", "Progress="+progress);
        Log.i("alinancal", "alinancal="+alinanCal);
        Log.i("gerekencal", "gerekencal="+gerekenCal);
        pb.setProgress((int) progress);
        textYuzdeKalori.setText("%" + (int) progress);

        return view;
    }


}