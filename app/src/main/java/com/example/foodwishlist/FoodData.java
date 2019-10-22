package com.example.foodwishlist;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FoodData {

    private static int[] foto = {
        R.drawable.steak,
        R.drawable.rice,
        R.drawable.rice,
        R.drawable.steak

    };

    private static String[] namaMakanan = {
        "Rendang",
        "Nasi Goreng",
        "Nasi Rawon",
        "Sate"
    };

    private static String[] deskripsiMakanan = {
        "Makanan khas padang yang berbahan utama daging sapi",
        "Makanan khas Indonesia yang dibuat dengan menggoreng nasi yang dicampur dengan tomat, cabai, bawang putih, dsb",
        "Hidangan yang terbuat dari daging sapi rebus dari Jawa Timur",
        "Sate adalah daging yang ditusuk dan dimasak diatas bara"

    };

    static ArrayList<ItemData> getListData() {
        ArrayList<ItemData> list = new ArrayList<>();
        for (int i = 0; i<namaMakanan.length; i++) {
            ItemData item = new ItemData();
            item.setNamaMakanan(namaMakanan[i]);
            item.setDeskripsi(deskripsiMakanan[i]);
            item.setFoto(foto[i]);
            list.add(item);
        }
        return list;
    }


}
