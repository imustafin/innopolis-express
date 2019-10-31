package com.mfq.foodle.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mfq.foodle.R;
import com.mfq.foodle.adapters.FoodDetailsAdapter;
import com.mfq.foodle.cart.CartFragment;
import com.mfq.foodle.coustemviews.PriceView;
import com.mfq.foodle.coustemviews.QuantityPicker;
import com.mfq.foodle.models.product.Product;


public class FoodDetails extends AppCompatActivity implements View.OnClickListener, QuantityPicker.OnQtyChangedListener {
    private static final String KEY_PRODUCT = "PRODUCT";
    private static final String KEY_POSITION = "POSITION";

    private RecyclerView mRecycler;
    private FoodDetailsAdapter mAdapter;
    private ImageView mProductImageView;
    private TextView mDescriptionTextView;
    private PriceView mPriceView;
    private TextView mNameTextView;
    private QuantityPicker mQuantityPicker;
    private View mScroll;
    private FloatingActionButton mFab;
    private View mBackIcon;
    private View mScrim;
    private Button mAddToCartButton;
    private Scene mCartAddProductScene;
    private ViewGroup mCartContainer;
    private ViewGroup mParentContainer;
    private ViewGroup mSceneRoot;
    private Scene mCartDefaultScene;
    private Scene mCartSceneOpen;
    private boolean mIsCartOpend;
    private ViewGroup mCartFragmentContainer;
    private View mCartOpendScrim;

    public static Intent getDetailsIntent(Context context, Product product, int postion) {
        Intent intent = new Intent(context, FoodDetails.class);
        intent.putExtra(KEY_PRODUCT, product);
        intent.putExtra(KEY_POSITION, postion);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        mProductImageView = findViewById(R.id.details_img);
        mDescriptionTextView = findViewById(R.id.details_description);
        mQuantityPicker = findViewById(R.id.quantity_picker);
        mNameTextView = findViewById(R.id.details_name);
        mPriceView = findViewById(R.id.details_price_view);
        mScroll = findViewById(R.id.scroll);
        mRecycler = findViewById(R.id.recycler);
        mScrim = findViewById(R.id.scrimView);
        mCartOpendScrim = findViewById(R.id.scrimViewCart);
        mAddToCartButton = findViewById(R.id.details_add_to_cart);
        mCartContainer = findViewById(R.id.cartContainer);
        mParentContainer = findViewById(R.id.parent);
        mCartFragmentContainer = findViewById(R.id.cartFragment);
        mFab = findViewById(R.id.fab);
        mFab.hide();
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setHasFixedSize(true);
        mRecycler.setNestedScrollingEnabled(false);


        bindProduct(getIntent().getParcelableExtra(KEY_PRODUCT));
        mBackIcon = findViewById(R.id.details_back);
        mBackIcon.setOnClickListener(v -> onBackPressed());
        mAddToCartButton.setOnClickListener(this);
        mCartContainer.setOnClickListener(this);

        supportPostponeEnterTransition();
        setupWindowAnimations();

    }

