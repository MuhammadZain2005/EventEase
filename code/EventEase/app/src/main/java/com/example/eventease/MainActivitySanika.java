package com.example.eventease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenEntrants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        Log.d("FirebaseTest", "FirebaseApp initialized: " + (FirebaseApp.getApps(this).size() > 0));

        setContentView(R.layout.activity_main);
        btnOpenEntrants = findViewById(R.id.btnOpenEntrants);

        btnOpenEntrants.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewEntrantsActivity.class);
            startActivity(intent);
        });
    }
}
