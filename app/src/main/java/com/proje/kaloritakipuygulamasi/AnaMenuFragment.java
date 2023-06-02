package com.proje.kaloritakipuygulamasi;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.proje.kaloritakipuygulamasi.database.KaloriTakipDatabase;
import com.proje.kaloritakipuygulamasi.database.entities.Kullanici;
import com.proje.kaloritakipuygulamasi.util.TarihUtil;


public class AnaMenuFragment extends Fragment {

    public float progress;
    NotificationManagerCompat notificationManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NotificationManagerCompat not2 = NotificationManagerCompat.from(getContext());
        notificationManager=not2;
        KaloriTakipDatabase kaloridb = KaloriTakipDatabase.getKaloriTakipDatabase(requireContext());
        Kullanici kullanici = kaloridb.kullaniciDao().loadFirstKullanici();
        int alinanCal = kaloridb.ogunKayitDao().loadAllKaloriByDailies(TarihUtil.getGun(), TarihUtil.getAy(), TarihUtil.getYil());
        float gerekenCal = 0;
        View view = inflater.inflate(R.layout.fragment_ana_menu, container, false);
        TextView textYuzdeKalori = (TextView) view.findViewById(R.id.txtYuzdeKalori);
        ProgressBar pb = (ProgressBar) view.findViewById(R.id.progressBar);
        TextView gerekenkal = (TextView) view.findViewById(R.id.txtVarGerekenCal);
        Context context = getActivity();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Kanal kimliği
            String channelId = "my_channel_id";
            // Kanal adı
            String channelName = "My Channel";
            // Bildirim önem düzeyi
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);

            // Kanal açıklaması (isteğe bağlı)
            channel.setDescription("My Channel Description");

            // Bildirim yöneticisi tarafından kanal oluşturma
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        String channelId = "bildirimKanali";
        TextView tv2 = (TextView) view.findViewById(R.id.txtSelamlama);
        tv2.setText("İyi Günler, " + kullanici.getKullaniciAdi());

        TextView tvalinancal = (TextView) view.findViewById(R.id.txtVarAlinanCal);
        tvalinancal.setText(Integer.toString(alinanCal));


        Button kahvaltibtn = (Button) view.findViewById(R.id.btnBesinEkle);

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
                getActivity().finish();
            }
        });

        if ("Erkek".equals(kullanici.getCinsiyet())) {
            gerekenCal = (float) (66.5 + (13.75 * kullanici.getKullaniciKilo()) + (5 * kullanici.getKullaniciBoy()) - (6.77 * kullanici.getKullaniciYas()));
            gerekenkal.setText(Integer.toString((int) gerekenCal));

        } else if ("Kadın".equals(kullanici.getCinsiyet())) {
            gerekenCal = (float) (655.1 + (9.56 * kullanici.getKullaniciKilo()) + (1.85 * kullanici.getKullaniciBoy()) - (4.67 * kullanici.getKullaniciYas()));
            gerekenkal.setText(Integer.toString((int) gerekenCal));

        }

        progress = (alinanCal / gerekenCal) * 100;
        Log.i("progress", "Progress=" + progress);
        Log.i("alinancal", "alinancal=" + alinanCal);
        Log.i("gerekencal", "gerekencal=" + gerekenCal);
        pb.setProgress((int) progress);
        textYuzdeKalori.setText("%" + (int) progress);

        if (progress >= 100)
        {
            Toast.makeText(getContext(),"Gereğinden fazla yemek yediniz. Artık kalori almamanızı öneririz.", Toast.LENGTH_LONG);
        }
        return view;
    }



}