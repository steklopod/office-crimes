package ru.app.my.officecrimes.controller;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

import ru.app.my.officecrimes.controller.fragments.CrimeFragment;
import ru.app.my.officecrimes.controller.fragments.SingleFragmentActivity;

/**
 * Элемент без пролистывания. Вместо него используется:
 * @link  ../controller.CrimePagerActivity.class
 */
@Deprecated
public class CrimeActivity extends SingleFragmentActivity {
    public static final String EXTRA_CRIME_ID = "ru.app.my.officecrimes.controller.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
