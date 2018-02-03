package ru.app.my.officecrimes.entities;

// Dmitry Koltovich, Янв., 2018.

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Crime implements Serializable{
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Crime crime = (Crime) o;

        if (mSolved != crime.mSolved) return false;
        if (mId != null ? !mId.equals(crime.mId) : crime.mId != null) return false;
        if (mTitle != null ? !mTitle.equals(crime.mTitle) : crime.mTitle != null) return false;
        return mDate != null ? mDate.equals(crime.mDate) : crime.mDate == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mDate != null ? mDate.hashCode() : 0);
        result = 31 * result + (mSolved ? 1 : 0);
        return result;
    }
}
