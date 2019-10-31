package mfq.com.refooddelivery2.fragments.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Explode;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.helper.ApiManager;
import mfq.com.refooddelivery2.helper.HomeItemDecoretore;
import mfq.com.refooddelivery2.helper.TransitionHelper;
import mfq.com.refooddelivery2.models.product.Product;
import mfq.com.refooddelivery2.recycler.FoodAdapter;


public class MealsFragment extends Fragment {
    private static final String TAG = MealsFragment.class.getSimpleName();

    private RecyclerView mRecycler;
    private FoodAdapter mAdapter;
    private View mProgress;

    public MealsFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_meals, container, false);
        mProgress = root.findViewById(R.id.progress);
        mRecycler = root.findViewById(R.id.recycler);
        int columnNumber = getResources().getInteger(R.integer.home_grid_columns);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), columnNumber));
        mRecycler.setHasFixedSize(true);
        mAdapter = new FoodAdapter();
        SlideInBottomAnimationAdapter adapter = new SlideInBottomAnimationAdapter(mAdapter);
        adapter.setFirstOnly(true);
        adapter.setDuration(350);
        mRecycler.setAdapter(adapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.home_list_spacing);
        mRecycler.addItemDecoration(new HomeItemDecoretore(columnNumber, spacingInPixels, true, 0));


        return root;
    }

    public void onClick(View clickedView) {
        // save rect of view in screen coordinates
        final Rect viewRect = new Rect();
        clickedView.getGlobalVisibleRect(viewRect);

        // create Explode transition with epicenter
        Transition explode = new Explode();
                explode.setEpicenterCallback(new Transition.EpicenterCallback() {
                    @Override
                    public Rect onGetEpicenter(Transition transition) {
                        return viewRect;
                    }
                });
        explode.setDuration(1000);
        explode.excludeTarget(clickedView, true);
        TransitionManager.beginDelayedTransition(mRecycler, explode);

        // remove all views from Recycler View
        mRecycler.setAdapter(null);



    }
    @SuppressWarnings("unchecked") void transitionTo(Intent i, Context c) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants((AppCompatActivity) c, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) c, pairs);
        c.startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initApi();

    }

    private void initApi() {
        Observable.just(ApiManager.loadFood(getContext()))
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
