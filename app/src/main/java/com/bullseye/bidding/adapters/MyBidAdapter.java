package com.bullseye.bidding.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bullseye.bidding.R;
import com.bullseye.bidding.bid.My_host_listitems;

import java.util.List;

public class MyBidAdapter extends RecyclerView.Adapter<MyBidAdapter.ViewHolder> {

    private List<My_host_listitems> listitems;
    private Context context;

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
    public void onBindViewHolder(@NonNull MyBidAdapter.ViewHolder viewHolder, int position) {
        My_host_listitems l = listitems.get(position);
        viewHolder.txtDescrip.setText(l.getmDescrip());
        viewHolder.txtName.setText(l.getmName());
        viewHolder.txtBasePrice.setText(l.getmBasePrice());
        viewHolder.txtCurrentPrice.setText(l.getmCurrentPrice());
        viewHolder.txtStatus.setText(l.getmStatus());
        viewHolder.txtUser.setText(l.getmUser());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public TextView txtDescrip,txtBasePrice,txtCurrentPrice,txtStatus,txtUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtDescrip =(TextView)itemView.findViewById(R.id.txtDescrip);
            txtStatus = (TextView)itemView.findViewById(R.id.txtStatus);
            txtBasePrice = (TextView)itemView.findViewById(R.id.txtBasePrice);
            txtCurrentPrice = (TextView)itemView.findViewById(R.id.txtCurrentPrice);
            txtUser = (TextView)itemView.findViewById(R.id.txtUser);

        }

    }
}
