package com.example.estatemap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button btnSend;
    private TextView tvSignIn;
    private FirebaseDatabase db;
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Database
        db = FirebaseDatabase.getInstance();
        root = db.getReference("Users"); // Change this to your desired path
        userName = findViewById(R.id.username);
        userEmail = findViewById(R.id.sign_up_email);
        userPassword = findViewById(R.id.sign_up_password);
        btnSend = findViewById(R.id.sign_up_btn);
        tvSignIn = findViewById(R.id.tv_sign_in); // Bind TextView here

        // Set click listener for the TextView
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent); // Start SignIn activity
            }
        });

        // Set click listener for the send button
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDB(view); // Call sendToDB when the button is clicked
            }
        });
    }

    public void sendToDB(View view) {
        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(SignUp.this, "Please add userName, Email, and Password.",
                    Toast.LENGTH_SHORT).show();
        } else {
            sendDatatoFirebase(name, email, password);
        }
    }

    private void sendDatatoFirebase(String name, String email, String password) {
        // Create a new instance of userInfo
        userInfo userInfo = new userInfo();

        // Set user info using setter methods
        userInfo.setUserName(name);
        userInfo.setUserEmail(email);
        userInfo.setPassword(password);

        // Use Firebase Authentication to create a user
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // User created successfully, now save userInfo to the database
                        String userId = root.push().getKey();
                        if (userId != null) {
                            root.child(userId).setValue(userInfo).addOnCompleteListener(databaseTask -> {
                                if (databaseTask.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignUp.this, "Failed to add data: " + databaseTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Failed to create user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
