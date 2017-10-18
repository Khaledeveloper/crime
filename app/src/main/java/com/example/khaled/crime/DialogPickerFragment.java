package com.example.khaled.crime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Date;

/**
 * Created by khaled on 18/10/2017.
 */

public class DialogPickerFragment extends DialogFragment {
    private static final String NewInstace_Tage_date ="com.example.khaled.crime.date";

    public static DialogPickerFragment newInstace(Date date){
        Bundle bundle = new Bundle();
        bundle.putSerializable(NewInstace_Tage_date,date);
        DialogPickerFragment dialogPickerFragment = new DialogPickerFragment();
        dialogPickerFragment.setArguments(bundle);

        return dialogPickerFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date mDate = (Date)getArguments().getSerializable(NewInstace_Tage_date);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.date_picker, null);


        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();


    }
}
