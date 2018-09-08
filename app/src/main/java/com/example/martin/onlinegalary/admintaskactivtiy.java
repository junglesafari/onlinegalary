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

public class admintaskactivtiy extends AppCompatActivity {

    RecyclerView admintasklist;
    usersubmittaskadaptor adaptor;
    ArrayList<modleclassforuploads> dataadmintask;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admintaskactivtiy );
 admintasklist=findViewById( R.id.admintaskrecyclerview );
 dataadmintask=new ArrayList<>(  );
 reference=Databaseref.getDatabaseInstance().getReference("uploads");

    }

    protected void onStart() {
        super.onStart();
    reference.addValueEventListener( new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        dataadmintask.clear();
        for (DataSnapshot data:dataSnapshot.getChildren()){
            modleclassforuploads modleclassforuploads=data.getValue(modleclassforuploads.class);
            if(modleclassforuploads.getmName().contains( "ADMINTASK" )){
                dataadmintask.add( modleclassforuploads );
            }
        }
         adaptor=new usersubmittaskadaptor( admintaskactivtiy.this, dataadmintask, new nameclicklistener() {
             @Override
             public void onnameclick(View view, int position) {
                 Toast.makeText( admintaskactivtiy.this,position+" clicked",Toast.LENGTH_SHORT ).show();
                 Intent intent=new Intent( admintaskactivtiy.this,totalsubmissionoftaskactivity.class );
                 intent.putExtra( "position",position );
                 startActivity( intent );



             }
         } );

            admintasklist.setAdapter( adaptor );
            admintasklist.addItemDecoration( new DividerItemDecoration( admintaskactivtiy.this, DividerItemDecoration.VERTICAL ) );

            LinearLayoutManager manager = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );

            admintasklist.setLayoutManager( manager );


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    } );




    }






}
