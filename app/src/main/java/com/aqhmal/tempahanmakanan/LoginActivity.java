package com.aqhmal.tempahanmakanan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    Button loginBtn, registerBtn;
    SweetAlertDialog alert;
    private AnimationDrawable animationDrawable;
    DBHelper DBHelper;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        username = findViewById(R.id.nameInput);
        password = findViewById(R.id.passInput);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        DBHelper = new DBHelper(this);
        final int[] attempt = {0};
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String uname = username.getText().toString();
                    String pass = password.getText().toString();
                    if(TextUtils.isEmpty((uname))) {
                        throw new Exception("Username cannot be empty");
                    }
                    if(TextUtils.isEmpty(pass)) {
                        throw new Exception("Password cannot be empty");
                    }
                    SQLiteDatabase sqLiteDatabase = DBHelper.getWritableDatabase();

                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + TempahanMakanan.USERNAME + ", " +
                            TempahanMakanan.PASSWORD + " FROM " + TempahanMakanan.TABLE + " WHERE " +
                            TempahanMakanan.USERNAME + " = '" + uname + "' AND " + TempahanMakanan.PASSWORD + " = '" +
                            pass + "'", null);

                    if(cursor.getCount() > 0) {
                        cursor.close();
                        alert = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        alert.setContentText("Login Success!");
                        alert.setCancelable(false);
                        alert.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent main_activity = new Intent(LoginActivity.this, MainActivity.class);
                                finish();
                                startActivity(main_activity);
                            }
                        });
                        alert.show();
                    } else {
                        ++attempt[0];
                        if (attempt[0] >= 3) {
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

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                finish();
                startActivity(intent);
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