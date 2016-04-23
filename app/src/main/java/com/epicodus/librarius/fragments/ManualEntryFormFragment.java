package com.epicodus.librarius.fragments;


import android.databinding.DataBindingUtil;
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
import com.epicodus.librarius.databinding.FragmentManualEntryFormBinding;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManualEntryFormFragment extends DialogFragment implements View.OnClickListener {
    private FragmentManualEntryFormBinding mBinding;

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_manual_entry_form, container, false);
        mBinding.submitNewButton.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
    }

    //ON CLICK LISTENER(S)
    @Override
    public void onClick(View v) {
        if (v == mBinding.submitNewButton) {
            BibliographyFragment bibliographyFragment = new BibliographyFragment();
            Bundle args = new Bundle();
            args.putString("authorFirstName", mBinding.authorFirstNameEditText.getText().toString());
            args.putString("authorLastName", mBinding.authorLastNameEditText.getText().toString());
            args.putString("bookTitle", mBinding.bookTitleEditText.getText().toString());
            args.putString("bookVol", mBinding.bookVolEditText.getText().toString());
            args.putString("bookEd", mBinding.bookEdEditText.getText().toString());
            args.putString("bookSeries", mBinding.bookSeriesEditText.getText().toString());
            args.putString("bookPublisher", mBinding.bookCityEditText.getText().toString());
            args.putString("bookYear", mBinding.bookYearEditText.getText().toString());
            args.putString("bookCity", mBinding.bookCityEditText.getText().toString());
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
