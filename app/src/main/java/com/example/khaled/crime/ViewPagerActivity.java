package com.example.khaled.crime;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

public class ViewPagerActivity extends AppCompatActivity {
    ViewPager mViewPager;
    private List<Crime>mCrime;
private static final String CRIMID_KEY ="com.example.khaled.crime.crimeIDViewPager";

    public static Intent newIntent(Context context, UUID crimeid){
        Intent intent = new Intent(context,ViewPagerActivity.class);
        intent.putExtra(CRIMID_KEY,crimeid);

        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager=(ViewPager)findViewById(R.id.ViewPagerID);
        UUID crimeID =(UUID) getIntent().getSerializableExtra(CRIMID_KEY);

        mCrime = CrimeLab.get(this).getCrimes();
        FragmentManager fm = getSupportFragmentManager();
        //fragmentstatepager adapter diffrent the fragmentpageradapter
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrime.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrime.size();
            }


        });

// to not start the viewpager from the bigaining
        for (int i = 0 ; i<mCrime.size() ; i++){
            if (mCrime.get(i).getId().equals(crimeID)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }




    }
}
