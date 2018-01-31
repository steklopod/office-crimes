package ru.app.my.officecrimes.entities;

// Dmitry Koltovich, Янв., 2018.

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Хранилище-синглтон (in-memory) для хранения преступлений.
 */

public class CrimeLAb {
    private static CrimeLAb sCrimeLAb;
    private List<Crime> mCrimes;

    private CrimeLAb(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime # " + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }


    public static CrimeLAb getInstance(Context context) {
        if (sCrimeLAb == null) {
            sCrimeLAb = new CrimeLAb(context);
        }
        return sCrimeLAb;
    }
}
