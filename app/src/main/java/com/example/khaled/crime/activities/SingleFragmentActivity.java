package com.example.khaled.crime.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import com.example.khaled.crime.R;
import com.example.khaled.crime.models.Crime;
import com.example.khaled.crime.models.CrimeLab;
/**
 * Created by khaled on 16/10/2017.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment creatFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.Fragment_Container);
        if (fragment==null){
            fragment = creatFragment();
            fm.beginTransaction()
                    .add(R.id.Fragment_Container , fragment)
                    .commit();
        }
    }
}
