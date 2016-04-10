package com.epicodus.librarius.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.librarius.LibrariusApplication;
import com.epicodus.librarius.adapters.FirebaseBookListAdapter;
import com.epicodus.librarius.R;
import com.epicodus.librarius.models.Book;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;


//holds saved books in recyclerview a la savedrestaurantlistactivity

public class BibliographyFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private FirebaseBookListAdapter mAdapter;
    private Query mQuery;
    private String mCurrentUserUid;
    private Firebase mFirebaseRef;

    public static BibliographyFragment newInstance() {
        return new BibliographyFragment();
    }

    public BibliographyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseRef = LibrariusApplication.getAppInstance().getFirebaseRef();
        AuthData authData = mFirebaseRef.getAuth();
        if (authData != null) {
            mCurrentUserUid = mFirebaseRef.getAuth().getUid();
            setUpFirebaseQuery();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_display, container, false);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    private void setUpFirebaseQuery() {
        String location = mFirebaseRef.child(mCurrentUserUid).toString();
        mQuery = new Firebase(location).orderByChild("bookTitle");
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseBookListAdapter(mQuery, Book.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }



}

