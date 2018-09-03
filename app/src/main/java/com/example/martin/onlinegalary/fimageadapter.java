package com.example.martin.onlinegalary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class fimageadapter extends RecyclerView.Adapter<viewholderfimage> {
   ArrayList<modleclassforuploads> datalist;
   LayoutInflater inflater;
   Context context;
    nameclicklistener clicklistener;

    public fimageadapter(Context context,ArrayList<modleclassforuploads> datalist,   nameclicklistener clicklistener) {
        this.datalist = datalist;
        this.context = context;
        this.clicklistener = clicklistener;
    }







    @NonNull
    @Override
    public viewholderfimage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater=(LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View output=inflater.inflate( R.layout.fimage,parent,false );
        return new viewholderfimage( output );
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholderfimage holder, int position) {
        Picasso
                .with( context )
                .load(datalist.get( position ).getmImageUrl()  )
                .fit()
                .placeholder( R.mipmap.ic_launcher_round )
                .into( holder.imageView );
          holder.itemView.setOnClickListener( new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  clicklistener.onnameclick( view,holder.getAdapterPosition() );
              }
          } );


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
