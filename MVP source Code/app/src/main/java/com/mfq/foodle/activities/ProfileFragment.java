package com.mfq.foodle.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.frgments.BottomBarFragment;
import com.mfq.foodle.profile.ProfileAddressesFragment;
import com.mfq.foodle.profile.ProfileInfoFragment;
import com.mfq.foodle.profile.ProfileOrdersFragment;


public class ProfileFragment extends BottomBarFragment implements TabLayout.BaseOnTabSelectedListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FloatingActionButton mFab;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profile_fragment, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = root.findViewById(R.id.container);
        TabLayout tabLayout = root.findViewById(R.id.tabLayout);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        ViewCompat.setNestedScrollingEnabled(mViewPager, false);
        tabLayout.addOnTabSelectedListener(this);
        return root;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // tabLayout.setupWithViewPager(mViewPager) is not working stupid Google;
        mViewPager.setCurrentItem(tab.getPosition());
        switch (tab.getPosition()) {
            case 0:
                mFab.setImageResource(R.drawable.ic_round_edit_24px);
                break;
            case 1:
                mFab.setImageResource(R.drawable.ic_round_add_location_24px);
                break;
            case 2:
                mFab.setImageResource(R.drawable.ic_round_filter_list_24px);
                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onBottomBarAttached(BottomAppBar bottomAppBar) {

    }

    @Override
    public void onFabAttached(FloatingActionButton fab) {
        mFab = fab;
    }


    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                default:
                case 0:
                    fragment = new ProfileInfoFragment();
                    break;
                case 1:
                    fragment = new ProfileAddressesFragment();
                    break;
                case 2:
                    fragment = new ProfileOrdersFragment();
                    break;
            }
//            TransitionHelper.addSlideEnterTransitionToFragment(getContext(), fragment, false);
            return fragment;
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }


    }
}
