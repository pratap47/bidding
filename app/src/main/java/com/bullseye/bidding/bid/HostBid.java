package com.bullseye.bidding.bid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bullseye.bidding.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HostBid extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mDatabse,selfDatabase;
    private String  uuid;
    private Button btnHostSubmit;
    EditText edtProductName, edtDescription,edtBasePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_bid);

        uuid="test";

        edtProductName = (EditText)findViewById(R.id.edtProductName);
        edtDescription= (EditText)findViewById(R.id.edtDescription);
        edtBasePrice = (EditText)findViewById(R.id.edtBasePrice);
        btnHostSubmit = (Button)findViewById(R.id.btnHostSubmit);

        btnHostSubmit.setOnClickListener(this);

        mDatabse = FirebaseDatabase.getInstance().getReference();
    //    selfDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabse = mDatabse.child("bid").child(uuid);
     //   selfDatabase= selfDatabase.child("users").child(uuid);

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



        }
    }
}