    private void setupWindowAnimations() {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP)
            return;
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_photo));
        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.slide);
        slide.excludeTarget(mFab, true);
        slide.excludeTarget(mScrim, true);
        slide.excludeTarget(R.id.appBar, true);
        slide.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                mRecycler.setVisibility(View.VISIBLE);
                mRecycler.animate().alpha(1).start();
                mScrim.setVisibility(View.VISIBLE);
                mFab.show();
                mBackIcon.animate().alpha(1).scaleX(1).scaleY(1).start();
                positionCartView();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        getWindow().setEnterTransition(slide);
    }

    private void positionCartView() {
        mIsCartOpend = false;
        TransitionManager.beginDelayedTransition(mCartContainer, TransitionInflater.from(FoodDetails.this).inflateTransition(R.transition.slide));
        int translationX = mParentContainer.getWidth() - getResources().getDimensionPixelSize(R.dimen.action_bar_size);
        int translationY = mParentContainer.getHeight() - getResources().getDimensionPixelSize(R.dimen.action_bar_size);
        mCartContainer.setTranslationX(translationX);
        mCartContainer.setTranslationY(translationY);
        mCartContainer.setVisibility(View.VISIBLE);
    }

    private void returnCartView() {
        mIsCartOpend = false;
        Transition transition = TransitionInflater.from(FoodDetails.this).inflateTransition(R.transition.arc_transtion);
        transition.setDuration(250);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                hideCartScrim();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                mCartFragmentContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.beginDelayedTransition(mParentContainer, transition);
        int translationX = mParentContainer.getWidth() - getResources().getDimensionPixelSize(R.dimen.action_bar_size);
        int translationY = mParentContainer.getHeight() - getResources().getDimensionPixelSize(R.dimen.action_bar_size);
        mCartContainer.setTranslationX(translationX);
        mCartContainer.setTranslationY(translationY);

    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        // to make the whole view slide down when exit transition is start, remove it and try open and exit the activity to understand
//        mScroll.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundColor));
        int translationX = mParentContainer.getWidth() - getResources().getDimensionPixelSize(R.dimen.action_bar_size);
        int translationY = mParentContainer.getHeight() - getResources().getDimensionPixelSize(R.dimen.action_bar_size);
        mCartContainer.setTranslationX(translationX);
        mCartContainer.setTranslationY(translationY);

    }

    private void bindProduct(Product product) {
        if (product == null) {
            return;
        }
        mNameTextView.setText(product.getName());
        mDescriptionTextView.setText(product.getDescription());
        mPriceView.setPrice(product.getPrice());
        mPriceView.getPriceTextView().setGravity(Gravity.END);
        if (product.getMealPreferences() != null) {
            mAdapter = new FoodDetailsAdapter();
            mRecycler.setAdapter(mAdapter);
            mAdapter.setProduct(product);
            mQuantityPicker.setOnQtyChangedListener(this);
        }

        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.freakah).dontTransform();
        Glide.with(this).load(product.getImgUrl()).apply(requestOptions).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                supportStartPostponedEnterTransition();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                supportStartPostponedEnterTransition();
                return false;
            }
        }).into(mProductImageView);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details_add_to_cart:

                break;
            case R.id.cartContainer:
                showCartScrim();
                mIsCartOpend = true;
                Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.arc_transtion);
                TransitionManager.beginDelayedTransition(mParentContainer, transition);
                mCartContainer.setTranslationX(0);
                mCartContainer.setTranslationY(0);
                CartFragment fragment = new CartFragment();
                fragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide));
                fragment.setAllowEnterTransitionOverlap(true);
                fragment.setAllowReturnTransitionOverlap(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.cartFragment, fragment, CartFragment.class.getSimpleName()).addToBackStack(CartFragment.class.getSimpleName()).commit();
                break;

        }
    }

    private void showCartScrim() {
        mCartOpendScrim.setVisibility(View.VISIBLE);
        mCartOpendScrim.animate().alpha(1).start();
    }

    private void hideCartScrim() {
        mCartOpendScrim.animate().alpha(0).withEndAction(() -> mCartOpendScrim.setVisibility(View.INVISIBLE)).start();
    }


    public void makeAddToCartScene() {
        Transition auto = new AutoTransition();
        auto.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Transition auto = new AutoTransition();
                auto.excludeTarget(R.id.small_product, true);
                TransitionManager.go(mCartDefaultScene, auto);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.go(mCartAddProductScene, auto);
    }

    @Override
    public void onQtyChanged(int qty) {

    }

    @Override
    public void onQtyIncreased(int qty) {
        mAdapter.addItem(qty);
        mRecycler.smoothScrollToPosition(qty);
    }

    @Override
    public void onBackPressed() {
        if (mIsCartOpend) {
            getSupportFragmentManager().popBackStack();
            mCartFragmentContainer.setVisibility(View.INVISIBLE);
            returnCartView();
            return;
        }
        int duration = 150;
        mFab.animate().setDuration(duration).scaleY(0).scaleX(0).withEndAction(super::onBackPressed).start();
        mBackIcon.animate().setDuration(duration).alpha(0).start();
        mScrim.animate().setDuration(duration).alpha(0).start();
        mRecycler.animate().setDuration(duration).alpha(0).start();
    }

    @Override
    public void onQtyDecreased(int qty) {
        if (qty != 1)
            mAdapter.removeItem(qty);

    }
}
