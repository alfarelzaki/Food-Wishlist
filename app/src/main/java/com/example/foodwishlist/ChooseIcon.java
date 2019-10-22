package com.example.foodwishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ChooseIcon extends AppCompatActivity {

    private ArrayList<Icon> iconValues = new ArrayList<>();
    private RecyclerView recyclerView;
    private IconAdapter IconAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_icon);

        String extraNamaMakanan = getIntent().getStringExtra("NAMA_MAKANAN");
        String extraDeskripsiMakanan = getIntent().getStringExtra("DESKRIPSI_MAKANAN");
        Log.d("cek nama", extraNamaMakanan);
        Log.d("cek deksripsi", extraDeskripsiMakanan);

        getSupportActionBar().setTitle("Choose Icon");
        recyclerView = findViewById(R.id.recyclerViewIcon);

        iconValues.addAll(IconData.getListData());

        IconAdapter = new IconAdapter(this, iconValues, extraNamaMakanan, extraDeskripsiMakanan);
        recyclerView.setLayoutManager(
                new GridLayoutManager(this, 3));
        recyclerView.setAdapter(IconAdapter);
    }
}
