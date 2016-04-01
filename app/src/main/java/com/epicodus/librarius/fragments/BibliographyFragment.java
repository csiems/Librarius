package com.epicodus.librarius.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epicodus.librarius.services.ISBNDBService;
import com.epicodus.librarius.R;
import com.epicodus.librarius.adapters.BookListAdapter;
import com.epicodus.librarius.models.Book;

import java.io.IOException;
import java.util.ArrayList;

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
        //TODO: pull items from firebase
        return view;
    }
}

