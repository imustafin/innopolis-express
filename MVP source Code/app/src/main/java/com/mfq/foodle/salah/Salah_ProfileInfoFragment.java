package com.mfq.foodle.salah;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;

public class Salah_ProfileInfoFragment extends Fragment {

    public static Salah_ProfileInfoFragment newInstance() {
        Salah_ProfileInfoFragment fragment = new Salah_ProfileInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.salah_fragment_profile_info, container, false);
    }


}
