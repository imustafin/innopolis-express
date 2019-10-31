package com.mfq.foodle.salah;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;


public class Salah_ProfileMyOrderFragment extends Fragment {
    RecyclerView mOrdersRecycler;
    public static Salah_ProfileMyOrderFragment newInstance() {
        return new Salah_ProfileMyOrderFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.salah_fragment_profile_my_order, container, false);
        init(inflate);
        return inflate;
    }


    private void init(View inflate) {
        mOrdersRecycler = inflate.findViewById(R.id.mOrdersRecycler);
        mOrdersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Salah_OrdersAdapter addressAdapter = new Salah_OrdersAdapter(getContext());
        mOrdersRecycler.setAdapter(addressAdapter);
    }
}
