package com.example.martin.onlinegalary;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recyclerviewactivity extends AppCompatActivity {
    RecyclerView namelist;
    ArrayList<modleclassforuploads> datatoshow;
    nameadapter adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recyclerviewactivity );
        namelist = findViewById( R.id.namereccyclerview );
        datatoshow = new ArrayList<>();
        reference = Databaseref.getDatabaseInstance().getReference( "uploads" );
    }


    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datatoshow.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    modleclassforuploads modleclassforuploads = data.getValue( modleclassforuploads.class );

                    if (modleclassforuploads.getmName().contains( "BIETDH" )) {
                        datatoshow.add( modleclassforuploads );
                    }
                }

                adapter = new nameadapter( recyclerviewactivity.this, datatoshow, new nameclicklistener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onnameclick(View view, int position) {
                       // Toast.makeText( recyclerviewactivity.this, "item clicked", Toast.LENGTH_SHORT ).show();

                        Intent intent=new Intent( recyclerviewactivity.this,usersubmittask.class );
                        intent.putExtra( "name",datatoshow.get( position ).getmName());
                        startActivity( intent );


                    }
                } );
                namelist.setAdapter( adapter );
                namelist.addItemDecoration( new DividerItemDecoration( recyclerviewactivity.this, DividerItemDecoration.VERTICAL ) );

                LinearLayoutManager manager = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );

                namelist.setLayoutManager( manager );


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( recyclerviewactivity.this, databaseError.getDetails() + " error message " + databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.uploadwork) {
            Intent intent = new Intent( recyclerviewactivity.this, MainActivity.class );
            startActivity( intent );
        }
        if (id == R.id.aboutdeveloper) {

           Intent intent=new Intent( recyclerviewactivity.this,aboutthedeveloper.class );
           startActivity( intent );


        }
        if (id == R.id.task) {
Intent intent=new Intent( recyclerviewactivity.this,admintaskactivtiy.class );
startActivity( intent );

        }
        if(id==R.id.finaltask){
            Intent intent=new Intent(recyclerviewactivity.this,selectedtask.class );
            startActivity( intent );
        }
        if(id==R.id.instructions){
            Intent intent=new Intent( recyclerviewactivity.this ,instructionactivity.class);
            startActivity( intent );
        }


        return super.onOptionsItemSelected( item );
    }
}
