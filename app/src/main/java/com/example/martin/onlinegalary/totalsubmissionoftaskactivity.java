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

public class totalsubmissionoftaskactivity extends AppCompatActivity {
RecyclerView totlesubmissiontask;
ArrayList<modleclassforuploads> datalist;
nameclicklistener listener;
DatabaseReference reference;
usersubmittaskadaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_totalsubmissionoftaskactivity );
   totlesubmissiontask=findViewById( R.id.totalsubmissionoftask );
   datalist=new ArrayList<>(  );
   reference=Databaseref.getDatabaseInstance().getReference("uploads");
    }

    protected  void onStart() {
        Intent intent=getIntent();
         final int position=intent.getIntExtra( "position",0 );
        final int pos=position+1;
        super.onStart();
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            datalist.clear();
            for(DataSnapshot data:dataSnapshot.getChildren()){
                modleclassforuploads modleclassforuploads=data.getValue(modleclassforuploads.class);
                if(modleclassforuploads.getmName().contains( "ST"+pos )){
                    datalist.add( modleclassforuploads );

                }
            }


                adaptor=new usersubmittaskadaptor( totalsubmissionoftaskactivity.this, datalist, new nameclicklistener() {
                    @Override
                    public void onnameclick(View view, int position) {
                        Toast.makeText( totalsubmissionoftaskactivity.this,"you clicked  "+position+" in totle submissions",Toast.LENGTH_SHORT ).show();
                    }
                } );
                totlesubmissiontask.setAdapter( adaptor );
                totlesubmissiontask.addItemDecoration( new DividerItemDecoration( totalsubmissionoftaskactivity.this, DividerItemDecoration.VERTICAL ) );

                LinearLayoutManager manager = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );

                totlesubmissiontask.setLayoutManager( manager );





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );




    }



}
