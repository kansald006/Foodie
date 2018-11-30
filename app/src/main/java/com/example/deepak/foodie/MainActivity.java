package com.example.deepak.foodie;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.FirestoreClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore fb;
    private DatabaseReference mDatabase;
    RecyclerView recyclerView;
    EditText editText;
    ArrayList<Food> list;
    CustomAdapter customAdapter;
    Food food;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb=FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
      // list.add(new Food("bread","1","140"));
        editText=findViewById(R.id.edit);
        customAdapter= new CustomAdapter(list);
        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                customAdapter.filter(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getfood();

    }
    public void getfood() {
        list = new ArrayList<>();
        db.collection("Food").get().addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                         food = document.toObject(Food.class);
                        Log.i("users",""+food.toString());
                        list.add(food);
                        customAdapter = new CustomAdapter(list);
                        recyclerView.setAdapter(customAdapter);

                    }
                } else {

                }

            }
        });
}}
