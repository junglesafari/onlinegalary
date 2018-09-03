package com.example.martin.onlinegalary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fimagerecyclerview extends AppCompatActivity {
RecyclerView namelist;
    ArrayList<modleclassforuploads> datatoshow;
    fimageadapter adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fimagerecyclerview );
        namelist=findViewById( R.id.fimagerview );
        datatoshow=new ArrayList<>(  );
        reference= Databaseref.getDatabaseInstance().getReference("uploads");

        Intent intent=getIntent();
        final String name=intent.getStringExtra( "name" );


        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datatoshow.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    modleclassforuploads modleclassforuploads=data.getValue(modleclassforuploads.class);
                    Log.d("mnm",name.subSequence( 0,name.length()-6 ) +"");
                    if(modleclassforuploads.getmName().contains(name.subSequence( 0,name.length()-6 ) )){
                        datatoshow.add( modleclassforuploads );
                    }
                }

                adapter=new fimageadapter( fimagerecyclerview.this, datatoshow, new nameclicklistener() {
                    @Override
                    public void onnameclick(View view, int position) {
                        //Toast.makeText( recyclerviewactivity.this,"item clicked",Toast.LENGTH_SHORT ).show();

                        Intent intent=new Intent( fimagerecyclerview.this,fimagerecyclerview.class );
                        intent.putExtra( "name",datatoshow.get( position ).getmName() );
                        startActivity( intent );


                    }
                } );
                namelist.setAdapter( adapter );
                namelist.addItemDecoration(new DividerItemDecoration(fimagerecyclerview.this,DividerItemDecoration.VERTICAL));

                LinearLayoutManager manager=new LinearLayoutManager( getApplicationContext(),LinearLayoutManager.VERTICAL,false );

                namelist.setLayoutManager( manager );



            }






            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( fimagerecyclerview.this,databaseError.getDetails()+" error message "+databaseError.getMessage(),Toast.LENGTH_SHORT ).show();
            }
        } );

















    }


    @Override
    protected void onStart() {
        super.onStart();
//        Intent intent=getIntent();
//        String name=intent.getStringExtra( "name" );
//        final String namereplace=name.replace( "BIET","" );
//
//        reference.addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                datatoshow.clear();
//                for (DataSnapshot data:dataSnapshot.getChildren()){
//                    modleclassforuploads modleclassforuploads=data.getValue(modleclassforuploads.class);
//
//                    if(modleclassforuploads.getmName().contains(namereplace.trim() )){
//                        datatoshow.add( modleclassforuploads );
//                    }
//                }
//
//                adapter=new fimageadapter( fimagerecyclerview.this, datatoshow, new nameclicklistener() {
//                    @Override
//                    public void onnameclick(View view, int position) {
//                        //Toast.makeText( recyclerviewactivity.this,"item clicked",Toast.LENGTH_SHORT ).show();
//
//                        Intent intent=new Intent( fimagerecyclerview.this,fimagerecyclerview.class );
//                        intent.putExtra( "name",datatoshow.get( position ).getmName() );
//                        startActivity( intent );
//
//
//                    }
//                } );
//                namelist.setAdapter( adapter );
//                namelist.addItemDecoration(new DividerItemDecoration(fimagerecyclerview.this,DividerItemDecoration.VERTICAL));
//
//                LinearLayoutManager manager=new LinearLayoutManager( getApplicationContext(),LinearLayoutManager.VERTICAL,false );
//
//                namelist.setLayoutManager( manager );
//
//
//
//            }
//
//
//
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText( fimagerecyclerview.this,databaseError.getDetails()+" error message "+databaseError.getMessage(),Toast.LENGTH_SHORT ).show();
//            }
//        } );

    }



}
