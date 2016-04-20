package com.epicodus.librarius.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.librarius.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends DialogFragment implements View.OnClickListener {
    @Bind(R.id.submitSearchButton) Button mSubmitSearchButton;
    @Bind(R.id.queryEditText) EditText mQueryEditText;
    private OnBookSearchedListener mListener;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBookSearchedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnBookSearchedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        mSubmitSearchButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
    }


    //ON CLICK LISTENER(S)
    @Override
    public void onClick(View v) {
        if (v == mSubmitSearchButton) {
            if (mListener != null) {
                mListener.onBookSearched(mQueryEditText.getText().toString());
                dismiss();
            }
        }
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

    public interface OnBookSearchedListener {
        void onBookSearched(String isbn);
    }
}
