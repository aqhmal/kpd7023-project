package com.aqhmal.tempahanmakanan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.aqhmal.tempahanmakanan.adapter.RecyclerViewVerticalListAdapter;
import com.aqhmal.tempahanmakanan.model.Food;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Food> foodList = new ArrayList<>();
    private RecyclerViewVerticalListAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Order Food");
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
}