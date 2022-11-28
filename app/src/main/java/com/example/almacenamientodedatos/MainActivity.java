package com.example.almacenamientodedatos;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.almacenamientodedatos.Adapter.Adapter;
import com.example.almacenamientodedatos.modelo.Personal;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_add_frg;
    RecyclerView mRecycler;
    Adapter mAdapter;
    FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recycleViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("Personal");

        FirestoreRecyclerOptions<Personal> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Personal>().setQuery(query, Personal.class).build();

        mAdapter = new Adapter(firestoreRecyclerOptions, this);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        btn_add = findViewById(R.id.btn_add);
        btn_add_frg = findViewById(R.id.btn_add_frg);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreatePersonalActivity.class));
            }
        });

        btn_add_frg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Create_Fragment fm = new Create_Fragment();
                fm.show(getSupportFragmentManager(), "Navegar a Fragment");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}