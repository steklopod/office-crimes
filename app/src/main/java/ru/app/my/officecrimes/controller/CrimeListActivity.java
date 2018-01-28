package ru.app.my.officecrimes.controller;

// Dmitry Koltovich, Янв., 2018.

import android.support.v4.app.Fragment;

import ru.app.my.officecrimes.views.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}