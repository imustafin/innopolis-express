package com.mfq.foodle.profile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.UserOrdersAdapter;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileOrdersFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecycler;
    private UserOrdersAdapter mAdapter;

    public ProfileOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile_orders, container, false);
        mRecycler = root.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new UserOrdersAdapter();
        SlideInBottomAnimationAdapter adapter = new SlideInBottomAnimationAdapter(mAdapter);
        adapter.setFirstOnly(true);
        mRecycler.setAdapter(adapter);
        mRecycler.setHasFixedSize(true);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter.add();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), ProfileOrdersFragment.class.getSimpleName(), Toast.LENGTH_SHORT).show();
    }

}
