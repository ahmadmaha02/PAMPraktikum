package com.example.firenew;

import static org.junit.Assert.assertEquals;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        scenario =activityScenarioRule.getScenario();
    }
    public ActivityScenario scenario;

    @After
    public void tearDown() throws Exception {
    }

    private FirebaseAuth mFier;
    FirebaseDatabase data;
    DatabaseReference db;
    private FirebaseUser currentU;
    private DatabaseReference reference;
    private ValueEventListener valueEventListener;
    private DataSnapshot dataSnapshot;

    List<Note> isi = new ArrayList<>();
    int jumlah;
    @Test
    public void load() {
        data = FirebaseDatabase.getInstance();

        mFier = FirebaseAuth.getInstance();
        currentU = mFier.getCurrentUser();
        String uid = "lJa099E31PYs5lFbgsPnAZ38FBh2";
        String path = Note.class.getSimpleName()+"/"+uid;
        if(data.getReference(path)==null) {
            data.getReference().child(uid).setValue("");
        }

        db =data.getReference(path);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isi.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Note note = snap.getValue(Note.class);
                    note.setKey(snap.getKey());
                    isi.add(note);
                }
                jumlah = isi.size();

                assertEquals(3, jumlah);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Test
    public void loadWOInput() {
        data = FirebaseDatabase.getInstance();

        mFier = FirebaseAuth.getInstance();
        currentU = mFier.getCurrentUser();
        String uid = "K4OSFOGYkQgo8rUH4g52miHZm323";
        String path = Note.class.getSimpleName()+"/"+uid;
        if(data.getReference(path)==null) {
            data.getReference().child(uid).setValue("");
        }

        db =data.getReference(path);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isi.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Note note = snap.getValue(Note.class);
                    note.setKey(snap.getKey());
                    isi.add(note);
                }

                jumlah = isi.size();

                assertEquals(0, jumlah);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
