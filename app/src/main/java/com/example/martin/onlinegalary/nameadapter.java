package com.example.martin.onlinegalary;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.net.ProtocolFamily;
import java.util.ArrayList;

public class nameadapter extends RecyclerView.Adapter<viewholdernamerecyclerview> {

    private ArrayList<modleclassforuploads> datalist;
    private Context context;
    private nameclicklistener clicklistener;

    View output;

    public nameadapter(Context context,ArrayList<modleclassforuploads> datalist, nameclicklistener clicklistener) {
        this.datalist = datalist;
        this.context = context;
        this.clicklistener = clicklistener;

    }


    @NonNull
    @Override
    public viewholdernamerecyclerview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        assert inflater != null;
            output = inflater.inflate( R.layout.layoutnamerecyclerview, parent, false );
       return new viewholdernamerecyclerview( output );
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholdernamerecyclerview holder, int position) {
        String to_replace="BIETDH";
        String to_show=datalist.get( position ).getmName().replace( to_replace,"" );
       holder.paricipentname.setText( to_show );
        Picasso
                .with( context )
                .load( datalist.get( position ).getmImageUrl() )
                .placeholder( R.mipmap.ic_launcher_round )
                .fit()
                .into( holder.circleImageView );
        holder.itemview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklistener.onnameclick( view, holder.getAdapterPosition() );
            }
        } );


    }

    @Override
    public int getItemCount() {
        return this.datalist.size();
    }
}
