package com.mfq.foodle.drive;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.FoodleDriveAdapter;
import com.mfq.foodle.frgments.BottomBarFragment;
import com.mfq.foodle.models.FoodleDrive;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodleDriveFragment extends BottomBarFragment {


    private BottomAppBar mBottomAppBar;
    private FloatingActionButton mFab;
    private RecyclerView mRecycler;
    private FoodleDriveAdapter mAdapter;

    public FoodleDriveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_foodle_drive, container, false);
        mRecycler = root.findViewById(R.id.drive_recycler);
        root.findViewById(R.id.close).setOnClickListener(v -> getActivity().onBackPressed());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setHasFixedSize(true);
        mAdapter = new FoodleDriveAdapter();
        mRecycler.setAdapter(mAdapter);
        buildDriveData();


    }

    private void buildDriveData() {
        List<FoodleDrive> foodleDrives = new ArrayList<>();
        foodleDrives.add(new FoodleDrive("1 jd", "1:00-2:00 pm"));
        foodleDrives.add(new FoodleDrive("1 jd", "3:00-4:00 pm"));
        foodleDrives.add(new FoodleDrive("3 jd", "Within 45 min"));
        mAdapter.setDrives(foodleDrives);
    }

    @Override
    public void onBottomBarAttached(BottomAppBar bottomAppBar) {
        mBottomAppBar = bottomAppBar;
        bottomAppBar.setVisibility(View.GONE);

    }

    @Override
    public void onFabAttached(FloatingActionButton fab) {
        mFab = fab;
        fab.hide();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBottomAppBar.setVisibility(View.VISIBLE);
        mFab.show();
    }
}
