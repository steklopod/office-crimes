package ru.app.my.officecrimes.controller;

// Dmitry Koltovich, Янв., 2018.

import android.support.v4.app.Fragment;

import ru.app.my.officecrimes.views.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new CrimeListFragment();
    }
}