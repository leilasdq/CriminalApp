package com.example.criminalintent.repository;

import com.example.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeRepository {
    private static final CrimeRepository instance = new CrimeRepository();
    private List<Crime> mCrimes;

    public static CrimeRepository getInstance() {
        return instance;
    }

    //read
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public void setCrimes(List<Crime> crimes) {
        mCrimes = crimes;
    }

    private CrimeRepository() {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime# " + i);
            crime.setSolved(i % 2 == 0);

            mCrimes.add(crime);
        }
    }

    //read
    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id))
                return crime;
        }

        return null;
    }

    public int getPosition(UUID id) {
        Crime crime = getCrime(id);
        return mCrimes.indexOf(crime);
    }

    //insert
    public void insertCrime(Crime crime) {
        mCrimes.add(crime);
    }

    //delete
    public void deleteCrime(Crime crime) {
        if (crime != null)
            mCrimes.remove(crime);
    }

    //delete
    public void deleteCrime(UUID id) {
        deleteCrime(getCrime(id));
    }
}
