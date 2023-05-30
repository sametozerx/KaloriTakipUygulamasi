package com.proje.kaloritakipuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;

import org.w3c.dom.Text;


public class AnaMenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        KaloriTakipDatabase kaloridb = KaloriTakipDatabase.getKaloriTakipDatabase(requireContext());
        Kullanici kullanici = kaloridb.kullaniciDao().loadFirstKullanici();

        View view = inflater.inflate(R.layout.fragment_ana_menu, container, false);
        TextView tv = (TextView) view.findViewById(R.id.txtYuzdeKalori);
        ProgressBar pb = (ProgressBar) view.findViewById(R.id.progressBar);
        String progress = Integer.toString(pb.getProgress());
        tv.setText("%" + progress);

        TextView tv2 = (TextView) view.findViewById(R.id.txtSelamlama);
        tv2.setText("İyi Günler, " + kullanici.getKullaniciAdi());

        Button kahvaltibtn = (Button) view.findViewById(R.id.btnKahvaltiEkle);
        Button ogleyemegibtn = (Button) view.findViewById(R.id.btnOgleYemegiEkle);
        Button aksamyemegibtn = (Button) view.findViewById(R.id.btnAksamYemegiEkle);
        Button araogunbtn = (Button) view.findViewById(R.id.btnAraOgunEkle);



        kahvaltibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), BesineklemeActivity.class);
                intent.putExtra("ogun", "kahvalti");
                startActivity(intent);

            }
        });

        ogleyemegibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), BesineklemeActivity.class);
                intent.putExtra("ogun", "ogleyemegi");
                startActivity(intent);

            }
        });

        aksamyemegibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), BesineklemeActivity.class);
                intent.putExtra("ogun", "aksamyemegi");
                startActivity(intent);

            }
        });

        araogunbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), BesineklemeActivity.class);
                intent.putExtra("ogun", "araogun");
                startActivity(intent);

            }
        });


        if("Erkek".equals(kullanici.getCinsiyet())){
            int kalorierkek = (int)(66.5 + (13.75* kullanici.getKullaniciKilo()) + (5*kullanici.getKullaniciBoy()) - (6.77* kullanici.getKullaniciYas()));
            TextView gerekenkal = (TextView) view.findViewById(R.id.txtVarGerekenCal);
            gerekenkal.setText(Integer.toString(kalorierkek));

        }

        else if ("Kadın".equals(kullanici.getCinsiyet())) {
            int kalorikadin = (int)(655.1 + (9.56* kullanici.getKullaniciKilo()) + (1.85*kullanici.getKullaniciBoy()) - (4.67* kullanici.getKullaniciYas()));
            TextView gerekenkal = (TextView) view.findViewById(R.id.txtVarGerekenCal);
            gerekenkal.setText(Integer.toString(kalorikadin));

        }


        return view;
    }


}