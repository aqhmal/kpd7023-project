package com.aqhmal.tempahanmakanan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {
    // Variables declaration
    EditText username, password;
    Button loginBtn;
    SweetAlertDialog alert;
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // Hide action bar
        setContentView(R.layout.activity_login);
        // Get layout view
        constraintLayout = findViewById(R.id.constraintLayout);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);
        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);
        // Variables initialization
        username = findViewById(R.id.unameInput);
        password = findViewById(R.id.passInput);
        loginBtn = findViewById(R.id.loginBtn);
        // Declare and initialize current login attempt
        final int[] attempt = {0};
        // Login button listener
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    String uname = username.getText().toString();
                    String pass = password.getText().toString();
                    // Check if username or password text are empty
                    if(TextUtils.isEmpty((uname))) {
                        throw new Exception("Username cannot be empty");
                    }
                    if(TextUtils.isEmpty(pass)) {
                        throw new Exception("Password cannot be empty");
                    }
                    // Login Success
                    if(uname.equals("root") && pass.equals("root")) {
                        // Login successful
                        alert = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        alert.setContentText("Login Success!");
                        alert.setCancelable(false);
                        alert.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                // Go to next activity
                                Intent main_activity = new Intent(LoginActivity.this, MainActivity.class);
                                finish(); // Kill current activity
                                startActivity(main_activity);
                            }
                        });
                        alert.show();
                    } else {
                        // Increment attempt
                        ++attempt[0];
                        // Disable login button if attempt is 3
                        if(attempt[0] >= 3) {
                            loginBtn.setEnabled(false);
                            throw new Exception("Login has been disabled");
                        } else {
                            throw new Exception("Invalid credential");
                        }
                    }
                } catch(Exception e) {
                    // Error sweetalert
                    alert = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                    alert.setTitleText("Error");
                    alert.setContentText(e.getMessage() + "!");
                    alert.setCancelable(false);
                    alert.show();
                    alert.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#fc5046"));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}