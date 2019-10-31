package com.mfq.foodle.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.InvoiceAdapter;
import com.mfq.foodle.helper.ApiManager;
import com.mfq.foodle.models.product.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InvoiceActivity extends Activity {

    private RecyclerView mRecycler;
    private InvoiceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        mRecycler = findViewById(R.id.invoice_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setHasFixedSize(true);
        mAdapter = new InvoiceAdapter();
        mRecycler.setAdapter(mAdapter);
        initApi();
    }



    private void initApi() {
        Observable.just(ApiManager.loaddessert(this))
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Product> products) {
                        mAdapter.setProductList(products);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
