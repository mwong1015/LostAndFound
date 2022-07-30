package com.example.lostandfound.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lostandfound.R;

public class moreTypesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_types);
    }

    public void onClickBooks(View v){
        Intent intent = new Intent(moreTypesActivity.this, itemListActivity.class);
        intent.putExtra("Type", "Books");
        startActivity(intent);
    }
    public void onClickUniform(View v){
        Intent intent = new Intent(moreTypesActivity.this, itemListActivity.class);
        intent.putExtra("Type", "Uniform");
        startActivity(intent);
    }
    public void onClickWaterBottles(View v){
        Intent intent = new Intent(moreTypesActivity.this, itemListActivity.class);
        intent.putExtra("Type", "Water Bottles");
        startActivity(intent);
    }


    public void onClickMiscellaneous(View v){
        Intent intent = new Intent(moreTypesActivity.this, itemListActivity.class);
        intent.putExtra("Type", "Miscellaneous");
        startActivity(intent);
    }
}
