package com.bullseye.bidding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bullseye.bidding.bid.HostBid;
import com.bullseye.bidding.bid.MyBids;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    Button btnHost ,btnOngoingBids, btnViewMyBids, btnMyBids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnHost = (Button) findViewById(R.id.btnHost);
        btnOngoingBids = (Button) findViewById(R.id.btnOngoingBids);
        btnViewMyBids = (Button) findViewById(R.id.btnViewMyBids);
        btnMyBids = (Button) findViewById(R.id.btnMyBids);

        btnHost.setOnClickListener(this);
        btnOngoingBids.setOnClickListener(this);
        btnViewMyBids.setOnClickListener(this);
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
        }

    }
}
