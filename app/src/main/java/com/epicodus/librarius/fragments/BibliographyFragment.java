package com.epicodus.librarius.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epicodus.librarius.LibrariusApplication;
import com.epicodus.librarius.adapters.FirebaseBookListAdapter;
import com.epicodus.librarius.services.ISBNDBService;
import com.epicodus.librarius.R;
import com.epicodus.librarius.adapters.BookListAdapter;
import com.epicodus.librarius.models.Book;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


//holds saved books in recyclerview a la savedrestaurantlistactivity

public class BibliographyFragment extends Fragment {
    private Query mQuery;
    private Firebase mFirebaseRef;
    private FirebaseBookListAdapter mAdapter;

    @Bind(R.id.bibliographyRecyclerView) RecyclerView mRecyclerView;

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

        Firebase.setAndroidContext(getActivity().getApplicationContext());
        mFirebaseRef = LibrariusApplication.getAppInstance().getFirebaseRef();

        setUpFirebaseQuery();
        setUpRecyclerView();

        return view;
    }

    private void setUpFirebaseQuery() {

    }

    private void setUpRecyclerView() {
        
    }
}

