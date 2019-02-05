package com.bullseye.bidding.bid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bullseye.bidding.R;
import com.bullseye.bidding.adapters.OnGoingBid_Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OnGoingBids extends AppCompatActivity {
    RecyclerView recyclerView;
    OnGoingBid_Adapter adapter;
    List<OnGoingBids_listitems> mOngoingBidslist;
    DatabaseReference mDatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going_bids);

        final String uuid = "qwer";
        mDatabse = FirebaseDatabase.getInstance().getReference().child("bid");

        mOngoingBidslist = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mDatabse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child :children) {
                    String mUuid = child.getKey();
                    if(!mUuid.equals(uuid)){
                        String mBidName = child.child("name").getValue(String.class);
                        String mBidDes = child.child("description").getValue(String.class);
                        String mBasePrice = child.child("basePrice").getValue(String.class);

                        OnGoingBids_listitems temp = new OnGoingBids_listitems(mBidName,mBidDes,mBasePrice);
                        mOngoingBidslist.add(temp);
                    }
                }
                adapter = new OnGoingBid_Adapter(mOngoingBidslist,OnGoingBids.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
