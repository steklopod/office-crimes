package ru.app.my.officecrimes.controller.fragments;

// Dmitry Koltovich, Янв., 2018.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.List;
import java.util.UUID;

import ru.app.my.officecrimes.R;
import ru.app.my.officecrimes.controller.CrimePagerActivity;
import ru.app.my.officecrimes.entities.Crime;
import ru.app.my.officecrimes.entities.CrimeLAb;
import ru.app.my.officecrimes.utils.TimeUtil;

import static android.widget.CompoundButton.OnCheckedChangeListener;

public class CrimeFragment extends Fragment {
    //public class CrimeFragment extends AppCompatActivity {
    private Crime mCrime;
    private EditText mTittleText;
    private Button mDateButton;
    private Button mGoFirst;
    private Button mGoLast;
    private CheckBox mSolvedCheckBox;
    private static final String ARG_CRIME_ID = "crime_id";

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      1-й вариант
//        UUID crimeID = (UUID) getActivity().getIntent()
//                .getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLAb.getInstance(getActivity()).getCrime(crimeID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(TimeUtil.getDateAsString(mCrime.getDate()));
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        mTittleText = (EditText) v.findViewById(R.id.crime_title);
        mTittleText.setText(mCrime.getTitle());
        mTittleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        final List<Crime> crimes = CrimeLAb.getInstance(getActivity()).getCrimes();


        mGoFirst = (Button) v.findViewById(R.id.goToFirst);
        mGoLast = (Button) v.findViewById(R.id.goToLast);
        if (crimes.size() > 0) {
            mGoFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent
                            = CrimePagerActivity.newIntent(getActivity(), crimes.get(0).getId());
                    startActivity(intent);
                }
            });
            mGoLast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent
                            = CrimePagerActivity.newIntent(getActivity(),
                            crimes.get(crimes.size() - 1).getId());
                    startActivity(intent);
                }
            });
        } else {
            mGoLast.setEnabled(false);
            mGoFirst.setEnabled(false);
        }

        return v;
    }


}
