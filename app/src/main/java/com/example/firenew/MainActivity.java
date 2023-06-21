package com.example.firenew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mFier;
    FirebaseDatabase data;
    DatabaseReference db;
    TextView user;
    List<Note> itemLIst ;
    Adapter adapt;

    Button logout,plus;
    FirebaseUser currentU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mFier = FirebaseAuth.getInstance();
        data = FirebaseDatabase.getInstance();
        db = data.getReference(Note.class.getSimpleName());
        logout =findViewById(R.id.logout);
        plus = findViewById(R.id.plus);
        RecyclerView re = findViewById(R.id.recy);
        user = findViewById(R.id.Uname);
        user.setText(mFier.getCurrentUser().getEmail());
        load();
        re.setLayoutManager(new LinearLayoutManager(this));
        adapt = new Adapter(isi);
        re.setAdapter(adapt);




        logout.setOnClickListener(this);
        plus.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentU = mFier.getCurrentUser();
        user.setText(currentU.getEmail());

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==logout.getId()){
            mFier.signOut();
            Intent lo = new Intent(MainActivity.this,login.class);
            lo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(lo);
        }else if (view.getId()==plus.getId()){
            Intent lo = new Intent(MainActivity.this,plus.class);
            startActivity(lo);
        }
    }
    List<Note> isi = new ArrayList<>();
    public void load(){
        currentU = mFier.getCurrentUser();
        String uid = currentU.getUid();
        String path = Note.class.getSimpleName()+"/"+uid;
        if(data.getReference(path)==null){
        data.getReference().child(uid).setValue("");
        }

        db =data.getReference(path);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isi.clear();
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        Note note= snap.getValue(Note.class);
                        note.setKey(snap.getKey());
                        isi.add(note);

                    }
                    adapt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Failed to load data",
                        Toast.LENGTH_SHORT ).show();
            }
        });

    }
}