package com.mfq.foodle.salah;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;


public class Salah_ProfileAddressFragment extends Fragment {


    RecyclerView mAddressesRecycler;
    public static Salah_ProfileAddressFragment newInstance() {
        return new Salah_ProfileAddressFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.salah_fragment_profile_address, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View inflate) {
        mAddressesRecycler = inflate.findViewById(R.id.mAddressesRecycler);
        mAddressesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Salah_AddressAdapter addressAdapter =new Salah_AddressAdapter(getContext());
        mAddressesRecycler.setAdapter(addressAdapter);
    }

}
