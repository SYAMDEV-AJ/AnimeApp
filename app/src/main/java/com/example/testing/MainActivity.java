package com.example.testing;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testing.modelclass.CharacterResponse;
import com.example.testing.modelclass.LoginResponse;
import com.example.testing.network.viewmodel.Viewmodel;

public class MainActivity extends AppCompatActivity {

    Viewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewmodel = ViewModelProviders.of(this).get(Viewmodel.class);

       // viewmodel.login();
        viewmodel.getCharacterResponseMutableLiveData().observe(this, new Observer<CharacterResponse>() {
            @Override
            public void onChanged(CharacterResponse characterResponse) {
                characterResponse.getResults().get(0).getId();


//                if (loginResponse.getStatus().equals("111")) {
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, loginResponse.getResult(), Toast.LENGTH_SHORT).show();
//
//                }
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();


            }
        });
        viewmodel.getFailureResponseMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });


    }
}