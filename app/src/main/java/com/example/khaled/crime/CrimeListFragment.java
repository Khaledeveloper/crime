package com.example.khaled.crime;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeListFragment extends Fragment {
    CrimeAdapter mAdapter;

RecyclerView mRecyclerView;
    public CrimeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
mRecyclerView =(RecyclerView)view.findViewById(R.id.mRecyclerviewID);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerUpdate();



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerUpdate();
    }

    private void RecyclerUpdate(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        } //the condation added in order to work with onResume to notify only


    }

    private class Crimeholder extends RecyclerView.ViewHolder implements View.OnClickListener{
private TextView mTitleCrime , mDateCrime;
        private CheckBox mCheckBoxList;
        private Crime mCrime;
        public Crimeholder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);




            mTitleCrime =(TextView) itemView.findViewById(R.id.CrimeTitle_listID);
            mDateCrime =(TextView)itemView.findViewById(R.id.CrimeDate_listID);
            mCheckBoxList =(CheckBox)itemView.findViewById(R.id.CheckBox_list_ctimeID);


           /* mCheckBoxList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   //if (mCheckBoxList.isChecked()){
                     //   mCrime.setSolved(false);
                  //  }else {
                        mCrime.setSolved(true);

                   // }

                }
            });*/






        }




        public void Bindcrime(Crime crime){
            mCrime = crime;
            mTitleCrime.setText(mCrime.getTitle());
            mDateCrime.setText(mCrime.getDate().toString());
            mCheckBoxList.setChecked(mCrime.isSolved());

        }









        @Override
        public void onClick(View v) {
            Crime crime = new Crime();
            //UUID CrimeID = crime.getId();
         //changing the intent from Mainactivity to ViewPager
          //  Intent intent = MainActivity.newIntent(getActivity(),mCrime.getId());
            Intent intent = ViewPagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);

           /* Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);*/

        }


    }


    private class CrimeAdapter extends RecyclerView.Adapter<Crimeholder>{
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public Crimeholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.crime_list_row , parent , false);


            return new Crimeholder(view);
        }

        @Override
        public void onBindViewHolder(Crimeholder holder, int position) {

            Crime crime = mCrimes.get(position);
            holder.Bindcrime(crime);

        }

       /* @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }*/

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}
