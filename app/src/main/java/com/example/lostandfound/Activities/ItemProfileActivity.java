package com.example.lostandfound.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ItemProfileActivity extends AppCompatActivity {
    private FirebaseStorage storage;
    private StorageReference storageRef;
    ImageView lostItemImageView;
    TextView lostItemTypeTextView;
    TextView lostItemLocationTextView;
    TextView descriptionTextView;
    lostItem thisLostItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        setContentView(R.layout.activity_item_profile);
        lostItemImageView = findViewById(R.id.lostItemImageView);
        lostItemTypeTextView = findViewById(R.id.lostItemTypeTextView);
        lostItemLocationTextView = findViewById(R.id.lostItemLocationTextView);
        descriptionTextView = findViewById(R.id.lostItemDescriptionTextVIew);
        thisLostItem = (lostItem) getIntent().getSerializableExtra("Lost Item");
        lostItemTypeTextView.setText("Type: "+thisLostItem.getType());
        lostItemLocationTextView.setText("Location: "+thisLostItem.getLocation());
        descriptionTextView.setText("Description: " +thisLostItem.getDescription());

        StorageReference photoRef = storageRef.child("myImages").child(thisLostItem.getUUID()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        photoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                lostItemImageView.setImageBitmap(bmp);
            }
        });

    }
}