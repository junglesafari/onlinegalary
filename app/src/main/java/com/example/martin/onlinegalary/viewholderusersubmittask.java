package com.example.martin.onlinegalary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class viewholderusersubmittask extends RecyclerView.ViewHolder {
     View itemview;
     TextView usersubmittasktextview;
     ImageView usersubmittaskimageview;
    public viewholderusersubmittask(View itemView) {
        super( itemView );

        usersubmittasktextview=itemView.findViewById( R.id.usersubmittasktextview );
        usersubmittaskimageview=itemView.findViewById( R.id.usersubmittaskimageview );
        this.itemview=itemView;

    }
}
