package com.aqhmal.tempahanmakanan;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class CheckOutActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // Hide action bar
        setContentView(R.layout.activity_check_out);
        TextView dummyTxt = findViewById(R.id.dummy);
        dummyTxt.setText("abc123");
    }
}
