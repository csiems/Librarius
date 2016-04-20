package com.epicodus.librarius.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.epicodus.librarius.R;
import butterknife.ButterKnife;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class BarcodeScannerFragment extends DialogFragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private OnBarcodeScannedListener mListener;

    public BarcodeScannerFragment() {
        // Required empty public constructor
    }

    public static BarcodeScannerFragment newInstance() {
        return new BarcodeScannerFragment();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBarcodeScannedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnBarcodeScannedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        if (mListener != null) {
            mListener.onBarcodeScanned(rawResult.toString());
        }
        dismiss();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(BarcodeScannerFragment.this);
            }
        }, 5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
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

    public interface OnBarcodeScannedListener {
        void onBarcodeScanned(String rawResult);
    }
}
