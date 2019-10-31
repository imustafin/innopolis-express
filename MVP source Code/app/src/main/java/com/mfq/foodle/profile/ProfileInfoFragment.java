package com.mfq.foodle.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mfq.foodle.R;
import com.mfq.foodle.activities.AddPhoneNumberActivity;
import com.mfq.foodle.activities.ProfileEditInfoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileInfoFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = ProfileInfoFragment.class.getSimpleName();


    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private ImageView imgHeader;
    private NestedScrollView mScroll;
    private CoordinatorLayout mParent;

    public ProfileInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile_info, container, false);
        mAppBarLayout = root.findViewById(R.id.appBar);
        imgHeader = root.findViewById(R.id.img_header);
        mScroll = root.findViewById(R.id.scroll);
        mParent = root.findViewById(R.id.parent);
        mCollapsingToolbarLayout = root.findViewById(R.id.collapsing_toolbar);

        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            // FIXME find the right value
            if (verticalOffset < -500) {
                //toolbar is collapsed here
                //write your code here
                imgHeader.setImageResource(R.drawable.sad_user);
            } else {
                imgHeader.setImageResource(R.drawable.profile_img);
            }

        });
        imgHeader.setOnClickListener(this);
        root.findViewById(R.id.profile_change_number_button).setOnClickListener(v->startActivity(new Intent(getContext(), AddPhoneNumberActivity.class)));
        root.findViewById(R.id.edit_info).setOnClickListener(v->startActivity(new Intent(getContext(), ProfileEditInfoActivity.class)));


        return root;
    }






    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), ProfileInfoFragment.class.getSimpleName(), Toast.LENGTH_SHORT).show();
    }
}
