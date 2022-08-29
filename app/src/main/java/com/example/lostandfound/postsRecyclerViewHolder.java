package com.example.lostandfound;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class postsRecyclerViewHolder extends RecyclerView.ViewHolder {
    protected TextView postItemName;
    protected TextView postLastSeenLocation;
    protected TextView postDescription;
    Context mContext;
    ConstraintLayout parentLayout;

    public postsRecyclerViewHolder(View postView) {
        super(postView);
        mContext = postView.getContext();
        postItemName = postView.findViewById(R.id.postItemNameTextView);
        postDescription = postView.findViewById(R.id.descriptionTextView);
        postLastSeenLocation = postView.findViewById(R.id.lastSeenLocationTextView);
        parentLayout = postView.findViewById(R.id.parentLayout);

    }
}
