package com.example.martin.onlinegalary;

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

public class selectedtask extends AppCompatActivity {
RecyclerView selectedtaskactivity;

    ArrayList<modleclassforuploads> data;
    usersubmittaskadaptor adapter;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_selectedtask );
        selectedtaskactivity=findViewById( R.id.selectedtaskactivity );
        data=new ArrayList<>(  );
        reference=Databaseref.getDatabaseInstance().getReference("uploads");

    }


    protected void onStart() {
        super.onStart();
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot data1:dataSnapshot.getChildren()){
                    modleclassforuploads modleclassforupload=data1.getValue(modleclassforuploads.class);
                    if(modleclassforupload.getmName().contains( "SEL" )){
                        data.add( modleclassforupload );

                    }
                }

                adapter=new usersubmittaskadaptor( selectedtask.this, data, new nameclicklistener() {
                    @Override
                    public void onnameclick(View view, int position) {
                        Toast.makeText( selectedtask.this,"This is Selected compete for the next task",Toast.LENGTH_SHORT ).show();

                    }
                } );
                selectedtaskactivity.setAdapter( adapter );
                selectedtaskactivity.addItemDecoration( new DividerItemDecoration( selectedtask.this, DividerItemDecoration.VERTICAL ) );

                LinearLayoutManager manager = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );

                selectedtaskactivity.setLayoutManager( manager );



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );







    }











}
