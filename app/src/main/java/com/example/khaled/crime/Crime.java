package com.example.khaled.crime;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by khaled on 15/10/2017.
 */

public class Crime {
    private String mTitle;
    private UUID mId;
    private Date mDate;
    private boolean mSolved;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
      /*  android.text.format.DateFormat dateFormat = new android.text.format.DateFormat();
       dateFormat.format("yyyy-MM-dd hh:mm:ss a", mDate);*/
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
