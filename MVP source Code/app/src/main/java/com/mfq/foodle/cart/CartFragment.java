package com.mfq.foodle.cart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.activities.AddPhoneNumberActivity;
import com.mfq.foodle.activities.InvoiceActivity;
import com.mfq.foodle.adapters.FoodleDriveAdapter;
import com.mfq.foodle.adapters.viewholder.CartItemsAdapter;
import com.mfq.foodle.frgments.BottomBarFragment;
import com.mfq.foodle.models.FoodleDrive;
import com.mfq.foodle.models.cart.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BottomBarFragment implements View.OnClickListener {


    private RecyclerView mCartItemsRecycler;
    private CartItemsAdapter mCartItemsAdapter;
    private RecyclerView mDriveRecycler;
    private FoodleDriveAdapter mFoodleDriveAdapter;
    private BottomAppBar mBottomAppBar;
    private FloatingActionButton mFab;

    public CartFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        mDriveRecycler = root.findViewById(R.id.drive_recycler);
        mCartItemsRecycler = root.findViewById(R.id.cart_recycler);
        root.findViewById(R.id.cart_button_phone).setOnClickListener(v -> startActivity(new Intent(getActivity(), AddPhoneNumberActivity.class)));
        root.findViewById(R.id.cart_order).setOnClickListener(v -> startActivity(new Intent(getActivity(), InvoiceActivity.class)));
        root.findViewById(R.id.cart_close).setOnClickListener(this);

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildItemRecycler();
        buildDriveRecycler();
    }

    private void buildDriveRecycler() {
        mDriveRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDriveRecycler.setItemAnimator(new DefaultItemAnimator());
        mDriveRecycler.setHasFixedSize(true);
        mFoodleDriveAdapter = new FoodleDriveAdapter();
        mDriveRecycler.setAdapter(mFoodleDriveAdapter);

        List<FoodleDrive> foodleDrives = new ArrayList<>();
        foodleDrives.add(new FoodleDrive("1 jd", "1:00-2:00 pm"));
        foodleDrives.add(new FoodleDrive("1 jd", "3:00-4:00 pm"));
        foodleDrives.add(new FoodleDrive("3 jd", "Within 45 min"));
        mFoodleDriveAdapter.setDrives(foodleDrives);
    }

    private void buildItemRecycler() {
        mCartItemsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mCartItemsRecycler.setItemAnimator(new DefaultItemAnimator());
        mCartItemsRecycler.setHasFixedSize(true);
        mCartItemsAdapter = new CartItemsAdapter();
        mCartItemsRecycler.setAdapter(mCartItemsAdapter);
        mCartItemsAdapter.setProductList(Cart.getInstance().getProducts());
    }



    @Override
    public void onBottomBarAttached(BottomAppBar bottomAppBar) {

        mBottomAppBar = bottomAppBar;
    }


    @Override
    public void onFabAttached(FloatingActionButton fab) {
        mFab = fab;
    }

    @Override
    public void onClick(View v) {
        if (mBottomAppBar != null)
            mBottomAppBar.setVisibility(View.VISIBLE);
        getActivity().onBackPressed();
    }
}
