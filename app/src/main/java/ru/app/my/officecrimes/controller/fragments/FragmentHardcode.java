package ru.app.my.officecrimes.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.app.my.officecrimes.R;

/**
 * Пример хардкода без наследования.
 *  >>>! НЕ ИСПОЛЬЗУЕТСЯ.
 * Использовать @link ../controller.frgments.CrimeFragment.class
 */

@Deprecated
public class FragmentHardcode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {

//          Единственный неуневерсальный фрагмант:
            fragment = new CrimeFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }


}
