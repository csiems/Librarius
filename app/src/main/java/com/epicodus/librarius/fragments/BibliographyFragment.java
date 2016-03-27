package com.epicodus.librarius.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.librarius.GoogleService;
import com.epicodus.librarius.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


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
        } else if (bundle.getString("searchTerm") == null) {
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

            return view;
        } else {
            String searchTerm = bundle.getString("searchTerm");
            searchBooks(searchTerm);
        }
        return view;
    }

    private void searchBooks(String searchTerm) {
        final GoogleService googleService = new GoogleService(getActivity());

        googleService.searchBooks(searchTerm, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v("JSON DATA", jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
