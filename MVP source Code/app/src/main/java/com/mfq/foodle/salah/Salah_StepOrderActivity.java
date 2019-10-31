package com.mfq.foodle.salah;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mfq.foodle.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class Salah_StepOrderActivity extends AppCompatActivity implements StepperLayout.StepperListener {
    public static final String CURRENT_STEP_POSITION_KEY = "STEP_KEY";

    private StepperLayout mStepperLayout;

    private FloatingActionButton fabCart;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salah_activity_step_order);
        fabCart = findViewById(R.id.fabCart);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));
        mStepperLayout.setListener(this);


        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStepperLayout.onTabClicked(++index);
            }
        });

    }

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {
        index = newStepPosition;

    }

    @Override
    public void onReturn() {

    }


    public static class MyStepperAdapter extends AbstractFragmentStepAdapter {


        public MyStepperAdapter(FragmentManager fm, Context context) {
            super(fm, context);
        }

        @Override
        public Step createStep(int position) {
            switch (position) {
                case 0:
                    return Salah_PickMealFragment.newInstance();

                case 1:
                    return Salah_PickDessertFragment.newInstance();
                case 2:
                    return Salah_AddLocationFragment.newInstance();

                case 3:
                    return Salah_OrderFragment.newInstance();
            }

            return Salah_PickDessertFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @NonNull
        @Override
        public StepViewModel getViewModel(@IntRange(from = 0) int position) {
            //Override this method to set Step title for the Tabs, not necessary for other stepper types
            switch (position) {
                case 0:
                    return new StepViewModel.Builder(context)
                            .setTitle(R.string.step1) //can be a CharSequence instead
                            .create();

                case 1:
                    return new StepViewModel.Builder(context)
                            .setTitle(R.string.step2) //can be a CharSequence instead
                            .create();
                case 2:
                    return new StepViewModel.Builder(context)
                            .setTitle(R.string.addLocation) //can be a CharSequence instead
                            .create();
                case 3:
                    return new StepViewModel.Builder(context)
                            .setTitle(R.string.order) //can be a CharSequence instead
                            .create();
            }

            return new StepViewModel.Builder(context)
                    .setTitle("") //can be a CharSequence instead
                    .create();
        }
    }
}
