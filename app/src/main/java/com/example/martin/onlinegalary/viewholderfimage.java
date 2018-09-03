package com.example.martin.onlinegalary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class viewholderfimage extends RecyclerView.ViewHolder {
    View itemView;
    TextView textView;
    ImageView imageView;


    public viewholderfimage(View itemView) {
        super( itemView );
        this.itemView = itemView;
        textView = itemView.findViewById( R.id.ftext );
        imageView = itemView.findViewById( R.id.fimage );

    }
}
