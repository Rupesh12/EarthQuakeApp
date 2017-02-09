package com.example.android.quakereport;

/**
 * Created by rupesh on 2/8/17.
 */




public class Earthquake {
    private double mMag ;
    private String mPlace ;
    private long mDate ;
    private String mUrl ;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param mag is the magnitude (size) of the earthquake
     * @param place is the location where the earthquake happened
     * @param date is the time in milliseconds (from the Epoch) when the
     *                           earthquake happened
     * @param url is the website URL to find more details about the earthquake
     */


    public Earthquake(double mag , String place ,Long date,String url){
        this.mMag = mag ;
        this.mPlace = place ;
        this.mDate = date ;
        this.mUrl = url ;
    }

    public double getmMag() {
        return mMag;
    }

    public String getmPlace() {
        return mPlace;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmUrl(){
        return mUrl ;
    }
}
