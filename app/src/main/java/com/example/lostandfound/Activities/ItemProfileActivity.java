package com.example.lostandfound.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ItemProfileActivity extends AppCompatActivity {
    FirebaseFirestore fireStore;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    ImageView lostItemImageView;
    TextView lostItemTypeTextView;
    TextView lostItemLocationTextView;
    TextView descriptionTextView;
    lostItem thisLostItem;
    Button claimButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        setContentView(R.layout.activity_item_profile);
        fireStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        lostItemImageView = findViewById(R.id.lostItemImageView);
        lostItemTypeTextView = findViewById(R.id.lostItemTypeTextView);
        lostItemLocationTextView = findViewById(R.id.lostItemLocationTextView);
        descriptionTextView = findViewById(R.id.lostItemDescriptionTextVIew);
        claimButton = findViewById(R.id.claimButton);
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
        if(thisLostItem.getClaimerID().equals(currentUser.getUid())){
            claimButton.setText("This is not my item");
        }
        else if(!(thisLostItem.getClaimerID().equals("")) && !(thisLostItem.getClaimerID().equals(currentUser.getUid()))){ // someone other than the user claimed the item
            claimButton.setText("This item has been claimed by someone else, contact us if this item belongs to you");
        }
    }
    public void onClick(View v){
        if(thisLostItem.getClaimerID() == ""){ // user clicked claim
            thisLostItem.setClaimerID(currentUser.getUid());
            fireStore.collection("Lost Items").document(thisLostItem.getUUID()).set(thisLostItem);
            Toast.makeText(getApplicationContext(), "You have claimed this item", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, homeActivity.class));
        }
        else if(thisLostItem.getClaimerID().equals(currentUser.getUid())){ // claimed user clicked unclaim
            thisLostItem.setClaimerID("");
            fireStore.collection("Lost Items").document(thisLostItem.getUUID()).set(thisLostItem);
            Toast.makeText(getApplicationContext(), "You have unclaimed this item", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, homeActivity.class));
        }
        else{ // another user clicking contact us button: use firebase messaging and send notification to me
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:marcow2023@student.cis.edu.hk"));
            intent.putExtra(Intent.EXTRA_SUBJECT,"Lost and Found app issues");
            startActivity(intent);
        }
    }
}