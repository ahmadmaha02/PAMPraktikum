package com.example.firenew;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class update extends AppCompatActivity implements View.OnClickListener {
    EditText titel,isi;
    Button bek,updet;
    DatabaseReference der;
    FirebaseDatabase db;
    String atas,kunci, bawah;
    FirebaseAuth mAuth;
    FirebaseUser curret;
    String link;
    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        titel = findViewById(R.id.titel);
        isi = findViewById(R.id.des);
        bek = findViewById(R.id.beky);
        updet = findViewById(R.id.updet);
        db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        curret = mAuth.getCurrentUser();
        der = db.getReference(Note.class.getSimpleName()).child(curret.getUid());



        kunci = getIntent().getStringExtra("key");
        atas = getIntent().getStringExtra("judule");
        bawah = getIntent().getStringExtra("deskripsi");
        link = getIntent().getStringExtra("imgUrl");

        isi.setText(bawah);
        titel.setText(atas);
        bek.setOnClickListener(this);
        updet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(bek.getId()==view.getId()){
            finish();
        }else if(updet.getId()==view.getId()){
            Note up = new Note(link,titel.getText().toString(),isi.getText().toString());
            der.child(kunci).setValue(up);
            finish();
        }

    }


}