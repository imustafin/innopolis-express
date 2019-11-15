package mfq.com.refooddelivery2.fragments;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.activity.CartActivity;
import mfq.com.refooddelivery2.activity.InvoiceActivity;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Invoices;
import mfq.com.refooddelivery2.models.Product;
import mfq.com.refooddelivery2.recycler.CartItemsAdapter;
import mfq.com.refooddelivery2.utils.RequestStatus;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private CartCompleteOrderTask mCartTask;

    private RecyclerView mCartItemsRecycler;
    private CartItemsAdapter mCartItemsAdapter;
    private EditText mPhone;
    private EditText mAddress;
    private TextView mTotals2;
    private TextView mTotals1;
    private List<Product> mProducts;
    private View mEmpty;
    private TextView totalCount;

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

        root.findViewById(R.id.cart_order).setOnClickListener(view -> attemptProcess());

        totalCount = root.findViewById(R.id.cart_item_count);
        int totalQuantity = Cart.getInstance().getTotalQuantity();
        totalCount.setText(totalQuantity + " item" + (totalQuantity == 1 ? "" : "s"));

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

        int totalQuantity = Cart.getInstance().getTotalQuantity();
        totalCount.setText(totalQuantity + " item" + (totalQuantity == 1 ? "" : "s"));

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
        mCartItemsAdapter = new CartItemsAdapter(() -> {
            mCartItemsAdapter.notifyDataSetChanged();
            if (mProducts.isEmpty()) {
                mEmpty.setVisibility(View.VISIBLE);
            } else checkPrice();

        });
        mCartItemsRecycler.setAdapter(mCartItemsAdapter);
        mCartItemsAdapter.setProductList(Cart.getInstance().getProducts());
    }

    public void attemptProcess(){

        if (mCartTask != null) {
            return;
        }

        mAddress.setError(null);
        mPhone.setError(null);

        String address = mAddress.getText().toString();
        String phone = mPhone.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(address)) {
            mAddress.setError(getString(R.string.error_field_required));
            focusView = mAddress;
            cancel = true;
        }else if(!isAddressValid(address)){
            mAddress.setError(getString(R.string.error_invalid_address));
            focusView = mAddress;
            cancel = true;
        }

        if (TextUtils.isEmpty(phone)) {
            mPhone.setError(getString(R.string.error_field_required));
            focusView = mPhone;
            cancel = true;
        }else if(!isPhoneValid(phone)){
            mPhone.setError(getString(R.string.error_invalid_phone));
            focusView = mPhone;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        } else {
            mCartTask = new CartCompleteOrderTask(phone, address, Cart.getInstance().getProducts());
            mCartTask.execute((Void) null);
        }
    }

    private boolean isAddressValid(String address){
        return address.length() > 0;
    }

    private boolean isPhoneValid(String phone){
        return phone.matches("\\d+") && phone.length() == 11;
    }

    public class CartCompleteOrderTask extends AsyncTask<Void, Void, Boolean>{

        private String phone;
        private String address;
        private List<Product> products;

        public CartCompleteOrderTask(String phone, String address, List<Product> products) {
            this.phone = phone;
            this.address = address;
            this.products = products;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            RequestStatus status;

            try {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                List<Map<String, Object>> products = new ArrayList<>();

                for (Product product : this.products) {
                    Map<String, Object> newProduct = new HashMap<>();

                    newProduct.put("name", product.getName());
                    newProduct.put("price", product.getPrice().getValue());
                    newProduct.put("quantity", product.getQuantity());

                    products.add(newProduct);
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                assert user != null;
                Invoices newInvoice = new Invoices("Pending", user.getEmail(), user.getDisplayName(), products, address, phone);

                db.collection("invoices").add(newInvoice);
                status = RequestStatus.SUCCESS;
            }catch (Exception e){
                e.printStackTrace();
                status = RequestStatus.FAIL;
            }

            return status == RequestStatus.SUCCESS;
        }

        @Override
        protected void onPostExecute(Boolean success) {

            mCartTask = null;

            if (success) {
                String message = "Order is successful 😘 , Admin will call you to confirm the order, be polite 😜 ";
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), InvoiceActivity.class);
                Objects.requireNonNull(getActivity()).finish();
                startActivity(intent);
            } else {
                System.out.println("Error has occurred");
            }
            
        }

        @Override
        protected void onCancelled() {
            mCartTask = null;
        }
    }


}

