package com.epicodus.librarius;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BibliographyFragment extends DialogFragment {

    @Bind(R.id.bibliographyTextView) TextView bibliographyTextView;


    public static BibliographyFragment newInstance() {
        return new BibliographyFragment();
    }

    public BibliographyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bibliography, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return view;
        } else {
            String authorFirstName = bundle.getString("authorFirstName");
            String authorLastName = bundle.getString("authorLastName");
            String bookTitle = bundle.getString("bookTitle");
            String bookVol = bundle.getString("bookVol");
            String bookEd = bundle.getString("bookEd");
            String bookSeries = bundle.getString("bookSeries");
            String publisher = bundle.getString("bookPublisher");
            String bookYear = bundle.getString("bookYear");
            String bookCity = bundle.getString("bookCity");

            Resources res = getResources();
            String bibliography = "";

            if (bookVol.equals("")) {
                bibliography = String.format(res.getString(R.string.bibliographyBookStandard), authorLastName, authorFirstName, bookTitle, bookCity, publisher, bookYear);
            } else {
                bibliography = String.format(res.getString(R.string.bibliographyBookAdvanced), authorLastName, authorFirstName, bookTitle, bookEd, bookVol, bookCity, publisher, bookYear, bookSeries);
            }

            bibliographyTextView.setText(bibliography);


            // Inflate the layout for this fragment
            return view;
        }


    }

}
