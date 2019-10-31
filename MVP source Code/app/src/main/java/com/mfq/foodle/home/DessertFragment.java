package com.mfq.foodle.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.FoodAdapter;
import com.mfq.foodle.decoreator.HomeItemDecoretore;
import com.mfq.foodle.helper.ApiManager;
import com.mfq.foodle.models.product.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class DessertFragment extends Fragment {

    private RecyclerView mRecycler;
    private FoodAdapter mAdapter;
    private View mProgress;

    public DessertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_meals, container, false);
        mRecycler = root.findViewById(R.id.recycler);
        mProgress = root.findViewById(R.id.progress);
        int columnNumber = getResources().getInteger(R.integer.home_grid_columns);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), columnNumber));
        mAdapter = new FoodAdapter();
        mRecycler.setAdapter(mAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.home_list_spacing);
        mRecycler.addItemDecoration(new HomeItemDecoretore(columnNumber, spacingInPixels, true, 0));
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initApi();
    }

    private void initApi() {
        Observable.just(ApiManager.loaddessert(getContext()))
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
                        mProgress.setVisibility(View.GONE);
                    }
                });
    }



}
