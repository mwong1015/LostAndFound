package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.example.lostandfound.itemsRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;

public class homeActivity extends AppCompatActivity{
    RecyclerView itemsRecycler;
    Button refreshButton;
    itemsRecyclerAdapter itemsAdapter;
    BottomNavigationView navigationBar;


    ArrayList<lostItem> lostItems = new ArrayList<>();
    lostItem itemOne = new lostItem("this","is","kinda", "uuid lmao","");
    lostItem itemTwo = new lostItem("this","is","kinda", "uuid lmao2","");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        itemsRecycler = findViewById(R.id.itemsRecyclerView);
        refreshButton = findViewById(R.id.refreshButton);

        lostItems.add(itemOne);
        lostItems.add(itemTwo);

        itemsAdapter = new itemsRecyclerAdapter(lostItems, this);
        itemsRecycler.setAdapter(itemsAdapter);
        itemsRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        navigationBar = findViewById(R.id.navigationView);
        navigationBar.setSelectedItemId(R.id.firstFragment);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.firstFragment: // home
                        return true;
                    case R.id.secondFragment: // post board
                        startActivity(new Intent(homeActivity.this,noticeBoardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.thirdFragment: // add lost item
                        startActivity(new Intent(homeActivity.this,addLostItemActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void onClickValuables(View v){
        Intent intent = new Intent(homeActivity.this, itemListActivity.class);
        intent.putExtra("Type", "valuables");
        startActivity(intent);
        // let the item list activtiy know what type of items to get from firebase and display
    }
    public void onClickNonValuables(View v){
        Intent intent = new Intent(homeActivity.this, moreTypesActivity.class);
        startActivity(intent);
    }


}