package com.bullseye.bidding.bid;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bullseye.bidding.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class HostBid extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mDatabse,selfDatabase;
    private String  uuid;
    private Button btnHostSubmit;
    EditText edtProductName, edtDescription,edtBasePrice;
    private StorageReference mstoreref;
    Uri uriprofileimage;
    private ImageView imageView;
    private int requestcode=12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_bid);
        imageView=(ImageView)findViewById(R.id.image);

        uuid="test";

        edtProductName = (EditText)findViewById(R.id.edtProductName);
        edtDescription= (EditText)findViewById(R.id.edtDescription);
        edtBasePrice = (EditText)findViewById(R.id.edtBasePrice);
        btnHostSubmit = (Button)findViewById(R.id.btnHostSubmit);

        btnHostSubmit.setOnClickListener(this);
        imageView.setOnClickListener(this);

        mDatabse = FirebaseDatabase.getInstance().getReference();
    //    selfDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabse = mDatabse.child("bid").child(uuid);
     //   selfDatabase= selfDatabase.child("users").child(uuid);
        mstoreref=FirebaseStorage.getInstance().getReference("uploads");

    }


    private void Showimagechooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,requestcode);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, "OnactivityResult", Toast.LENGTH_SHORT).show();

        if(requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            uriprofileimage=data.getData();
            imageView.setImageURI(uriprofileimage);
            try {
                //      Toast.makeText(this, "try", Toast.LENGTH_SHORT).show();
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uriprofileimage);
                imageView.setImageBitmap(bitmap);
                //uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileExtention(Uri uri)
    {
        ContentResolver CR=getContentResolver();
        MimeTypeMap mine=MimeTypeMap.getSingleton();
        return  mine.getExtensionFromMimeType(CR.getType(uri));
    }

    private void uploadImageToFirebaseStorage() {
        if(uriprofileimage!=null)
        {
            //        Toast.makeText(this, "UploadImageToFirebase", Toast.LENGTH_SHORT).show();

            mstoreref=mstoreref.child(System.currentTimeMillis()+"."+getFileExtention(uriprofileimage));
            mstoreref.putFile(uriprofileimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(HostBid.this, "Uploaded successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HostBid.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        }
        else{
            Toast.makeText(this, "Please select an Image", Toast.LENGTH_SHORT).show();
        }

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHostSubmit:
                String name = edtProductName.getText().toString();
                String description = edtDescription.getText().toString();
                String basePrice = edtBasePrice.getText().toString();
                mDatabse.child("name").setValue(name);
                mDatabse.child("description").setValue(description);
                mDatabse.child("basePrice").setValue(basePrice);
                uploadImageToFirebaseStorage();
                break;
            case R.id.image:
                Showimagechooser();
                break;




        }
    }
}
