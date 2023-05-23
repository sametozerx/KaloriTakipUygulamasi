package com.proje.kaloritakipuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Scanner;

public class IlkKayitActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_kayit);
        autoCompleteTextView = findViewById(R.id.AutoCompleteCinsiyet);

        String[] Cinsiyetler = new String[]{getString(R.string.erkek), getString(R.string.kadin)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, Cinsiyetler);
        autoCompleteTextView.setAdapter(adapter);

        //autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
        //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //    }
        //});

    }}