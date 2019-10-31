package mfq.com.refooddelivery2.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.recycler.CartItemsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements View.OnClickListener {


    private RecyclerView mCartItemsRecycler;
    private CartItemsAdapter mCartItemsAdapter;
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
        mCartItemsRecycler = root.findViewById(R.id.cart_recycler);

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildItemRecycler();
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
    public void onClick(View v) {
        if (mBottomAppBar != null)
            mBottomAppBar.setVisibility(View.VISIBLE);
        getActivity().onBackPressed();
    }
}
