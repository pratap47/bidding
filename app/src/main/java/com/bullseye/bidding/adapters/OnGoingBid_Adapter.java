package com.bullseye.bidding.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bullseye.bidding.R;
import com.bullseye.bidding.bid.My_host_listitems;
import com.bullseye.bidding.bid.OnGoingBids;
import com.bullseye.bidding.bid.OnGoingBids_listitems;

import java.util.List;

public class OnGoingBid_Adapter extends RecyclerView.Adapter<OnGoingBid_Adapter.ViewHolder> {
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
    public void onBindViewHolder(@NonNull OnGoingBid_Adapter.ViewHolder viewHolder, int position) {
      OnGoingBids_listitems l = list.get(position);
      viewHolder.txtBidBasePrice.setText(l.getBidBasePrice());
      viewHolder.txtBiddesc.setText(l.getBidDescrip());
      viewHolder.txtBidName.setText(l.getBidName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtBidName, txtBiddesc,txtBidBasePrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBidName = (TextView)itemView.findViewById(R.id.txtBidName);
            txtBiddesc =(TextView)itemView.findViewById(R.id.txtBidDescrip);
            txtBidBasePrice=(TextView)itemView.findViewById(R.id.txtBidBasePrice);
        }
    }
}
