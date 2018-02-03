package ru.app.my.officecrimes.controller.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.app.my.officecrimes.R;
import ru.app.my.officecrimes.controller.CrimePagerActivity;
import ru.app.my.officecrimes.entities.Crime;
import ru.app.my.officecrimes.entities.CrimeLAb;
import ru.app.my.officecrimes.utils.TimeUtil;
//  Page 238
/**
 * Класс для создания списка прокрутки.
 */
public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLAb crimeLAb = CrimeLAb.getInstance(getActivity());
        List<Crime> crimes = crimeLAb.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Вложенный класс, заполняющий макет
     */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private ImageView mSolvedImageView;
//        TimeUtil mTimeUtil = new TimeUtil();

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            String date = TimeUtil.getDateAsString(mCrime.getDate());
            mDateTextView.setText(date);
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
//            1-я версия
//            Toast.makeText(getActivity(),
//                    mCrime.getTitle() + " выбрано! ", Toast.LENGTH_SHORT)
//                    .show();
//            2-я версия
//            Intent intent = new Intent(getActivity(), CrimeActivity.class);
//            3-я версия
//            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());

//            4-я PagerView
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());

            Log.i("CrimeListFragment",
                    "CrimeHolder  > onClick(), getActivity: " + getActivity().toString());
            Log.i("CrimeListFragment",
                    "CrimeHolder  > onClick(), INTENT = : " + intent.toString());
            startActivity(intent);
        }
    }

    /**
     * Вложенный класс-адаптер
     */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater from = LayoutInflater.from(getActivity());
            return new CrimeHolder(from, parent);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
