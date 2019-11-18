package com.example.stock.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

public class StockUtils {



    //used to setup the toolbar
    public static void setUpToolbar(Toolbar toolbar, FragmentActivity mActivity) {
        AppCompatActivity activity = (AppCompatActivity) mActivity;
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

}
