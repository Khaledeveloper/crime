package com.example.khaled.crime.activities;

import android.support.v4.app.Fragment;

import com.example.khaled.crime.CrimeListFragment;

/**
 * Created by khaled on 16/10/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        return new CrimeListFragment();
    }
}
