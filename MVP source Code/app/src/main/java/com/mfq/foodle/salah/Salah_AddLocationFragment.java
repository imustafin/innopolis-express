package com.mfq.foodle.salah;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;


public class Salah_AddLocationFragment extends Fragment implements Step {

    public static Salah_AddLocationFragment newInstance() {
        Salah_AddLocationFragment fragment = new Salah_AddLocationFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.salah_fragment_add_location, container, false);
        return root;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
