package com.mfq.foodle.adapters.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.mfq.foodle.R;
import com.mfq.foodle.events.SelectedAddressEvent;

import org.greenrobot.eventbus.EventBus;

public class UserAddressesViewHolder extends RecyclerView.ViewHolder {



    private final TextView mNameTextView;
    private final TextView mDescriptionTextView;
    private final Switch mIsDeafultAddress;

    public UserAddressesViewHolder(@NonNull View itemView) {
        super(itemView);
        mNameTextView = itemView.findViewById(R.id.addressName);
        mDescriptionTextView = itemView.findViewById(R.id.address_describtion);
        mIsDeafultAddress = itemView.findViewById(R.id.default_address);
        Button deltetButton = itemView.findViewById(R.id.address_delete);
        Button editButton = itemView.findViewById(R.id.address_edit);
        itemView.setOnClickListener(v -> EventBus.getDefault().post(new SelectedAddressEvent()));
    }

    public void bind() {

        if(getLayoutPosition()==1){
            mNameTextView.setText("Home Address");
            mDescriptionTextView.setText("7th circle , 34 building");
        }

    }


}
