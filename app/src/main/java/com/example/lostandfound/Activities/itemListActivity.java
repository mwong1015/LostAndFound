package com.example.lostandfound.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lostandfound.R;

public class itemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        String type = getIntent().getStringExtra("Type");
        if (type.equals("valuables")){

        }
        else if(type.equals("non-valuables")){

        }
        else if(type.equals("uniform")){

        }
        else if(type.equals("books")){

        }
        else if(type.equals("waterBottles")){

        }
        else if(type.equals("miscellaneous")){

        }
    }
}