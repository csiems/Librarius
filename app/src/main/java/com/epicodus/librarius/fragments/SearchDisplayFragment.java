package com.epicodus.librarius.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.librarius.R;
import com.epicodus.librarius.adapters.SearchDisplayListAdapter;
import com.epicodus.librarius.models.Book;
import com.epicodus.librarius.services.ISBNDBService;
import com.epicodus.librarius.util.OnSearchedBookSelectedListener;

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
public class SearchDisplayFragment extends DialogFragment implements OnSearchedBookSelectedListener {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private SearchDisplayListAdapter mAdapter;
    public ArrayList<Book> mBooks = new ArrayList<>();
    private Integer mPosition;

    public SearchDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String query = bundle.getString("query");
        getBooks(query);
    }


    @Override
    public void onSearchedBookSelected(Integer position, ArrayList<Book> books) {
        mPosition = position;
        mBooks = books;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_display, container, false);
        ButterKnife.bind(this, view);
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
                        mAdapter = new SearchDisplayListAdapter(getContext(), mBooks);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }
    @Override
    public void onStop() {
        super.onStop();
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismiss();
    }
}
