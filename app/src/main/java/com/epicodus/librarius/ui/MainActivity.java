package com.epicodus.librarius.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.epicodus.librarius.LibrariusApplication;
import com.epicodus.librarius.fragments.BarcodeScannerFragment;
import com.epicodus.librarius.fragments.ManualEntryFormFragment;
import com.epicodus.librarius.R;
import com.epicodus.librarius.fragments.BibliographyFragment;
import com.epicodus.librarius.fragments.SearchFragment;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.scanButton) FloatingActionButton mScanButton;
    @Bind(R.id.searchButton) FloatingActionButton mSearchButton;
    @Bind(R.id.manualEntryButton) FloatingActionButton mManualEntryButton;
    private Firebase mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFirebaseRef = LibrariusApplication.getAppInstance().getFirebaseRef();
        checkForAuthenticatedUser();

        //TODO: Figure out how to ask for camera permissions in app.

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_content_layout, BibliographyFragment.newInstance(), "bibliography")
                    .commit();
        }

        mScanButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
        mManualEntryButton.setOnClickListener(this);

    }

    private void checkForAuthenticatedUser() {
        AuthData authData = mFirebaseRef.getAuth();
        if (authData == null) {
            goToLoginActivity();
        }
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_logout:
                this.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mFirebaseRef.unauth();
        goToLoginActivity();
    }

    @Override
    public void onClick(View v) {
        if (v == mScanButton) {
            launchBarcodeScannerFragment();
        }
        if (v == mSearchButton) {
            launchSearchFragment();
        }
        if (v == mManualEntryButton) {
            launchManualEntryFormFragment();
        }
    }

    private void launchBarcodeScannerFragment() {
        FragmentManager fm = getSupportFragmentManager();
        BarcodeScannerFragment barcodeScanner = BarcodeScannerFragment.newInstance();
        barcodeScanner.show(fm, "fragment_barcode_scanner");
    }

    private void launchSearchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        SearchFragment search = SearchFragment.newInstance();
        search.show(fm, "fragment_search");
    }

    private void launchManualEntryFormFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ManualEntryFormFragment manualEntryForm = ManualEntryFormFragment.newInstance();
        manualEntryForm.show(fm, "fragment_manual_entry_form");
    }
}
