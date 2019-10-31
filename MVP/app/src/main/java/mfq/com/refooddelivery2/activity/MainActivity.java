package mfq.com.refooddelivery2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.fragments.CartFragment;
import mfq.com.refooddelivery2.fragments.HomeFragment;
import mfq.com.refooddelivery2.helper.TransitionHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FloatingActionButton mFab;
    private BottomSheetBehavior<View> bottomDrawerBehavior;
    private BottomAppBar mBottomBar;
    private View mScrim;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View view, int i) {
            if (i == BottomSheetBehavior.STATE_HIDDEN) {
                mScrim.setVisibility(View.GONE);
            } else {
                mScrim.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onSlide(@NonNull View view, float v) {

        }
    };


    private void setupWindowAnimations() {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP)
            return;
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBar = findViewById(R.id.bottomBar);
        setSupportActionBar(mBottomBar);
        mFab = findViewById(R.id.fabCart);
        mScrim = findViewById(R.id.scrimView);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        setupWindowAnimations();


        setUpNavHeader(navigationView);
        setUpBottomDrawer();
        mFab.setOnClickListener(this);
        mScrim.setOnClickListener(v -> closeDrawer());
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        attachFragment(new HomeFragment(), false);

    }

    private void attachFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment == null)
            return;
        if (fragment instanceof HomeFragment)
            TransitionHelper.addSlideEnterTransitionToFragment(this, fragment, Gravity.BOTTOM, true);
        else
            TransitionHelper.addExplodeEnterTransitionToFragment(this, fragment, true);

        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack)
            replace.addToBackStack(fragment.getClass().getSimpleName());
        replace.commit();
        closeDrawer();

    }

    private void setUpNavHeader(NavigationView navigationView) {
        if (navigationView.getHeaderCount() == 0)
            return;
        View view = navigationView.getHeaderView(0);
        View icon = view.findViewById(R.id.nav_close_icon);
        icon.setOnClickListener(v -> closeDrawer());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mFab.setOnClickListener(this);
        switch (item.getItemId()) {
//            case R.id.nav_assistant:
//                startActivity(new Intent(this, FoodleAssistantAI.class));
//                closeDrawer();
//                return false;
//            case R.id.nav_home:
//                attachFragment(new HomeFragment(), false);
//                break;
//            case R.id.nav_profile:
//                attachFragment(new ProfileFragment(), false);
//                break;
//            case R.id.nav_cart:
//                attachFragment(new CartFragment(), true);
//                break;
//            case R.id.nav_settings:
//                break;
//            case R.id.nav_feedback:
//                attachFragment(new HelpFeedback(), true);
//
//                break;
//            case R.id.nav_about:
//                break;
//            case R.id.nav_search:
//                attachFragment(new SearchFragment(), true);
//                break;
//            case R.id.nav_drive:
//                attachFragment(new FoodleDriveFragment(), true);
//                break;
        }

        closeDrawer();
        return true;


    }

    @Override
    public void onBackPressed() {
        mFab.setOnClickListener(this);
        if (bottomDrawerBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
            closeDrawer();
        } else if (mFab.isExpanded()) {
            mFab.setExpanded(false);
            mBottomBar.setVisibility(View.VISIBLE);
        } else
            super.onBackPressed();
    }

    private void closeDrawer() {
        bottomDrawerBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mScrim.setVisibility(View.GONE);
    }

    private void openDrawer() {
        bottomDrawerBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        mScrim.setVisibility(View.VISIBLE);
    }

    protected void setUpBottomDrawer() {
        View bottomDrawer = findViewById(R.id.bottom_drawer);
        bottomDrawerBehavior = BottomSheetBehavior.from(bottomDrawer);
        bottomDrawerBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomDrawerBehavior.setBottomSheetCallback(mBottomSheetCallback);
        mBottomBar.setNavigationOnClickListener(v -> openDrawer());
        mBottomBar.replaceMenu(R.menu.nav_menu);
    }


    @Override
    public void onClick(View v) {
        CartFragment fragment = new CartFragment();
        fragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide));
        fragment.setAllowEnterTransitionOverlap(true);
        fragment.setAllowReturnTransitionOverlap(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.cartFragment, fragment, CartFragment.class.getSimpleName()).addToBackStack(CartFragment.class.getSimpleName()).commit();
        startActivity(new Intent(this, CartActivity.class));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
