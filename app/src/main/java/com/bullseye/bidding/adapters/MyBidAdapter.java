package com.bullseye.bidding.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bullseye.bidding.R;
import com.bullseye.bidding.bid.My_host_listitems;
import com.bullseye.bidding.bid.OnGoingBids_listitems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.bullseye.bidding.Dashboard.email;
import static com.bullseye.bidding.Dashboard.uuid;

public class MyBidAdapter extends RecyclerView.Adapter<MyBidAdapter.ViewHolder> {

    private List<My_host_listitems> listitems;
    private Context context;
    DatabaseReference database;
    String push;
    String name;
    String uid;

    My_host_listitems mCurrent;

    public MyBidAdapter(List<My_host_listitems> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyBidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_host_listitems,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBidAdapter.ViewHolder viewHolder, final int position) {
        My_host_listitems l = listitems.get(position);
        viewHolder.txtDescrip.setText(l.getmDescrip());
        viewHolder.txtName.setText(l.getmName());
        viewHolder.txtBasePrice.setText(l.getmBasePrice());
        viewHolder.txtCurrentPrice.setText(l.getmCurrentPrice());
        viewHolder.txtStatus.setText(l.getmStatus());
        viewHolder.txtUser.setText(l.getmUser());
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = position;
                mCurrent = listitems.get(pos);
                uid = mCurrent.getmUser();
                name = mCurrent.getmName();

                database = FirebaseDatabase.getInstance().getReference().child("bid");
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            Iterable<DataSnapshot> ch = child.getChildren();
                            for (DataSnapshot q : ch) {
                                String mUid = q.getKey();
                                String n = q.child("name").getValue(String.class);
                                if(mUid.equals(uuid)&& n.equals(name)){
                                    push = child.getKey();
                                    String x =push;
                                    database.child(push).removeValue();
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
//        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
//            int pos = position;


//            uid = mCurrentBid.getUuid();
//            name = mCurrentBid.getBidName();

//            @Override
//            public void onClick(View view) {
//                database = FirebaseDatabase.getInstance().getReference().child("bid");
//                database.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                        for (DataSnapshot child : children) {
//                            Iterable<DataSnapshot> ch = child.getChildren();
//                            for (DataSnapshot q : ch) {
//                                String mUuid = q.getKey();
//                                String n= q.child("name").getValue(String.class);
//                                if (mUuid.equals(uid) && n.equals(name)) {
//                                    push= child.getKey();
//                                }
//                            }
//                        }
//                        database.child(push).setValue(null);
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button btnRemove;
        public TextView txtName;
        public TextView txtDescrip,txtBasePrice,txtCurrentPrice,txtStatus,txtUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnRemove = (Button)itemView.findViewById(R.id.btnRemove);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtDescrip =(TextView)itemView.findViewById(R.id.txtDescrip);
            txtStatus = (TextView)itemView.findViewById(R.id.txtStatus);
            txtBasePrice = (TextView)itemView.findViewById(R.id.txtBasePrice);
            txtCurrentPrice = (TextView)itemView.findViewById(R.id.txtCurrentPrice);
            txtUser = (TextView)itemView.findViewById(R.id.txtUser);

        }

    }
}
