package com.mfq.foodle.salah;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.FoodAdapter;
import com.mfq.foodle.decoreator.HomeItemDecoretore;
import com.mfq.foodle.models.Meal;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;


public class Salah_PickDessertFragment extends Fragment implements Step {

    private RecyclerView mRecycler;
    private RecyclerView mRecycler2;
    private Salah_FoodAdapter mAdapter;
    public static Salah_PickDessertFragment newInstance() {
        Salah_PickDessertFragment fragment = new Salah_PickDessertFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.salah_fragment_pick_dessert, container, false);
        // Inflate the layout for this fragment
        mRecycler = root.findViewById(R.id.recycler);
        mRecycler2 = root.findViewById(R.id.recycler2);

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), GridLayoutManager.HORIZONTAL , false));
        mRecycler2.setLayoutManager(new LinearLayoutManager(getActivity() , GridLayoutManager.HORIZONTAL , false));
        mAdapter = new Salah_FoodAdapter();
        mRecycler.setAdapter(mAdapter);
        mRecycler2.setAdapter(mAdapter);
        mAdapter.setMealList(Meal.buildDessert());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.home_list_spacing);
        mRecycler.addItemDecoration(new HomeItemDecoretore(1, spacingInPixels, true, 0));
        mRecycler2.addItemDecoration(new HomeItemDecoretore(1, spacingInPixels, true, 0));

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
