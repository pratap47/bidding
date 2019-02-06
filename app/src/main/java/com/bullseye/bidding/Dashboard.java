package com.bullseye.bidding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bullseye.bidding.bid.HostBid;
import com.bullseye.bidding.bid.MyBids;
import com.bullseye.bidding.bid.OnGoingBids;
import com.bullseye.bidding.login.s_signup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    Button btnHost ,btnOngoingBids, btnSignOut, btnMyBids;
    DatabaseReference uDatabase;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    public static String uuid;
    public static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        uDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        SharedPreferences sharedPreferences =getSharedPreferences("email",Context.MODE_PRIVATE);
        email =sharedPreferences.getString("email","error");

        uDatabase.push().setValue(email);

        mAuth =FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            Toast.makeText(this,"no user",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Dashboard.this,s_signup.class));
        }
        else{
            firebaseUser = mAuth.getCurrentUser();

            uuid = firebaseUser.getUid();
        }


        if(uuid!=null) {
            Toast.makeText(this, uuid, Toast.LENGTH_SHORT).show();
        }
        btnHost = (Button) findViewById(R.id.btnHost);
        btnOngoingBids = (Button) findViewById(R.id.btnOngoingBids);
        btnSignOut = (Button) findViewById(R.id.btnSignOut);
        btnMyBids = (Button) findViewById(R.id.btnMyBids);

        btnHost.setOnClickListener(this);
        btnOngoingBids.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnMyBids.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHost:
                startActivity(new Intent(Dashboard.this,HostBid.class));
                break;
            case R.id.btnMyBids:
                startActivity(new Intent(Dashboard.this,MyBids.class));
                break;
            case R.id.btnOngoingBids:
                startActivity(new Intent(Dashboard.this,OnGoingBids.class));
                break;
            case R.id.btnSignOut:
                mAuth.signOut();
                startActivity(new Intent(Dashboard.this,MainActivity.class));
        }

    }
}
