package com.mfq.foodle.coustemviews;

import android.content.Context;
import android.support.design.chip.Chip;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.mfq.foodle.R;
import com.mfq.foodle.models.product.Preference;


public class DynamicChip extends Chip {

    /**
     * Used to get the object from the view to get the data from it,
     * because chips get inflated at runtime and added to the ChipGroup dynamically
     */
    private Preference mPreference;

    public Preference getPreferenceObject() {
        return mPreference;
    }

    public void setPreferenceObject(Preference preference) {
        mPreference = preference;
    }

    public DynamicChip(Context context) {
        super(context);
        updateChip();

    }

    public DynamicChip(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateChip();

    }

    public DynamicChip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        updateChip();
    }

    private void updateChip() {
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                setChipStrokeColorResource(R.color.white);
                setChipStrokeWidthResource(R.dimen.chip_stroke_width);
                if (checked) {
                    setChipBackgroundColorResource(R.color.chipBackgroundColorSelected);
                } else {
                    setChipBackgroundColorResource(R.color.chipBackgroundColor);


                }

            }
        });
    }


}
