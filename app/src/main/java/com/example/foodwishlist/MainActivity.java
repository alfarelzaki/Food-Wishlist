package com.example.foodwishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<ItemData> itemValues;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private EditText inputNamaMakanan;
    private EditText inputDeskripsi;
    private ImageView imgMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        inputNamaMakanan = findViewById(R.id.inputNamaMakanan);
        inputDeskripsi = findViewById(R.id.inputDeskripsi);
        imgMakanan = findViewById(R.id.img_item);

        loadData();

        itemAdapter = new ItemAdapter(this, itemValues);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);

        updateData();
        saveData();
    }

    private void updateData() {
        if (getIntent().hasExtra("ICON")) {
            String sNamaMakanan = getIntent().getStringExtra("NAMA_MAKANAN");
            String sDeskripsi = getIntent().getStringExtra("DESKRIPSI_MAKANAN");
            int id = getIntent().getIntExtra("ICON", 0);

            ItemData item = new ItemData();
            item.namaMakanan = sNamaMakanan;
            item.deskripsi = sDeskripsi;
            item.foto = IconData.iconDrawer[id];
            itemValues.add(item);
            itemAdapter.notifyDataSetChanged();
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ItemData>>() {}.getType();
        itemValues = gson.fromJson(json, type);

        if (itemValues == null) {
            itemValues = new ArrayList<>();
            itemValues.addAll(FoodData.getListData());
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(itemValues);
        editor.putString("task list", json);
        editor.apply();
    }

    public void chooseIcon(View view) {
        saveData();
        boolean filled = true;
        if (TextUtils.isEmpty(inputNamaMakanan.getText())) {
            inputNamaMakanan.setError("Tulis nama makanan");
            filled = false;
        }

        if (TextUtils.isEmpty(inputDeskripsi.getText())) {
            inputDeskripsi.setError("Tulis deksripsi makanan");
            filled = false;
        }

        if (filled) {
            ItemData item = new ItemData();
            String sNamaMakanan = inputNamaMakanan.getText().toString();
            String sDeskripsi = inputDeskripsi.getText().toString();
            item.namaMakanan = sNamaMakanan;
            item.deskripsi = sDeskripsi;
            Intent intent = new Intent(this, ChooseIcon.class);
            intent.putExtra("NAMA_MAKANAN", item.namaMakanan);
            intent.putExtra("DESKRIPSI_MAKANAN", item.deskripsi);
            startActivityForResult(intent, 1);
        }

    }
}
