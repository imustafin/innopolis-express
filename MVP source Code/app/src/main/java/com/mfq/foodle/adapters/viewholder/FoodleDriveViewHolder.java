package com.mfq.foodle.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mfq.foodle.R;
import com.mfq.foodle.models.FoodleDrive;

public class FoodleDriveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private final TextView mFeesTextView;
    private final TextView mDriveTime;
    private final RadioButton mDriveRadioButton;
    private final View mDivider;
    private FoodleDrive mFoodleDrive;

    public FoodleDriveViewHolder(View itemView) {
        super(itemView);

        mFeesTextView = itemView.findViewById(R.id.drive_fees);
        mDriveTime = itemView.findViewById(R.id.drive_order_time);
        mDriveRadioButton = itemView.findViewById(R.id.drive_radio_button);
        mDivider = itemView.findViewById(R.id.drive_divider);
//        mDriveRadioButton.setOnCheckedChangeListener(this);
        itemView.setOnClickListener(this);
    }

    public void bind(FoodleDrive foodleDrive) {
        mFoodleDrive = foodleDrive;
        mFeesTextView.setText(foodleDrive.getFees());
        mDriveTime.setText(foodleDrive.getTime());
        if (foodleDrive.getFees().contains("3"))
            mDivider.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        setDriveChecked();
    }

    private void setDriveChecked() {
        mDriveRadioButton.setChecked(!mFoodleDrive.isSelected());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setDriveChecked();

    }
}
