package com.mfq.foodle.activities;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.mfq.foodle.BuildConfig;
import com.mfq.foodle.R;
import com.mfq.foodle.adapters.FoodAdapter;
import com.mfq.foodle.decoreator.HomeItemDecoretore;
import com.mfq.foodle.frgments.BottomBarFragment;
import com.mfq.foodle.helper.ApiManager;
import com.mfq.foodle.models.product.Product;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BottomBarFragment implements View.OnClickListener, TextWatcher {
    private static final String TAG = SearchFragment.class.getSimpleName();

    private AutoCompleteTextView mSearchTextField;
    private View mClearIcon;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    List<String> mProductNames = new ArrayList<>();
    private FloatingActionButton mFab;
    private BottomAppBar mBottomAppBar;
    private RecyclerView mRecycler;
    private FoodAdapter mAdapter;
    private Observable<List<Product>> mListObservable;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);


        mSearchTextField = root.findViewById(R.id.search_input);
        mClearIcon = root.findViewById(R.id.search_clear);
        mRecycler = root.findViewById(R.id.recycler);
        int columnNumber = getResources().getInteger(R.integer.home_grid_columns);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), columnNumber));
        mRecycler.setHasFixedSize(true);
        mAdapter = new FoodAdapter();
        mRecycler.setAdapter(mAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.home_list_spacing);
        mRecycler.addItemDecoration(new HomeItemDecoretore(columnNumber, spacingInPixels, true, 0));
        mClearIcon.setOnClickListener(this);
        root.findViewById(R.id.close).setOnClickListener(this);
        mSearchTextField.addTextChangedListener(this);


        initApi();

        return root;
    }

    private void initApi() {
        mListObservable = Observable.fromArray(ApiManager.loadFood(getActivity()));
        mListObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(products -> products)
                .map(product -> product.getName())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        mProductNames.add(s);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        initAutocomplete(mProductNames);
                    }
                });
    }

    private void initAutocomplete(List<String> productsName) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, "initAutocomplete() called with: productsName = [" + productsName + "]");
        mSearchTextField.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.search_autocomplete_list_item, productsName));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.close:
                getActivity().onBackPressed();
                break;
            case R.id.search_clear:
                mSearchTextField.setText("");
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mClearIcon.setVisibility(s.length() == 0 ? View.INVISIBLE : View.VISIBLE);
        search(s.toString().toLowerCase());

    }

    private void search(String s) {
        if (s.isEmpty() || mListObservable == null) return;

        mListObservable
                .flatMapIterable(products -> products)
                .filter(product -> product.getName().toLowerCase().contains(s))
                .toList().subscribe(new SingleObserver<List<Product>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable.add(d);

            }

            @Override
            public void onSuccess(List<Product> products) {
                TransitionManager.beginDelayedTransition((ViewGroup) getView().getParent(), TransitionInflater.from(getContext()).inflateTransition(R.transition.slide));
                mAdapter.setProductList(products);

            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onBottomBarAttached(BottomAppBar bottomAppBar) {
        mBottomAppBar = bottomAppBar;
        bottomAppBar.setVisibility(View.GONE);
    }

    @Override
    public void onFabAttached(FloatingActionButton fab) {
        mFab = fab;
        fab.hide();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFab.show();
        mBottomAppBar.setVisibility(View.VISIBLE);
        mDisposable.dispose();
    }
}
