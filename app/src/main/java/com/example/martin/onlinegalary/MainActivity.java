package com.example.martin.onlinegalary;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
private static final int PICK_IMAGE_REQUEST=1;
private Button mButtonChooseImage;
private Button mButtonUpload;
private EditText mEditTextFileName;

private ImageView imageView;
private ProgressBar progressBar;
private Uri mImageUri;
//get the database and imagereferences
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mButtonChooseImage=findViewById( R.id.button_choose_image );
        mButtonUpload=findViewById( R.id.button_upload );
        mEditTextFileName=findViewById( R.id.edit_text_file_name );

        imageView=findViewById( R.id.imageView );
        progressBar=findViewById( R.id.progress_bar );
        storageReference= FirebaseStorage.getInstance().getReference("uploads");
        databaseReference=FirebaseDatabase.getInstance().getReference("uploads");

        mButtonUpload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             uploadFile();
            }
        } );

        mButtonChooseImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openFileChoser();
            }
        } );


    }
    private void openFileChoser(){
        Intent intent=new Intent(  );
        intent.setType( "image/*" );
        intent.setAction( Intent.ACTION_GET_CONTENT );
        startActivityForResult( intent,PICK_IMAGE_REQUEST );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

       if(requestCode == PICK_IMAGE_REQUEST &&
               resultCode == RESULT_OK && data != null &&
               data.getData() != null){
           mImageUri=data.getData();
           Picasso.with( this ).load( mImageUri ).into( imageView );
       }

    }
//the only responsibilty of this function is to get the extension of image file.
    private String getFileExtension(Uri uri){
        ContentResolver ct=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType( ct.getType( uri ) );
    }


 private void uploadFile(){
if(mImageUri!=null){
    StorageReference fileReference=storageReference.child( System.currentTimeMillis()+"."+getFileExtension( mImageUri ) );
     fileReference.putFile( mImageUri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
         @Override
         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
           //we use handler here to use postdelay method becase we want that when upload is successfull
             // then progressbar takes time to set its progress back to 0 in some time not immediately
             Handler handler=new Handler(  );
             handler.postDelayed( new Runnable() {
                 @Override
                 public void run() {
                     progressBar.setProgress( 0 );
                 }
             },5000 );
// work to do here////
         }
     } ).addOnFailureListener( new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
        Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();
         }
     } ).addOnProgressListener( new OnProgressListener<UploadTask.TaskSnapshot>() {
         @Override
         public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
           double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
           progressBar.setProgress( (int)progress );
         }
     } );


}else {
    Toast.makeText( this,"file not exist",Toast.LENGTH_SHORT ).show();
}


 }

}
