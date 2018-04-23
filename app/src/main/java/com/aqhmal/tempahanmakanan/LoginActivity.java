package com.aqhmal.tempahanmakanan;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginBtn;
    SweetAlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.unameInput);
        password = findViewById(R.id.passInput);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    String uname = username.getText().toString();
                    String pass = password.getText().toString();

                    if(TextUtils.isEmpty((uname))) {
                        throw new Exception("Username cannot be empty");
                    }
                    if(TextUtils.isEmpty(pass)) {
                        throw new Exception("Password cannot be empty.");
                    }
                    if(!Objects.equals(uname, "root") || !Objects.equals(pass, "root")) {
                        throw new Exception("Wrong username or password");
                    } else {
                        alert = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        alert.setTitleText("Good job!");
                        alert.setContentText("You clicked the button!");
                        alert.show();
                    }
                } catch(Exception e) {
                    alert = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE);
                    alert.setTitleText("Invalid Input");
                    alert.setContentText(e.getMessage() + "!");
                    alert.show();
                }
            }
        });
    }
}