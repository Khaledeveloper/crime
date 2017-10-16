package com.example.khaled.crime;

import android.support.v4.app.Fragment;

/**
 * Created by khaled on 16/10/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        return new CrimeListFragment();
    }
}
