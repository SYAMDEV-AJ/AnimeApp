package com.example.testing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.databinding.DataBindingUtil;

import com.example.testing.R;

import com.example.testing.constants.Constants;
import com.example.testing.databinding.ActivityLoginBinding;
import com.example.testing.usersession.BaseActivity;
import com.example.testing.utility.ConnectionLiveData;
import com.example.testing.utility.Connectivity;


public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginbtn();
        checkInternetConnectivity();
        ShowHidePass();

    }

    private void loginbtn() {

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.username.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
                } else if (binding.password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (flag.equals("1")) {
                        if ((binding.username.getText().toString().equals(Constants.USERNAME)) && (binding.password.getText().toString().equals(Constants.PASSWORD))) {

                            editor.putString("username", binding.username.getText().toString());
                            editor.putString("password", binding.username.getText().toString());
                            editor.apply();

                            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid LoginCredentials", Toast.LENGTH_SHORT).show();


                        }
                    } else if (flag.equals("2")) {
                        Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });


    }


    private void checkInternetConnectivity() {
        if (Connectivity.isConnected(this)) {
            flag = "1";
        } else {
            flag = "2";
            Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            initInternetListener();
        }
    }

    private void initInternetListener() {
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(this, connectionModel -> {
            if (connectionModel.getIsConnected()) {
                Toast.makeText(LoginActivity.this, "Connected", Toast.LENGTH_SHORT).show();

                flag = "1";
            } else {
                flag = "2";
                Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ShowHidePass() {
        binding.showpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.password.getInputType() != (InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    binding.showpassword.setBackground(getResources().getDrawable(R.drawable.ic_showhide_password));
                    binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    binding.password.setSelection(binding.password.getText().toString().length());
                } else {
                    binding.password.setTransformationMethod(null);
                    binding.password.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.showpassword.setBackground(getResources().getDrawable(R.drawable.ic_show_password));
                    binding.password.setSelection(binding.password.getText().toString().length());
                }
            }
        });
    }

}