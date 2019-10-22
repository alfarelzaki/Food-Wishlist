package com.example.foodwishlist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Icon> values;
    private String extraNama;
    private String extraDeskripsi;


    public IconAdapter(Context context, ArrayList<Icon> values, String extraNama, String extraDeskripsi) {
        this.context = context;
        this.values = values;
        this.extraNama = extraNama;
        this.extraDeskripsi = extraDeskripsi;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.icon_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Icon data = values.get(position);
        Glide.with(holder.itemView.getContext())
                .load(data.getIcon())
                .format(DecodeFormat.PREFER_ARGB_8888)
                .apply(new RequestOptions().override(50, 50))
                .into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity.class);
                intent.putExtra("ICON", data.getId());
                intent.putExtra("NAMA_MAKANAN", extraNama);
                intent.putExtra("DESKRIPSI_MAKANAN", extraDeskripsi);

                Log.d("cek nama", Integer.toString(data.getId()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.img_item);
        }
    }
}
