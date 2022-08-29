package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.example.lostandfound.itemsRecyclerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class homeActivity extends AppCompatActivity{
    RecyclerView itemsRecycler;
    itemsRecyclerAdapter itemsAdapter;
    BottomNavigationView navigationBar;
    ArrayList<lostItem> lostItems = new ArrayList<lostItem>();;
    ArrayList<lostItem> itemsSortedByTime;
    FirebaseFirestore fireStore;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fireStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("843476618997-jcpcg1ivathbnoead2fgn7mitjl7a00p.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        itemsRecycler = findViewById(R.id.itemsRecyclerView);
        navigationBar = findViewById(R.id.navigationView);
        navigationBar.setSelectedItemId(R.id.firstFragment);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.firstFragment: // home
                        return true;
                    case R.id.secondFragment: // post board
                        startActivity(new Intent(homeActivity.this, postBoardActivity.class));
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

        CollectionReference lostItemRef= fireStore.collection("Lost Items");
        fireStore.collection("Lost Items").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot ds : task.getResult()) {
                        lostItem thisLostItem = ds.toObject(lostItem.class);
                        lostItems.add(thisLostItem);
                    }
                    sortItemByTime();
                    itemsAdapter = new itemsRecyclerAdapter(lostItems, homeActivity.this);
                    itemsRecycler.setAdapter(itemsAdapter);
                    itemsRecycler.setLayoutManager(new LinearLayoutManager(homeActivity.this, RecyclerView.HORIZONTAL, false));
                }
            }
        });
    }
    private void sortItemByTime() {
        Collections.sort(lostItems, new Comparator<lostItem>() {
            public int compare(lostItem item1, lostItem item2) {
                return item2.getDateTime().compareTo(item1.getDateTime());
            }
        });
    }
    public void onClickValuables(View v){
        Intent intent = new Intent(this, itemListActivity.class);
        intent.putExtra("Type", "Valuables");
        startActivity(intent);
        // let the item list activity know what type of items to get from firebase and display
    }
    public void onClickNonValuables(View v){
        Intent intent = new Intent(this, moreTypesActivity.class);
        startActivity(intent);
    }
    public void onClickLogOut(View v){
        firebaseAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(homeActivity.this, loginActivity.class));
                    }
                });
    }


}