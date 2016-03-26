package com.epicodus.librarius.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.epicodus.librarius.fragments.ManualEntryFormFragment;
import com.epicodus.librarius.R;
import com.epicodus.librarius.fragments.BibliographyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.scanButton) FloatingActionButton mScanButton;
    @Bind(R.id.searchButton) FloatingActionButton mSearchButton;
    @Bind(R.id.manualEntryButton) FloatingActionButton mManualEntryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == mScanButton) {
            Snackbar.make(v, "Imagine a Scanning Window popping up now", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        if (v == mSearchButton) {
            Snackbar.make(v, "Imagine a Search Form popping up now", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        if (v == mManualEntryButton) {
            showManualEntryFormFragment();
        }
    }

    private void showManualEntryFormFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ManualEntryFormFragment manualEntryForm = ManualEntryFormFragment.newInstance("Some Title");
        manualEntryForm.show(fm, "fragment_manual_entry_form");
    }
}
