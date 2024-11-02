package com.example.estatemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private PropertyAdapter1 propertyAdapter;
    private final List<Apartment> propertyList = new ArrayList<>();
    private RecyclerView recyclerViewProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerViewProperties = findViewById(R.id.recyclerViewProperties);
        recyclerViewProperties.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        propertyAdapter = new PropertyAdapter1(propertyList);
        recyclerViewProperties.setAdapter(propertyAdapter);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(HomePage.this, SignIn.class);
            Toast.makeText(this, "The error User is not logged ", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "The User is logged ", Toast.LENGTH_SHORT).show();
            fetchDataFromFirestore();
        }
    }

    private void fetchDataFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Apartment")
                .limit(10)
                .get()
                .addOnSuccessListener(this::onSuccess)
                .addOnFailureListener(e -> Log.w("HomePage", "Error fetching data", e));
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        propertyList.clear();
        for (DocumentSnapshot document : queryDocumentSnapshots) {
            Double price = document.getDouble("price") != null ? document.getDouble("price") : 20.3;
            String imageURL = document.getString("imageURL");
            Apartment property = new Apartment(price, imageURL);
            propertyList.add(property);
        }
        propertyAdapter.notifyDataSetChanged();
    }
}

