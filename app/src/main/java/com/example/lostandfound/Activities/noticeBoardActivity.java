package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.lostandfound.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class noticeBoardActivity extends AppCompatActivity {
    Button addPostButton;
    BottomNavigationView navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        navigationBar = findViewById(R.id.navigationView);
        navigationBar.setSelectedItemId(R.id.secondFragment);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.firstFragment: // home
                        startActivity(new Intent(noticeBoardActivity.this,homeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.secondFragment: // post board
                        return true;
                    case R.id.thirdFragment: // add lost item
                        startActivity(new Intent(noticeBoardActivity.this,addLostItemActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}