package com.epicodus.librarius.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epicodus.librarius.GoogleService;
import com.epicodus.librarius.ISBNDBService;
import com.epicodus.librarius.R;
import com.epicodus.librarius.adapters.BookListAdapter;
import com.epicodus.librarius.models.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BibliographyFragment extends Fragment {
    @Bind(R.id.bibliographyRecyclerView) RecyclerView mRecyclerView;
    private BookListAdapter mAdapter;
    public ArrayList<Book> mBooks = new ArrayList<>();

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
        } else if (bundle.getString("query") != null) {
            String query = bundle.getString("query");
            getBooks(query);
            return view;
        }
        return view;
    }

    private void getBooks(String query) {
        final ISBNDBService isbnDbService = new ISBNDBService(getContext());

        isbnDbService.findBooks(query, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mBooks = isbnDbService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new BookListAdapter(getActivity().getApplicationContext(), mBooks);
                        mRecyclerView.setAdapter(mAdapter);
                        for (Book book : mBooks) {
                            Log.d("Title: ", book.getBookTitle());
                        }
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
//
//
//            String bibliography = "";
////
////            if (bookVol.equals("")) {
////                bibliography = String.format(res.getString(R.string.bibliographyBookStandard), authorLastName, authorFirstName, bookTitle, bookCity, publisher, bookYear);
////            } else {
////                bibliography = String.format(res.getString(R.string.bibliographyBookAdvanced), authorLastName, authorFirstName, bookTitle, bookEd, bookVol, bookCity, publisher, bookYear, bookSeries);
////            }
//
//            bibliographyTextView.setText(bibliography);
//
//            return view;
//        } else {
//            String searchTerm = bundle.getString("searchTerm");
//            searchBooks(searchTerm);
//        }
//        return view;
//    }



}
