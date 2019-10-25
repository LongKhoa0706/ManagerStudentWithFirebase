package com.example.managerstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.managerstudent.Adapter.XemDsAdapter;
import com.example.managerstudent.Model.Lop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class XemDsActivity extends AppCompatActivity {
    private ArrayList<Lop> arrClass = new ArrayList<>();
    private RecyclerView recyclerView;
    private XemDsAdapter xemDsAdapter;
    DatabaseReference noteRoot = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_ds);
        anhXa();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataClass = dataSnapshot.child("Class");
                for (DataSnapshot valueClass : dataClass.getChildren()) {
                    Lop lop =  valueClass.getValue(Lop.class);

                    arrClass.add(lop);
                    xemDsAdapter = new XemDsAdapter(getApplicationContext(),R.layout.custom_row_class,arrClass);
                    recyclerView.setAdapter(xemDsAdapter);
                    xemDsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        noteRoot.addListenerForSingleValueEvent(eventListener);

    }

    private void anhXa() {
        recyclerView = findViewById(R.id.recycleviewclass);

    }
}
