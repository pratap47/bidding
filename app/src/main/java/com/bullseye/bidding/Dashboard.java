package com.bullseye.bidding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bullseye.bidding.bid.HostBid;
import com.bullseye.bidding.bid.MyBids;
import com.bullseye.bidding.bid.OnGoingBids;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    Button btnHost ,btnOngoingBids, btnSignOut, btnMyBids;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    public static String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth =FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            Toast.makeText(this,"no user",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Dashboard.this,MainActivity.class));
        }
        else{

        }

        firebaseUser = mAuth.getCurrentUser();
        uuid = firebaseUser.getUid();
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
