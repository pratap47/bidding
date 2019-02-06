package com.bullseye.bidding.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bullseye.bidding.R;
import com.bullseye.bidding.bid.My_host_listitems;
import com.bullseye.bidding.bid.OnGoingBids;
import com.bullseye.bidding.bid.OnGoingBids_listitems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.bullseye.bidding.Dashboard.email;
import static com.bullseye.bidding.Dashboard.uuid;

public class OnGoingBid_Adapter extends RecyclerView.Adapter<OnGoingBid_Adapter.ViewHolder>  {
    String push;
    String uid;
    String name;
    String bidAmount;
    OnGoingBids_listitems mCurrentBid;
    DatabaseReference database;
    private List<OnGoingBids_listitems> list;
    private Context context;

    public OnGoingBid_Adapter(List<OnGoingBids_listitems> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public OnGoingBid_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.on_going_bids_listitems,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OnGoingBid_Adapter.ViewHolder viewHolder, final int position) {
      OnGoingBids_listitems l = list.get(position);
      viewHolder.txtBidBasePrice.setText(l.getBidBasePrice());
      viewHolder.txtBiddesc.setText(l.getBidDescrip());
      viewHolder.txtBidName.setText(l.getBidName());
      viewHolder.txtBidStatus.setText(l.getBidStatus());
      viewHolder.txtBidCurrentPrice.setText(l.getBidCurrentPrice());
      viewHolder.txtUser.setText(l.getBidUser());

      viewHolder.btnMakeBid.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            int pos = position;

            mCurrentBid = list.get(pos);
            bidAmount = viewHolder.edtBidAmount.getText().toString();
            int bid = Integer.parseInt(bidAmount);
            String bidCurrentAmount = mCurrentBid.getBidCurrentPrice();
            int bidCurrent = Integer.parseInt(bidCurrentAmount);
            uid = mCurrentBid.getUuid();
            name = mCurrentBid.getBidName();
            if(bidCurrent<bid){
                viewHolder.txtBidCurrentPrice.setText(bidAmount);
                database = FirebaseDatabase.getInstance().getReference().child("bid");
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            Iterable<DataSnapshot> ch = child.getChildren();
                            for (DataSnapshot q : ch) {
                                String mUuid = q.getKey();
                                String n= q.child("name").getValue(String.class);
                                if (mUuid.equals(uid) && n.equals(name)) {
                                    push= child.getKey();
                                }
                            }
                        }
                        database.child(push).child(mCurrentBid.getUuid()).child("current").setValue(bidAmount);
                        database.child(push).child(mCurrentBid.getUuid()).child("email").setValue(email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

               // DatabaseReference p = FirebaseDatabase.getInstance().getReference().child(bid);

            }


          }
      });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtBidName, txtBiddesc,txtBidBasePrice,txtBidCurrentPrice,txtBidStatus,txtUser;
        Button btnMakeBid;
        EditText edtBidAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBidName = (TextView)itemView.findViewById(R.id.txtBidName);
            txtBiddesc =(TextView)itemView.findViewById(R.id.txtBidDescrip);
            txtBidBasePrice=(TextView)itemView.findViewById(R.id.txtBidBasePrice);
            txtBidCurrentPrice = (TextView)itemView.findViewById(R.id.txtBidCurrentPrice);
            txtBidStatus=(TextView)itemView.findViewById(R.id.txtBidStatus);
            btnMakeBid = (Button)itemView.findViewById(R.id.btnMakeBid);
            txtUser=(TextView)itemView.findViewById(R.id.txtUser);
            edtBidAmount =(EditText)itemView.findViewById(R.id.edtBidAmount);


        }
    }
}
