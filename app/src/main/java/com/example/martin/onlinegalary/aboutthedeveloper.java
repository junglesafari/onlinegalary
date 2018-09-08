package com.example.martin.onlinegalary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class aboutthedeveloper extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_aboutthedeveloper );
imageView=findViewById( R.id.himanshu );
        Picasso.with( aboutthedeveloper.this ).load( R.drawable.profile ).fit().into( imageView );
    }
}
