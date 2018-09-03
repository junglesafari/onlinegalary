package com.example.martin.onlinegalary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class viewholdernamerecyclerview extends RecyclerView.ViewHolder {
TextView paricipentname;
CircleImageView circleImageView;
View itemview;
public viewholdernamerecyclerview(@NonNull View itemview){
super(itemview);
    this.itemview=itemview;
    paricipentname=itemview.findViewById( R.id.followertextview );
    circleImageView=itemView.findViewById( R.id.circularimageview );


}

}
