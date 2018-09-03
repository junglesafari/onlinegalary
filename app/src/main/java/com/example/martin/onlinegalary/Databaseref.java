package com.example.martin.onlinegalary;

import android.content.Intent;

import com.google.firebase.database.FirebaseDatabase;

public class Databaseref {
private static FirebaseDatabase INSTANCE;
public static FirebaseDatabase getDatabaseInstance(){
    if(INSTANCE == null){
        INSTANCE=FirebaseDatabase.getInstance();
        INSTANCE.setPersistenceEnabled( true );
    }
return INSTANCE;

}


}
