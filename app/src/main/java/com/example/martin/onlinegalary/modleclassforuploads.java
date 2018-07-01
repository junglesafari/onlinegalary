package com.example.martin.onlinegalary;

public class modleclassforuploads {
    private  String mName;
    private String mImageUrl;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
    public modleclassforuploads(String mName, String mImageUrl) {
       if(mName.trim().equals( "" )){mName="NO NAME";}
        this.mName = mName;
        this.mImageUrl = mImageUrl;
    }
    public modleclassforuploads(){

    }
}
