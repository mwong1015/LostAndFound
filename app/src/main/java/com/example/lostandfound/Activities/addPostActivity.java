package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class addPostActivity extends AppCompatActivity {
    FirebaseFirestore fireStore;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Button submitPostButton;
    EditText lostItemNameEditText;
    EditText lastSeenLocationEditText;
    EditText lastSeenDescriptionEditText;
    String UUIDref;
    post newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        UUIDref = UUID.randomUUID().toString();
        fireStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        lostItemNameEditText = findViewById(R.id.lastSeenLostItemName);
        lastSeenLocationEditText = findViewById(R.id.lastSeenLocationEditText);
        lastSeenDescriptionEditText = findViewById(R.id.lastSeenDescriptionEditText);
        submitPostButton = findViewById(R.id.submitPostButton);
    }
    public void addPost(View v) {
        if ((lostItemNameEditText.getText().toString().equals("")) || (lastSeenLocationEditText.getText().toString().equals(""))|| (lastSeenDescriptionEditText.getText().toString().equals(""))) { // owner didn't fill in all the fields
            Toast.makeText(addPostActivity.this, "Please fill in all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Date dateNow = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y HH:mm");
            String dateString = dateForm.format(dateNow);
            newPost = new post(currentUser.getEmail(), lostItemNameEditText.getText().toString(), lastSeenLocationEditText.getText().toString(), lastSeenDescriptionEditText.getText().toString(), null, dateString, UUIDref);
            fireStore.collection("Post").document(UUIDref).set(newPost).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(addPostActivity.this, "Post added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addPostActivity.this, homeActivity.class));
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(addPostActivity.this, "Item add failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}