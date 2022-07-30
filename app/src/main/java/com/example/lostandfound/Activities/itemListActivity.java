package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.example.lostandfound.itemsRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class itemListActivity extends AppCompatActivity {

    FirebaseFirestore fireStore;
    ArrayList<lostItem> lostItems;
    ArrayList<lostItem> itemsSortedByTime;
    itemsRecyclerAdapter itemsAdapter;
    RecyclerView itemsRecycler;
    TextView typeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        fireStore = FirebaseFirestore.getInstance();

        typeName = findViewById(R.id.typeTextView);

        itemsRecycler = findViewById(R.id.itemsSortByTypeRecyclerView);

        CollectionReference lostItemRef= fireStore.collection("Lost Items");
        lostItems = new ArrayList<lostItem>();
        String type = getIntent().getStringExtra("Type");
        typeName.setText(type);
        fireStore.collection("Lost Items").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot ds : task.getResult()) {
                        lostItem thisLostItem = ds.toObject(lostItem.class);
                        if(thisLostItem.getType().equals(type))
                            lostItems.add(thisLostItem);
                            sortItemByTime();
                            displayItems();
                    }
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
    private void displayItems(){
        itemsAdapter = new itemsRecyclerAdapter(lostItems, itemListActivity.this);
        itemsRecycler.setAdapter(itemsAdapter);
        itemsRecycler.setLayoutManager(new LinearLayoutManager(itemListActivity.this, RecyclerView.VERTICAL, false));
    }
}