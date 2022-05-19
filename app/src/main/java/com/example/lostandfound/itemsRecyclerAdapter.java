package com.example.lostandfound;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostandfound.classes.lostItem;

import java.util.ArrayList;

public class itemsRecyclerAdapter extends RecyclerView.Adapter<itemsRecyclerViewHolder> {

    private ArrayList<lostItem> lostItemList;

    public itemsRecyclerAdapter(ArrayList<lostItem> lostItemList){
        this.lostItemList=lostItemList
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

    }

    @NonNull
    @Override
    public itemsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull itemsRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
