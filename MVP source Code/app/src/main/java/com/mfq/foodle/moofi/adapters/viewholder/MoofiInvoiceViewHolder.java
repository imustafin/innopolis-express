package com.mfq.foodle.moofi.adapters.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.InvoiceAdapter;
import com.mfq.foodle.models.product.Product;

import java.util.List;

public class MoofiInvoiceViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerView mRecycler;
    private final InvoiceAdapter mAdapter;

    public MoofiInvoiceViewHolder(View view) {
        super(view);

        mRecycler = view.findViewById(R.id.invoice_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecycler.setHasFixedSize(true);
        mAdapter = new InvoiceAdapter();
        mRecycler.setAdapter(mAdapter);
    }

    public void bind(List<Product> products) {
        mAdapter.setProductList(products);
    }
}
