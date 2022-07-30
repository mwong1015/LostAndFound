package com.example.lostandfound.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.R;
import com.example.lostandfound.classes.lostItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class addLostItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String type;
    Spinner typeSpinner;
    EditText locationEditText;
    EditText descriptionEditText;
    TextView cameraTextView;
    Button cameraButton;
    Button confirmButton;
    FirebaseFirestore fireStore;
    BottomNavigationView navigationBar;

    public Uri mImageUri;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageTask mUploadTask;

    byte bb[];

    Long fileName;
    String UUIDref = UUID.randomUUID().toString();

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lost_item);

        fireStore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        typeSpinner = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(this);
        locationEditText = findViewById(R.id.lostItemLocationEditText);
        descriptionEditText = findViewById(R.id.lostItemDescriptionEditText);
        cameraTextView = findViewById(R.id.cameraTextView);
        cameraButton = findViewById(R.id.cameraButton);
        confirmButton = findViewById(R.id.confirmButton);


        navigationBar = findViewById(R.id.navigationView);
        navigationBar.setSelectedItemId(R.id.thirdFragment);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.firstFragment: // home
                        startActivity(new Intent(addLostItemActivity.this, homeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.secondFragment: // post board
                        startActivity(new Intent(addLostItemActivity.this, postBoardActivity.class));
                        overridePendingTransition(0, 0);
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

    public void addLostItem(View v) {
        Date dateNow = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y HH:mm");
        String dateString = dateForm.format(dateNow);
        lostItem item = new lostItem(type, locationEditText.getText().toString(), descriptionEditText.getText().toString(), UUIDref, "", dateString);
        uploadToFirebase(bb);
        fireStore.collection("Lost Items").document(UUIDref).set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(addLostItemActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(addLostItemActivity.this, homeActivity.class));
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addLostItemActivity.this, "Item add failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void takePhoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                onCaptureImageResult(data);
            }
        }
    }
    private void onCaptureImageResult(Intent data) {
        boolean success = true;
        try{
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            bb = bytes.toByteArray();
            String file = Base64.encodeToString(bb, Base64.DEFAULT);
        }catch(Exception e){
            success = false;
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Image capture failed", Toast.LENGTH_SHORT).show();
        }
        if (success) {
            Toast.makeText(getApplicationContext(), "Image capture Success", Toast.LENGTH_SHORT).show();
            cameraButton.setVisibility(View.GONE);
            cameraTextView.setText("Image captured successfully");
        }

    }

    private void uploadToFirebase(byte[] bb) {
        StorageReference sr = storageRef.child("myImages/" + UUIDref + ".jpg");
        sr.putBytes(bb).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Image upload failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
