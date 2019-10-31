package com.mfq.foodle.frgments;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

public abstract class BottomBarFragment extends Fragment {

    public abstract void onBottomBarAttached(BottomAppBar bottomAppBar);
    public abstract void onFabAttached(FloatingActionButton fab);
}
