package com.example.criminalintent.controller;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.repository.CrimeRepository;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeDetailFragment extends Fragment {

    public static final String ARG_CRIME_ID = "crimeId";

    private EditText mEditTextTitle;
    private Button mButtonDate;
    private CheckBox mCheckBoxSolved;


    private Crime mCrime;

    public CrimeDetailFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param uuid id of crime for showing detail
     * @return
     */
    public static Fragment newInstance(UUID uuid) {
        CrimeDetailFragment fragment = new CrimeDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(CrimeDetailFragment.ARG_CRIME_ID, uuid);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(SingleFragmentActivity.TAG, "CrimeFragment:onCreate");

//        UUID id = (UUID) getActivity().getIntent().getSerializableExtra(CrimeListFragment.EXTRA_CRIME_ID);
        UUID id = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeRepository.getInstance().getCrime(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime, container, false);


        mEditTextTitle = view.findViewById(R.id.edittext_title);
        mButtonDate = view.findViewById(R.id.button_date);
        mCheckBoxSolved = view.findViewById(R.id.checkbox_solved);

        mEditTextTitle.setText(mCrime.getTitle());
        mButtonDate.setText(mCrime.getDate().toString());
        mCheckBoxSolved.setChecked(mCrime.getSolved());

        mButtonDate.setEnabled(false);

        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

}
