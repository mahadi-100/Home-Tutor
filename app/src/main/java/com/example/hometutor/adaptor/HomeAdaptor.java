package com.example.hometutor.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hometutor.Classes.FirebaseInfo;
import com.example.hometutor.R;

import java.util.ArrayList;

public class HomeAdaptor extends RecyclerView.Adapter<HomeAdaptor.ViewHolder>{
    ArrayList<FirebaseInfo> list;
    Context context;

    public HomeAdaptor(ArrayList<FirebaseInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recycle_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirebaseInfo infoModel = list.get(position);

        holder.name.setText(infoModel.getName());
        holder.age.setText(infoModel.getAge());
        holder.address.setText(infoModel.getAddress());

        holder.image.setImageResource(infoModel.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, age, address;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.home_name);
            age = itemView.findViewById(R.id.home_age);
            address = itemView.findViewById(R.id.home_address);

            image = itemView.findViewById(R.id.home_image);
        }
    }
}
