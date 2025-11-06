package com.example.eventease;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewEntrantsActivity extends AppCompatActivity {

    private ListView listSelected, listNotSelected, listCancelled;
    private Button btnRemoveCancelled;
    private DatabaseReference dbRef;

    private List<Entrant> selectedList = new ArrayList<>();
    private List<Entrant> notSelectedList = new ArrayList<>();
    private List<Entrant> cancelledList = new ArrayList<>();

    private EntrantAdapter selectedAdapter;
    private EntrantAdapter notSelectedAdapter;
    private EntrantAdapter cancelledAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewentrants);

        listSelected = findViewById(R.id.recyclerSelected);
        listNotSelected = findViewById(R.id.recyclerNotSelected);
        listCancelled = findViewById(R.id.recyclerCancelled);
        btnRemoveCancelled = findViewById(R.id.btnRemCancelledEntrants);

        dbRef = FirebaseDatabase.getInstance().getReference("entrants");

        selectedAdapter = new EntrantAdapter(this, selectedList);
        notSelectedAdapter = new EntrantAdapter(this, notSelectedList);
        cancelledAdapter = new EntrantAdapter(this, cancelledList);

        listSelected.setAdapter(selectedAdapter);
        listNotSelected.setAdapter(notSelectedAdapter);
        listCancelled.setAdapter(cancelledAdapter);

        loadEntrantsFromFirebase();

        btnRemoveCancelled.setOnClickListener(v -> removeCancelledEntrants());
    }

    private void loadEntrantsFromFirebase() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                selectedList.clear();
                notSelectedList.clear();
                cancelledList.clear();

                for (DataSnapshot entrantSnap : snapshot.getChildren()) {
                    Entrant entrant = entrantSnap.getValue(Entrant.class);
                    if (entrant != null) {
                        switch (entrant.getStatus()) {
                            case "selected":
                                selectedList.add(entrant);
                                break;
                            case "not_selected":
                                notSelectedList.add(entrant);
                                break;
                            case "cancelled":
                                cancelledList.add(entrant);
                                break;
                        }
                    }
                }

                selectedAdapter.notifyDataSetChanged();
                notSelectedAdapter.notifyDataSetChanged();
                cancelledAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewEntrantsActivity.this, "Failed to load entrants.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void removeCancelledEntrants() {
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot entrantSnap : snapshot.getChildren()) {
                    Entrant entrant = entrantSnap.getValue(Entrant.class);
                    if (entrant != null && "cancelled".equals(entrant.getStatus())) {
                        entrantSnap.getRef().removeValue();
                    }
                }
                Toast.makeText(ViewEntrantsActivity.this, "Cancelled entrants removed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
