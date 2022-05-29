package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class addLostItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String type;
    Spinner typeSpinner;
    EditText locationEditText;
    EditText descriptionEditText;
    FirebaseFirestore fireStore;
    BottomNavigationView navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lost_item);

        fireStore = FirebaseFirestore.getInstance();

        typeSpinner = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(this);
        locationEditText = findViewById(R.id.lostItemLocationEditText);
        descriptionEditText = findViewById(R.id.lostItemDescriptionEditText);

        navigationBar = findViewById(R.id.navigationView);
        navigationBar.setSelectedItemId(R.id.thirdFragment);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.firstFragment: // home
                        startActivity(new Intent(addLostItemActivity.this,homeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.secondFragment: // post board
                        startActivity(new Intent(addLostItemActivity.this,noticeBoardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.thirdFragment: // add lost item
                        return true;
                }
                return false;
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void addLostItem(View v){
        UUID newID= UUID.randomUUID();
        lostItem item = new lostItem(type, locationEditText.getText().toString(),descriptionEditText.getText().toString(), newID.toString(),"");
        fireStore.collection("Lost items").document(newID.toString()).set(item);
        Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,homeActivity.class));

        // task now: figure out why it doesn't add to firebase
    }
}