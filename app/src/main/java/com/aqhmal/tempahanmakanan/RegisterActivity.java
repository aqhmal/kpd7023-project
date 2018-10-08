package com.aqhmal.tempahanmakanan;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {
    EditText name, username, password;
    Button registerBtn;
    SweetAlertDialog alert;
    private AnimationDrawable animationDrawable;
    DBHelper DBHelper;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        name = findViewById(R.id.nameInput);
        username = findViewById(R.id.unameInput);
        password = findViewById(R.id.passInput);
        registerBtn = findViewById(R.id.registerBtn);
        DBHelper = new DBHelper(this);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    String iname = name.getText().toString();
                    String uname = username.getText().toString();
                    String pass = password.getText().toString();
                    if(TextUtils.isEmpty(iname)) {
                        throw new Exception("Name cannot be empty");
                    }
                    if(TextUtils.isEmpty((uname))) {
                        throw new Exception("Username cannot be empty");
                    }
                    if(TextUtils.isEmpty(pass)) {
                        throw new Exception("Password cannot be empty");
                    }
                    String query = "INSERT INTO " + TempahanMakanan.TABLE + "(" + TempahanMakanan.NAME + ", " + TempahanMakanan.USERNAME + ", "+TempahanMakanan.PASSWORD + ") VALUES('" + iname + "', '" + uname + "', '" + pass + "')";

                    SQLiteDatabase sqLiteDatabase = DBHelper.getWritableDatabase();

                    sqLiteDatabase.execSQL(query);

                    sqLiteDatabase.close();

                    alert = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    alert.setContentText("Register Successful");
                    alert.setCancelable(false);
                    alert.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent login_activity = new Intent(RegisterActivity.this, LoginActivity.class);
                            finish();
                            startActivity(login_activity);
                        }
                    });
                    alert.show();
                } catch(Exception e) {
                    // Error sweetalert
                    alert = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
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