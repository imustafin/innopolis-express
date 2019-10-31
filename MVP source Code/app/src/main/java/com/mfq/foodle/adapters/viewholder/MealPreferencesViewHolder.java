package com.mfq.foodle.adapters.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.chip.ChipGroup;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mfq.foodle.R;
import com.mfq.foodle.coustemviews.DynamicChip;
import com.mfq.foodle.models.product.MealPreference;
import com.mfq.foodle.models.product.Preference;

import java.util.List;

public class MealPreferencesViewHolder extends RecyclerView.ViewHolder {

    private final TextView mHeaderTextView;
    private final TextView mDescriptionTextView;
    private final ChipGroup mChipGroup;
    private final Context mContext;

    public MealPreferencesViewHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mHeaderTextView = itemView.findViewById(R.id.preference_header);
        mChipGroup = itemView.findViewById(R.id.preference_chip_group);
        mDescriptionTextView = itemView.findViewById(R.id.preference_description);


    }

    public void bind(MealPreference mealPreference) {
        mHeaderTextView.setText(mealPreference.getName());
        mDescriptionTextView.setText(mealPreference.getDescription());
        initChipGroup(mealPreference);

    }

    private void initChipGroup(MealPreference mealPreference) {
        mChipGroup.removeAllViews();
        List<Preference> preferences = mealPreference.getPreference();
        for (Preference preference : preferences) {
            DynamicChip chip =
                    (DynamicChip) LayoutInflater.from(mContext).inflate(getChipGroupItem(mealPreference.getSelectionType()), mChipGroup, false);
            chip.setText(preference.getText());
            chip.setPreferenceObject(preference);
            chip.setOnClickListener(view -> {
                Preference preferenceObject = ((DynamicChip) view).getPreferenceObject();
            });
            mChipGroup.addView(chip);
        }
    }

    private int getChipGroupItem(String type) {
        mChipGroup.setSingleLine(false);
        mChipGroup.setSingleSelection(false);

        switch (type) {
            case MealPreference.TYPE_ENTRY:
                return R.layout.chip_item_entry;
            case MealPreference.TYPE_FILTER:
                return R.layout.chip_item_filter;
            case MealPreference.TYPE_SINGLE:
            default:
                mChipGroup.setSingleLine(true);
                mChipGroup.setSingleSelection(true);
                return R.layout.chip_item_choice;
        }

    }


}
