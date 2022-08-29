package com.example.lostandfound.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.example.lostandfound.classes.post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class postProfileActivity extends AppCompatActivity {

    FirebaseFirestore fireStore;
    FirebaseAuth mAuth;


    post thisPost;
    TextView postItemName;
    TextView postLastSeenLocation;
    TextView postDescription;
    EditText postFoundLocationEditText;
    Button contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_profile);
        fireStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        postItemName = findViewById(R.id.postItemName);
        postLastSeenLocation = findViewById(R.id.postLastSeenLocation);
        postDescription = findViewById(R.id.postDescription);
        contactButton = findViewById(R.id.contactOwnerButton);
        postFoundLocationEditText = findViewById(R.id.postFoundLocationEditText);
        thisPost = (post) getIntent().getSerializableExtra("post");
        postItemName.setText("Post Item: "+thisPost.getItemName());
        postLastSeenLocation.setText("Last Seen Location: "+thisPost.getLastSeenLocation());
        postDescription.setText(thisPost.getDescription());
    }
    public void onContact (View V){
        if(postFoundLocationEditText.getText().toString().equals("")){
            Toast.makeText(postProfileActivity.this, "Please fill in the location before you contact the owner", Toast.LENGTH_SHORT).show();
            return;
        }
        thisPost.setLocation(postFoundLocationEditText.getText().toString());
        fireStore.collection("Post").document(thisPost.getUUID()).set(thisPost);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+thisPost.getOwnerEmail())); // find a way to get that user email with uid
        intent.putExtra(Intent.EXTRA_SUBJECT,"Lost Item Found");
        intent.putExtra(Intent.EXTRA_TEXT,"Your lost item is at: "+postFoundLocationEditText.getText().toString());
        startActivity(intent);
        contactButton.setVisibility(View.GONE);
    }
}