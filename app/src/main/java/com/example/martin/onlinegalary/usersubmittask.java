package com.example.martin.onlinegalary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class usersubmittask extends AppCompatActivity {

    RecyclerView tasklist;
    ArrayList<modleclassforuploads> data;
    usersubmittaskadaptor adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_usersubmittask );
        tasklist=findViewById( R.id.usersubmittaskrecyclerview );
        data=new ArrayList<>(  );
        reference=Databaseref.getDatabaseInstance().getReference("uploads");

    }


    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        final String name=intent.getStringExtra( "name" );
        String to_replace="BIETDH";
        final String to_show=name.replace( to_replace,"" );
     //   Toast.makeText( usersubmittask.this,name+"    "+to_show,Toast.LENGTH_SHORT ).show();
reference.addValueEventListener( new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        data.clear();
        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
            modleclassforuploads modleclassforuploads=dataSnapshot1.getValue(modleclassforuploads.class);

            assert modleclassforuploads != null;
            if(modleclassforuploads.getmName().contains( to_show )){
                data.add( modleclassforuploads );
            }

        }

       adapter=new usersubmittaskadaptor( usersubmittask.this, data, new nameclicklistener() {
           @Override
           public void onnameclick(View view, int position) {
              // Toast.makeText( usersubmittask.this,"item  "+ position+" clicked",Toast.LENGTH_SHORT).show();
           }
       } );

        tasklist.setAdapter( adapter );


        tasklist.addItemDecoration( new DividerItemDecoration( usersubmittask.this, DividerItemDecoration.VERTICAL ) );

        LinearLayoutManager manager = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );

        tasklist.setLayoutManager( manager );










    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
} );







    }





















}
