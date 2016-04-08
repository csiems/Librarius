package com.epicodus.librarius.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.epicodus.librarius.LibrariusApplication;
import com.epicodus.librarius.R;
import com.epicodus.librarius.adapters.FirebaseBookListAdapter;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BibliographyActivity extends AppCompatActivity {

    private Query mQuery;
    private Firebase mFirebaseRef;
    private String mCurrentUserUid;
    private FirebaseBookListAdapter mAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_display); //using the same layout as search display
        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);
        mFirebaseRef = LibrariusApplication.getAppInstance().getFirebaseRef();
        mCurrentUserUid = mFirebaseRef.getAuth().getUid();
//
//        setUpFirebaseQuery();
//        setUpRecyclerView();
    }
}
