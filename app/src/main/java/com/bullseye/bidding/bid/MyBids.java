package com.bullseye.bidding.bid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bullseye.bidding.Dashboard;
import com.bullseye.bidding.R;
import com.bullseye.bidding.adapters.MyBidAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBids extends AppCompatActivity {

    RecyclerView recyclerView;
    MyBidAdapter myBidAdapter;
    List<My_host_listitems> mBidsList;
    DatabaseReference mDatabase;
    String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bids);
        uuid = "test";

        mDatabase =FirebaseDatabase.getInstance().getReference().child("bid");

        mBidsList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child :children){
                    String mUuid = child.getKey();
                    if(mUuid.equals(uuid)){
                        String mBidName = child.child("name").getValue(String.class);
                        String mBidDes = child.child("description").getValue(String.class);
                        String mBasePrice = child.child("basePrice").getValue(String.class);

                        My_host_listitems temp = new My_host_listitems(mBidName,mBidDes);
                        mBidsList.add(temp);

                        //myBidAdapter.notifyItemInserted(mBidsList.size()-1);
                    }
                }
                myBidAdapter = new MyBidAdapter(mBidsList,MyBids.this);
                recyclerView.setAdapter(myBidAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


      //  recyclerView.setLayoutManager(new LinearLayoutManager(MyBids.this));




    }
}
