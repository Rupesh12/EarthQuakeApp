package com.example.android.quakereport;

/**
 * Created by rupesh on 2/8/17.
 */

public class Earthquake {
    private String mMag ;
    private String mPlace ;
    private String mDate ;

    public Earthquake(String mag , String place ,String date){
        this.mMag = mag ;
        this.mPlace = place ;
        this.mDate = date ;
    }

    public String getmMag() {
        return mMag;
    }

    public String getmPlace() {
        return mPlace;
    }

    public String getmDate() {
        return mDate;
    }
}
