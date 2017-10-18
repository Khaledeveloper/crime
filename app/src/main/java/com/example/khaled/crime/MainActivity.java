package com.example.khaled.crime;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    private static final String Crime_ID_KEY ="com.example.khaled.crime.crimeID";


    public static Intent newIntent(Context context, UUID crimeID){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(Crime_ID_KEY,crimeID);
        return intent;

    }

//crimeActivity
    @Override
    protected Fragment creatFragment() {
        //to send it to method of newInstance for Argue
        UUID crimeID = (UUID)getIntent().getSerializableExtra(Crime_ID_KEY);

        return CrimeFragment.newInstance(crimeID);
    }
}
