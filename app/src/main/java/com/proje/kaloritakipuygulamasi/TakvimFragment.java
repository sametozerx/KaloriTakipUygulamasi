package com.proje.kaloritakipuygulamasi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.ZoneId;


public class TakvimFragment extends Fragment {

    CalendarView takvim;
    TextView yazi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_takvim, container, false);
        takvim = (CalendarView) view.findViewById(R.id.takvim);
        yazi = (TextView) view.findViewById(R.id.txtTarihlik);
        takvim.setDate(System.currentTimeMillis(),false,true);

        takvim.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String tarih = dayOfMonth + "/" + (month+1) + "/" + year;
                yazi.setText(tarih);
            }
        });
        return view;
    }
}