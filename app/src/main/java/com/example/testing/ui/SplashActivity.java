package com.example.testing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.testing.R;
import com.example.testing.databinding.ActivitySplashBinding;
import com.example.testing.usersession.BaseActivity;
import com.example.testing.utility.ConnectionLiveData;
import com.example.testing.utility.Connectivity;

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;

    String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        checkInternetConnectivity();


    }


    private void checkInternetConnectivity() {
        if (Connectivity.isConnected(this)) {

            if (sharedPreferences.contains("username")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, HomeScreenActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 2000);


            } else {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 2000);

            }
        } else {
            Toast.makeText(SplashActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            initInternetListener();
        }
    }

    private void initInternetListener() {
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(this, connectionModel -> {
            if (connectionModel.getIsConnected()) {

                if (sharedPreferences.contains("username")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(SplashActivity.this, HomeScreenActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }, 2000);


                } else {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }, 2000);

                }
                Toast.makeText(SplashActivity.this, "Connected", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(SplashActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}