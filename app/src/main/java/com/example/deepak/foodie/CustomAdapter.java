package com.example.deepak.foodie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    ArrayList<Food> list,templist;

    public CustomAdapter(ArrayList<Food> list) {
        this.list = list;
        templist= new ArrayList<>();
        templist.addAll(list);
    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int i) {
        Food user= list.get(i);
        holder.name.setText(user.Food);
        holder.size.setText(user.Size);
        holder.calories.setText(user.Calories);


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public void filter(String searchQuery) {
        list.clear();
        if (searchQuery.length() > 0) {
            for (Food item : templist) {
                if (item.Food.toLowerCase().contains(searchQuery.toLowerCase())||(item.Size.toLowerCase().contains(searchQuery.toLowerCase()))) {
                       list.add(item);
                }
            }
        }else{
            list.addAll(templist);
        }
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name,size,calories;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Name);
            size=itemView.findViewById(R.id.Size);
            calories=itemView.findViewById(R.id.Calories);

        }
    }
}
