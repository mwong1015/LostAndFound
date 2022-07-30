package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.lostandfound.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class postBoardActivity extends AppCompatActivity {
    Button addPostButton;
    BottomNavigationView navigationBar;
    FirebaseFirestore fireStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_board);
        fireStore = FirebaseFirestore.getInstance();
        addPostButton = findViewById(R.id.addPostButton);
        navigationBar = findViewById(R.id.navigationView);
        navigationBar.setSelectedItemId(R.id.secondFragment);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.firstFragment: // home
                        startActivity(new Intent(postBoardActivity.this, homeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.secondFragment: // post board
                        return true;
                    case R.id.thirdFragment: // add lost item
                        startActivity(new Intent(postBoardActivity.this, addLostItemActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void onClickAddPost(View v){
        Intent intent = new Intent(postBoardActivity.this, addPostActivity.class);
        startActivity(intent);
    }
}