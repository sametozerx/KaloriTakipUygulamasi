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

        kahvaltibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), BesineklemeActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }


}