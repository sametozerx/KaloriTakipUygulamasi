package com.proje.kaloritakipuygulamasi;

import android.graphics.drawable.RotateDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


public class AnaMenuFragment extends Fragment {

    View DisView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // View'ı fonksiyondan çıkartır.
        View IcView = inflater.inflate(R.layout.fragment_ana_menu, container, false);
        DisView = IcView;

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ana_menu, container, false);
    }

}