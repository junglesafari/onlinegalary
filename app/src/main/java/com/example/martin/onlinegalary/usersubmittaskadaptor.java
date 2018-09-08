package com.example.martin.onlinegalary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class usersubmittaskadaptor extends RecyclerView.Adapter<viewholderusersubmittask> {
    ArrayList<modleclassforuploads> datatoshow;
    Context context;
    private nameclicklistener listener;
    String show;

    public usersubmittaskadaptor(Context context, ArrayList<modleclassforuploads> datatoshow, nameclicklistener listener) {
        this.datatoshow = datatoshow;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public viewholderusersubmittask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View output = inflater.inflate( R.layout.tasklayut, parent, false );

        return new viewholderusersubmittask( output );
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholderusersubmittask holder, int position) {
        Picasso.with( context ).load( datatoshow.get( position ).getmImageUrl() ).fit().placeholder( R.mipmap.ic_launcher ).into( holder.usersubmittaskimageview );
         show=datatoshow.get( position ).getmName();


        if (show.contains( "ADMINTASK" )) {
            //holder.usersubmittasktextview.setText( "THIS IS YOUR TASK" );
        show="THIS IS YOUR TASK";
        }  if (show.contains( "SEL" )) {
             show=show.replace( "SEL", "" ) ;
        } if (datatoshow.get( position ).getmName().contains( "BIETDH" )) {
            show= "Your profile " + show.replace( "BIETDH", "" ) ;
        }  if (datatoshow.get( position ).getmName().contains( "ST" )) {
            show= show.replace( "ST", "  Submitted Task " ) ;
        }
            holder.usersubmittasktextview.setText( show );


        holder.itemview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onnameclick( v, holder.getAdapterPosition() );
            }
        } );


    }

    @Override
    public int getItemCount() {
        return datatoshow.size();
    }
}
