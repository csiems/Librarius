package com.epicodus.librarius.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 123;
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
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                launchBarcodeScannerFragment();
            } else {
                showMessageOKCancel("You need to allow access to Contacts",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                            }
                        });
                return;
            }
        }
        if (v == mSearchButton) {
            launchSearchFragment();
        }
        if (v == mManualEntryButton) {
            launchManualEntryFormFragment();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Thank you!", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "CAMERA Use Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
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
