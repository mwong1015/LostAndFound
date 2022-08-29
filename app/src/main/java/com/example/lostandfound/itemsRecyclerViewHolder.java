package com.example.lostandfound;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class itemsRecyclerViewHolder extends RecyclerView.ViewHolder {
    protected ImageView itemPhoto;
    protected TextView itemName;
    protected TextView ownerName;
    Context mContext;
    ConstraintLayout parentLayout;

    public itemsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ownerName = itemView.findViewById(R.id.ownerNameTextView);
        itemPhoto = itemView.findViewById(R.id.itemPhoto);
        itemName = itemView.findViewById(R.id.itemNameTextView);
        parentLayout = itemView.findViewById(R.id.parentLayout);
    }
}
