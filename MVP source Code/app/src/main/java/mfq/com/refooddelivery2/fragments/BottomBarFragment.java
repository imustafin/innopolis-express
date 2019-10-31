package mfq.com.refooddelivery2.fragments;


import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

public abstract class BottomBarFragment extends Fragment {

    public abstract void onBottomBarAttached(BottomAppBar bottomAppBar);
    public abstract void onFabAttached(FloatingActionButton fab);
}
