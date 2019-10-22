package com.example.foodwishlist;

import java.util.ArrayList;

public class IconData {
    private static int[] iconId = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9 , 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
    };

    public static int[] iconDrawer = {
            R.drawable.rice,
            R.drawable.steak,
            R.drawable.chicken,
            R.drawable.apple,
            R.drawable.bread,
            R.drawable.burger,
            R.drawable.cheese,
            R.drawable.coffe,
            R.drawable.cookie,
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.dumpling,
            R.drawable.french_fries,
            R.drawable.ice_cream,
            R.drawable.pancakes,
            R.drawable.pizza,
            R.drawable.pretzel,
            R.drawable.salad,
            R.drawable.sushi,
            R.drawable.sandwich

    };

    static ArrayList<Icon> getListData() {
        ArrayList<Icon> list = new ArrayList<>();
        for (int i = 0; i<iconId.length; i++) {
            Icon item = new Icon();
            item.setId(iconId[i]);
            item.setIcon(iconDrawer[i]);
            list.add(item);
        }
        return list;
    }
}
