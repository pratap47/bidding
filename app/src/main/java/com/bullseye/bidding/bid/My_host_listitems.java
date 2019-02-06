package com.bullseye.bidding.bid;

public class My_host_listitems {

    private String mName;
    private String mDescrip;
    private String mBasePrice;
    private String mCurrentPrice;
    private String mStatus;
    private String mUser;

    public My_host_listitems(String mName, String mDescrip, String mBasePrice, String mCurrentPrice, String mStatus,String mUser) {
        this.mName = mName;
        this.mDescrip = mDescrip;
        this.mBasePrice = mBasePrice;
        this.mCurrentPrice = mCurrentPrice;
        this.mStatus = mStatus;
        this.mUser =mUser;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescrip() {
        return mDescrip;
    }

    public void setmDescrip(String mDescrip) {
        this.mDescrip = mDescrip;
    }

    public String getmBasePrice() {
        return mBasePrice;
    }

    public void setmBasePrice(String mBasePrice) {
        this.mBasePrice = mBasePrice;
    }

    public String getmCurrentPrice() {
        return mCurrentPrice;
    }

    public void setmCurrentPrice(String mCurrentPrice) {
        this.mCurrentPrice = mCurrentPrice;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}

