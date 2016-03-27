package com.epicodus.librarius.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.librarius.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManualEntryFormFragment extends DialogFragment implements View.OnClickListener {

    @Bind(R.id.submitNewButton) Button mSubmitNewButton;
    @Bind(R.id.authorFirstNameEditText) EditText mAuthorFirstName;
    @Bind(R.id.authorLastNameEditText) EditText mAuthorLastName;
    @Bind(R.id.bookTitleEditText) EditText mBookTitle;
    @Bind(R.id.bookVolEditText) EditText mBookVol;
    @Bind(R.id.bookEdEditText) EditText mBookEd;
    @Bind(R.id.bookSeriesEditText) EditText mBookSeries;
    @Bind(R.id.publisherEditText) EditText mPublisher;
    @Bind(R.id.bookCityEditText) EditText mBookCity;
    @Bind(R.id.bookYearEditText) EditText mBookYear;

    public ManualEntryFormFragment() {
        // Required empty public constructor
    }

    public static ManualEntryFormFragment newInstance() {
        return new ManualEntryFormFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manual_entry_form, container, false);
        ButterKnife.bind(this, view);
        mSubmitNewButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
    }

    //ON CLICK LISTENER(S)
    @Override
    public void onClick(View v) {
        if (v == mSubmitNewButton) {
            BibliographyFragment bibliographyFragment = new BibliographyFragment();
            Bundle args = new Bundle();
            args.putString("authorFirstName", mAuthorFirstName.getText().toString());
            args.putString("authorLastName", mAuthorLastName.getText().toString());
            args.putString("bookTitle", mBookTitle.getText().toString());
            args.putString("bookVol", mBookVol.getText().toString());
            args.putString("bookEd", mBookEd.getText().toString());
            args.putString("bookSeries", mBookSeries.getText().toString());
            args.putString("bookPublisher", mPublisher.getText().toString());
            args.putString("bookYear", mBookYear.getText().toString());
            args.putString("bookCity", mBookCity.getText().toString());
            bibliographyFragment.setArguments(args);
            dismiss();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content_layout, bibliographyFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
