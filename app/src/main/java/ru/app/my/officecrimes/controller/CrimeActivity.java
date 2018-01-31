package ru.app.my.officecrimes.controller;

import android.support.v4.app.Fragment;

import ru.app.my.officecrimes.views.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new CrimeFragment();
    }
}
