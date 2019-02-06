package com.bullseye.bidding.bid;

import android.widget.TextView;

public class OnGoingBids_listitems {
    private String BidName;
    private String BidDescrip;
    private String BidBasePrice;
    private String BidStatus;
    private String BidCurrentPrice;
    private String BidUser;
    private String uuid;

    public OnGoingBids_listitems(String bidName, String bidDescrip, String bidBasePrice, String bidStatus, String bidCurrentPrice,String bidUser,String Uuid) {
        BidName = bidName;
        BidDescrip = bidDescrip;
        BidBasePrice = bidBasePrice;
        BidStatus = bidStatus;
        BidCurrentPrice = bidCurrentPrice;
        BidUser = bidUser;
        uuid =Uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBidUser() {
        return BidUser;
    }

    public void setBidUser(String bidUser) {
        BidUser = bidUser;
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

    public String getBidStatus() {
        return BidStatus;
    }

    public void setBidStatus(String bidStatus) {
        BidStatus = bidStatus;
    }

    public String getBidCurrentPrice() {
        return BidCurrentPrice;
    }

    public void setBidCurrentPrice(String bidCurrentPrice) {
        BidCurrentPrice = bidCurrentPrice;
    }
}
