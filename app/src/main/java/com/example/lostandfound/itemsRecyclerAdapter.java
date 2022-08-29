package com.example.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostandfound.Activities.ItemProfileActivity;
import com.example.lostandfound.classes.lostItem;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;

public class itemsRecyclerAdapter extends RecyclerView.Adapter<itemsRecyclerViewHolder> {

    private ArrayList<lostItem> lostItemList;
    private Context mContext;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    public itemsRecyclerAdapter(ArrayList<lostItem> lostItemList, Context mContext){
        this.lostItemList=lostItemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public itemsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_view,parent,false);
        itemsRecyclerViewHolder itemViewHolder = new itemsRecyclerViewHolder(itemView);
        mContext = parent.getContext();
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull itemsRecyclerViewHolder holder, int position) {
        StorageReference photoRef = storageRef.child("myImages").child(lostItemList.get(position).getUUID()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        photoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.itemPhoto.setImageBitmap(bmp);
            }
        });
        holder.itemName.setText(lostItemList.get(position).getDescription());
        try{
            if(!(lostItemList.get(position).getOwner().equals(null))){
                holder.ownerName.setText("Owner: "+lostItemList.get(position).getOwner());
            }
        }
        catch(Exception E){

        }
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ItemProfileActivity.class);
                intent.putExtra("Lost Item", (Serializable) lostItemList.get(position));
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return lostItemList.size();
    }
}
