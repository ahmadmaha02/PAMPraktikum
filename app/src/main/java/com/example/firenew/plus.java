package com.example.firenew;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Map;

public class plus extends AppCompatActivity implements View.OnClickListener {
    EditText judul,desc;
    Button back,add,uplos;
    FirebaseAuth auth;
    FirebaseUser current;
    FirebaseDatabase db;
    DatabaseReference bd;
    Note note ;
    StorageReference stor;
    ActivityResultLauncher<Intent> filePickerLauncher;
    Uri fileUri;
    String Link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        db = FirebaseDatabase.getInstance();
        bd = db.getReference(Note.class.getSimpleName());
        auth = FirebaseAuth.getInstance();
        judul = findViewById(R.id.judul);
        desc = findViewById(R.id.desc);
        back = findViewById(R.id.bek);
        add = findViewById(R.id.add);
        uplos = findViewById(R.id.GAmbar);
        uplos.setOnClickListener(this);
        back.setOnClickListener(this);
        add.setOnClickListener(this);
        current = auth.getCurrentUser();
        stor = FirebaseStorage.getInstance().getReference();

        filePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            fileUri = data.getData();
                            if (fileUri != null) {
                                uploadFile(fileUri);
                            } else {
                                Log.e("plus", "Failed to retrieve file URI");
                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
    if(back.getId()==view.getId()){
        finish();
    }else if (add.getId()==view.getId()){
        if(fileUri == null){
            Toast.makeText(this, "Upload gambar dulu boss", Toast.LENGTH_SHORT).show();
            return;
        }
        String judule = judul.getText().toString().trim();
        String desv = desc.getText().toString().trim();
        add(judule,desv);

        finish();
    }else if(uplos.getId()==view.getId()){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        filePickerLauncher.launch(intent);
    }
    }
    public boolean cekform(){
        boolean result = true;
        if (TextUtils.isEmpty(judul.getText().toString())) {
            judul.setError("Required");
            result = false;
        } else {
            judul.setError(null);
        }
        if (TextUtils.isEmpty(desc.getText().toString())) {
            desc.setError("Required");
            result = false;
        } else {
            desc.setError(null);
        }
        return result;
    }
    String key;


    private void add(String judul , String desc){
        current = auth.getCurrentUser();
        DatabaseReference dr =  bd.child(current.getUid()).push();
        key = dr.getKey();
        note = new Note(Link,judul,desc);
        if(!cekform()) {
        return;
        }
           dr.setValue(note).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(plus.this, "Data added",
                            Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(plus.this, "Fail to add",
                            Toast.LENGTH_SHORT).show();
                }
            }
            );

    }

    private void uploadFile(Uri fileUri) {
        File file = new File(fileUri.getPath());
        StorageReference fileRef = stor.child("img/" +current.getUid()+"/"+ file.getName());
        // Upload file to Firebase Storage
        UploadTask uploadTask = fileRef.putFile(fileUri);
        // Register observers to listen for the upload progress or errors
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // File uploaded successfully
                Log.d("MainActivity", "File uploaded successfully");
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Link = uri.toString();
                        // Use the imageUrl as needed

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle unsuccessful uploads
                Log.e("MainActivity", "Failed to upload file: " + e.getMessage());
            }
        });
    }

}