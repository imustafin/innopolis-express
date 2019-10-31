package com.mfq.foodle.frgments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFeedback extends BottomBarFragment {


    public HelpFeedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_help_feedback, container, false);
        root.findViewById(R.id.close).setOnClickListener(v -> getActivity().onBackPressed());

        return root;
    }

    @Override
    public void onBottomBarAttached(BottomAppBar bottomAppBar) {

    }

    @Override
    public void onFabAttached(FloatingActionButton fab) {

    }
}
