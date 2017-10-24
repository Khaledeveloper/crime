package com.example.khaled.crime.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.khaled.crime.R;
import com.example.khaled.crime.addnotefragment;

public class AddNote extends SingleFragmentActivity{


    @Override
    protected Fragment creatFragment() {
        return new addnotefragment();
    }
}