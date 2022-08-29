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
import com.example.lostandfound.Activities.postProfileActivity;
import com.example.lostandfound.classes.lostItem;
import com.example.lostandfound.classes.post;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;

public class postsRecyclerAdapter extends RecyclerView.Adapter<postsRecyclerViewHolder> {
    private ArrayList<post> postsList;
    private Context mContext;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    public postsRecyclerAdapter(ArrayList<post> postsList, Context mContext){
        this.postsList= postsList;
        this.mContext = mContext;
    }

    @NonNull
    public postsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        View postView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view,parent,false);
        postsRecyclerViewHolder postsViewHolder = new postsRecyclerViewHolder(postView);
        mContext = parent.getContext();
        return postsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull postsRecyclerViewHolder holder, int position) {
        holder.postItemName.setText("Missing: "+postsList.get(position).getItemName());
        holder.postDescription.setText(postsList.get(position).getDescription());
        holder.postLastSeenLocation.setText("Last Seen Location: "+postsList.get(position).getLastSeenLocation());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, postProfileActivity.class);
                intent.putExtra("post", (Serializable) postsList.get(position));
                mContext.startActivity(intent);
            }
        });
    }
    public int getItemCount() {
        return postsList.size();
    }
}

