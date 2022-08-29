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
import android.widget.Button;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.example.lostandfound.classes.post;
import com.example.lostandfound.itemsRecyclerAdapter;
import com.example.lostandfound.postsRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class postBoardActivity extends AppCompatActivity {
    Button addPostButton;
    BottomNavigationView navigationBar;
    postsRecyclerAdapter postAdapter;
    FirebaseFirestore fireStore;
    ArrayList <post> postList;
    RecyclerView postRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_board);
        fireStore = FirebaseFirestore.getInstance();
        postRecycler = findViewById(R.id.postRecyclerView);
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
        postList = new ArrayList<>();
        fireStore.collection("Post").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot ds : task.getResult()) {
                        post thisPost= ds.toObject(post.class);
                        postList.add(thisPost);
                    }
                    sortPostByTime();
                    postAdapter = new postsRecyclerAdapter(postList, postBoardActivity.this);
                    postRecycler.setAdapter(postAdapter);
                    postRecycler.setLayoutManager(new LinearLayoutManager(postBoardActivity.this, RecyclerView.VERTICAL, false));
                }
            }
        });
    }
    private void sortPostByTime() {
        Collections.sort(postList, new Comparator<post>() {
            public int compare(post item1, post item2) {
                return item2.getDateTime().compareTo(item1.getDateTime());
            }
        });
    }
    public void onClickAddPost(View v){
        Intent intent = new Intent(postBoardActivity.this, addPostActivity.class);
        startActivity(intent);
    }


}