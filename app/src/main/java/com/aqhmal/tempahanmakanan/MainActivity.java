package com.aqhmal.tempahanmakanan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aqhmal.tempahanmakanan.adapter.RecyclerViewVerticalListAdapter;
import com.aqhmal.tempahanmakanan.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    private List<Food> foodList = new ArrayList<>();
    private RecyclerViewVerticalListAdapter foodAdapter;
    private int currentApiVersion;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide(); // Hide action bar
        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        setTitle("Order Food");
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

        // Buttons
        Button logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog swa = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                swa.setTitleText("Log Keluar");
                swa.setContentText("Adakah anda ingin log keluar?");
                swa.setConfirmText("Ya");
                swa.setCancelText("Tidak");
                swa.showCancelButton(true);
                swa.setCancelable(false);
                swa.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                swa.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.setTitleText("Log Keluar");
                        sweetAlertDialog.setContentText("Anda telah dilog keluar");
                        sweetAlertDialog.setConfirmText("OK");
                        sweetAlertDialog.showCancelButton(false);
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent login_activity = new Intent(MainActivity.this, LoginActivity.class);
                                finish();
                                startActivity(login_activity);
                            }
                        });
                    }
                });
                swa.show();
            }
        });

        RecyclerView foodRecyclerView = findViewById(R.id.idRecyclerViewVerticalList);
        foodRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        foodAdapter = new RecyclerViewVerticalListAdapter(foodList, getApplicationContext());
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        foodRecyclerView.setLayoutManager(verticalLayoutManager);
        foodRecyclerView.setAdapter(foodAdapter);
        populateFoodList();
    }

    private void populateFoodList() {
        Food nasi_goreng_ayam = new Food("Nasi Goreng Ayam", R.drawable.nasi_goreng_ayam);
        Food nasi_pattaya = new Food("Nasi Goreng Pattaya", R.drawable.nasi_goreng_pattaya);
        Food nasi_kampung = new Food("Nasi Goreng Kampung", R.drawable.nasi_goreng_kampung);
        Food maggi_goreng = new Food("Maggi Goreng", R.drawable.maggi_goreng);
        Food mee_goreng = new Food("Mee Goreng", R.drawable.mee_goreng);
        Food kuey_teow_goreng = new Food("Kuey Teow Goreng", R.drawable.kuey_teow_goreng);
        Food roti_canai = new Food("Roti Canai", R.drawable.roti_canai);
        foodList.add(nasi_goreng_ayam);
        foodList.add(nasi_pattaya);
        foodList.add(nasi_kampung);
        foodList.add(maggi_goreng);
        foodList.add(mee_goreng);
        foodList.add(kuey_teow_goreng);
        foodList.add(roti_canai);
        foodAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}