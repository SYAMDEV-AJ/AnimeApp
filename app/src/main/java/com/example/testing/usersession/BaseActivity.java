package com.example.testing.usersession;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testing.utility.CustomLoader;

public class BaseActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public Activity mActivity;

    public String usrername = "";
    public String password = "";
    private CustomLoader customLoader;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customLoader = new CustomLoader(this);


        sharedPreferences = getSharedPreferences("OpenTrendApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        usrername = sharedPreferences.getString("username", "");


    }


    public void showProgress() {
        if (!customLoader.isShowing())
            customLoader.show();
    }

    /**
     * Hide Progress Dialog
     */
    public void hideProgress() {
        if (customLoader.isShowing())
            customLoader.dismiss();
    }
}

