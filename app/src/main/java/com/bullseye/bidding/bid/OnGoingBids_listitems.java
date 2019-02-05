package com.bullseye.bidding.bid;

import android.widget.TextView;

public class OnGoingBids_listitems {
    private String BidName;
    private String BidDescrip;
    private String BidBasePrice;

    public OnGoingBids_listitems(String bidName, String bidDescrip, String bidBasePrice) {
        BidName = bidName;
        BidDescrip = bidDescrip;
        BidBasePrice = bidBasePrice;
    }

    public String getBidName() {
        return BidName;
    }

    public void setBidName(String bidName) {
        BidName = bidName;
    }

    public String getBidDescrip() {
        return BidDescrip;
    }

    public void setBidDescrip(String bidDescrip) {
        BidDescrip = bidDescrip;
    }

    public String getBidBasePrice() {
        return BidBasePrice;
    }

    public void setBidBasePrice(String bidBasePrice) {
        BidBasePrice = bidBasePrice;
    }
}
