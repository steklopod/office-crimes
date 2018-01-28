package ru.app.my.officecrimes.controller;

import android.support.v4.app.Fragment;

import ru.app.my.officecrimes.views.CrimeFragment;
import ru.app.my.officecrimes.views.SingleFragmentActivity;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();

    }

}
