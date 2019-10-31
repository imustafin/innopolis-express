package com.mfq.foodle.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.UserAddressesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileAddressesFragment extends Fragment implements View.OnClickListener {


    private RecyclerView mRecycler;
    private UserAddressesAdapter mAdapter;

    public ProfileAddressesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile_addresses, container, false);
        mRecycler = root.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new UserAddressesAdapter();
        mRecycler.setAdapter(mAdapter);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecycler.setHasFixedSize(true);
        return root;
    }



    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), ProfileAddressesFragment.class.getSimpleName(), Toast.LENGTH_SHORT).show();
    }

}
