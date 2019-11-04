package mfq.com.refooddelivery2.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Product;
import mfq.com.refooddelivery2.recycler.CartItemsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements View.OnClickListener {


    private RecyclerView mCartItemsRecycler;
    private CartItemsAdapter mCartItemsAdapter;
    private EditText mPhone;
    private EditText mAddress;
    private TextView mTotals2;
    private TextView mTotals1;
    private List<Product> mProducts;
    private View mEmpty;

    public CartFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        mCartItemsRecycler = root.findViewById(R.id.cart_recycler);
        mPhone = root.findViewById(R.id.cart_phone);
        mAddress = root.findViewById(R.id.cart_address);
        mTotals2 = root.findViewById(R.id.cart_total_price);
        mTotals1 = root.findViewById(R.id.cart_total_first);
        mEmpty = root.findViewById(R.id.cart_empty);

        root.findViewById(R.id.cart_order).setOnClickListener(this);

        mProducts = Cart.getInstance().getProducts();

        if (mProducts.isEmpty()) {
            mEmpty.setVisibility(View.VISIBLE);
        } else
            checkPrice();

        return root;

    }

    private void checkPrice() {
        int totals = 0;
        for (Product product : mProducts) {
            totals += product.getPrice().getValue() * product.getQuantity();
        }
        mTotals1.setText(String.valueOf(totals));
        mTotals2.setText(String.valueOf(totals));
        mTotals1.append(" \u20BD");
        mTotals2.append(" \u20BD");

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
        String message = "Order is successful 😘 , Admin will call you to confirm the order, be polite 😜 ";
        if (mPhone.getText().toString().isEmpty()) {
            message = "Please Enter your phone number correctly 😡";
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            return;
        }
        if (mAddress.getText().toString().isEmpty()) {
            message = "Please Enter your Address Dorm number and room number 🧐";
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            return;

        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        getActivity().finish();
    }
}

