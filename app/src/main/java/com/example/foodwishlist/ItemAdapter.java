package com.example.foodwishlist;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ItemData> values;

    public ItemAdapter(Context context, ArrayList<ItemData> values) {
        this.context = context;
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        final ItemData data = values.get(position);
        holder.viewNamaMakanan.setText(data.namaMakanan);
        holder.viewDeskripsi.setText(data.deskripsi);
        Glide.with(holder.itemView.getContext())
                .load(data.getFoto())
                .format(DecodeFormat.PREFER_ARGB_8888)
                .apply(new RequestOptions().override(50, 50))
                .into(holder.viewFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, data.namaMakanan
                        + " itu enak lho!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        new MaterialAlertDialogBuilder(context)
                                .setTitle("Hapus " + data.namaMakanan + " ?")
                                .setMessage("Apakah anda yakin akan menghapus " + data.namaMakanan + " dari wishlist anda?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        values.remove(data);
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Tidak", null)
                                .show();
                        return false;
                    }

                }
        );

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewNamaMakanan;
        TextView viewDeskripsi;
        ImageView viewFoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNamaMakanan = itemView.findViewById(R.id.namaMakanan);
            viewDeskripsi = itemView.findViewById(R.id.deskripsi);
            viewFoto = itemView.findViewById(R.id.img_item);
        }
    }
}
