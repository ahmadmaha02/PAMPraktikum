package com.example.firenew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Note> itemList;

    public Adapter(List<Note> itemLIst) {
    this.itemList = itemLIst;
    }

    public void setItemList(List<Note> itemList) {
        this.itemList = itemList;
    }
    DatabaseReference db;
    FirebaseDatabase data;
    FirebaseUser current;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.card,parent,false);
        ViewHolder isi = new ViewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder._nama.setText(itemList.get(position).getNama());
        holder._isi.setText(itemList.get(position).getIsi());
        Glide.with(holder.itemView.getContext())
                .load(itemList.get(position).getImgUrl())
                .override(100,100)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        if(itemList == null){
            return 0;
        }
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView _nama,_isi;
        Button _delete,_update;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data = FirebaseDatabase.getInstance();
            _nama = itemView.findViewById(R.id.Nama);
            _isi = itemView.findViewById(R.id.isi);
            _delete = itemView.findViewById(R.id.del);
            _delete.setOnClickListener(this);
            _update= itemView.findViewById(R.id.kon);
            _update.setOnClickListener(this);
            current = FirebaseAuth.getInstance().getCurrentUser();
            db = data.getReference().child(Note.class.getSimpleName()).child(current.getUid());
            img = itemView.findViewById(R.id.img);


        }

        @Override
        public void onClick(View view) {
        if(_delete.getId()==view.getId()){
        int a = getAdapterPosition();
        db.child(itemList.get(a).getKey()).removeValue();
//        db.push().setValue(new Note(itemList.get(a).getKey(),itemList.get(a).getIsi()));
        itemList.remove(a);
        notifyItemRemoved(a);
        notifyItemRangeChanged(a,itemList.size());
        notifyDataSetChanged();
    } else if(_update.getId()==view.getId()){
            int a = getAdapterPosition();
            Intent upd = new Intent(view.getContext(),update.class);
            upd.putExtra("key",itemList.get(a).getKey());
            upd.putExtra("judule",itemList.get(a).getNama());
            upd.putExtra("deskripsi",itemList.get(a).getIsi());
            upd.putExtra("imgUrl",itemList.get(a).getImgUrl());
            view.getContext().startActivity(upd);
        }
        }


    }
//    public String[] getLink(int a){
//        final String[] t = new String[1];
//        current = FirebaseAuth.getInstance().getCurrentUser();
//
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                t[0] = snapshot.child(itemList.get(a).getKey()).child("urI").getValue(String.class);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return t ;
//    }
}




