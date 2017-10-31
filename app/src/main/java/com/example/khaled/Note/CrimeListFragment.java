package com.example.khaled.Note;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaled.Note.activities.CrimeListActivity;
import com.example.khaled.Note.activities.ViewPagerActivity;
import com.example.khaled.Note.interfaces.InterfaceOnLongClick;
import com.example.khaled.Note.models.Crime;
import com.example.khaled.Note.models.CrimeLab;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeListFragment extends Fragment implements InterfaceOnLongClick {

    CrimeAdapter mAdapter;
    Toolbar mToolbar;
    FloatingActionButton mFAB;
    private DrawerLayout mDrawerLayout;
    Crimeholder crimeholder;
    //private ActionBarDrawerToggle mActionBarDrawerToggle;
    static boolean isSelected = false;
    ArrayList<Crime> SelectedItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);



    }


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
        mToolbar=(Toolbar)view.findViewById(R.id.ToolbarrecyclerviewID);

        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mDrawerLayout=(DrawerLayout)view.findViewById(R.id.DrawerLayoutMainID);

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,mToolbar,R.string.Enter_Content ,R.string.date_picker_title);

        if (((AppCompatActivity)getActivity()).getSupportActionBar()!=null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        }
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        TextView textViewToolbar =(TextView)view.findViewById(R.id.TextviewToolbarRecyclerviewID);



        mFAB =(FloatingActionButton)view.findViewById(R.id.FABmainID);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewCrime();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerUpdate();

        if (!isSelected){
            textViewToolbar.setVisibility(View.GONE);

        }



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
            mAdapter = new CrimeAdapter(crimes/*,getActivity()*/);
            mRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        } //the condation added in order to work with onResume to notify only




    }



    @Override
    public void onLongClickInterface(View view, int position) {
        Toast.makeText(view.getContext(), "interface done!!", Toast.LENGTH_SHORT).show();

    }

    public class Crimeholder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
private TextView mTitleCrime , mDateCrime;
        private TextView mContentNote;
        InterfaceOnLongClick interfaceOnLongClick;
        public CheckBox mCheckBoxList,checkdelete;
        private Crime mCrime;
        CardView mCardView;

        public Crimeholder(View itemView/*, CrimeListActivity crimeListActivity*/) {
            super(itemView);



            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

          //  itemView.setOnLongClickListener(this);

          //  checkdelete.setVisibility(View.GONE);




            mTitleCrime =(TextView) itemView.findViewById(R.id.CrimeTitle_listID);
            mDateCrime =(TextView)itemView.findViewById(R.id.CrimeDate_listID);
            mCheckBoxList =(CheckBox)itemView.findViewById(R.id.CheckBox_list_ctimeID);
            mContentNote=(TextView) itemView.findViewById(R.id.NoteContentRowID);
            checkdelete=(CheckBox)itemView.findViewById(R.id.checkTodeleteID);
            mCardView=(CardView)itemView.findViewById(R.id.cardviewRow);

         //   mCardView.setOnLongClickListener(this);




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
        public void setonLongClick(InterfaceOnLongClick interfaceOnLongClick){
            this.interfaceOnLongClick = interfaceOnLongClick;
        }




        public void Bindcrime(Crime crime){
            mCrime = crime;

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setTimeZone(TimeZone.getDefault());
           String date = df.format(mCrime.getDate());

            mTitleCrime.setText(mCrime.getTitle());
            mDateCrime.setText(date);
            mCheckBoxList.setChecked(mCrime.isSolved());
            mContentNote.setText(mCrime.getContent());

        }











        @Override
        public void onClick(View v) {
            Crime crime = new Crime();
            //UUID CrimeID = crime.getId();
         //changing the intent from Mainactivity to ViewPager
          //  Intent intent = MainActivity.newIntent(getActivity(),mCrime.getId());
            Intent intent = ViewPagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);

           /* Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);*/

        }






        @Override
        public boolean onLongClick(View v) {
           // CrimeListFragment.isSelected = true;

            if (interfaceOnLongClick!=null) {
                interfaceOnLongClick.onLongClickInterface(itemView, getAdapterPosition());
            }
            return true;
        }
    }


    public class CrimeAdapter extends RecyclerView.Adapter<Crimeholder>{
        InterfaceOnLongClick interfaceOnLongClick;
        private List<Crime> mCrimes;
        Context mContext;
       // CrimeListActivity mCrimeListActivity;
     //   CrimeListFragment mCrimeListFragment;
        public CrimeAdapter(List<Crime> crimes/* Context ctx*/) {
           // this.mContext = ctx;
            mCrimes = crimes;
        //   this.mCrimeListActivity =(CrimeListActivity) mContext;

        }

        @Override
        public Crimeholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.crime_list_row , parent , false);
            //Crimeholder crimeholder= new Crimeholder(view, mCrimeListActivity);


            return new Crimeholder(view);
          //  return crimeholder;
        }

        @Override
        public void onBindViewHolder(Crimeholder holder, int position) {

            Crime crime = mCrimes.get(position);
            holder.Bindcrime(crime);

            /*if (!CrimeListFragment.isSelected) {


                holder.checkdelete.setVisibility(View.GONE);
            }else {
                holder.checkdelete.setVisibility(View.VISIBLE);
            }*/


            holder.checkdelete.setVisibility(View.GONE);



        }

       /* @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }*/

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        //added for database
        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }





    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list, menu);
    }
    public void AddNewCrime(){
        Crime crime = new Crime();
        CrimeLab.get(getActivity()).addCrime(crime);
        Intent intent = ViewPagerActivity.newIntent(getActivity(), crime.getId());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_item_new_crime:
               AddNewCrime();



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
