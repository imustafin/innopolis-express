package com.mfq.foodle.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.SpaceViewHolder;
import com.mfq.foodle.adapters.viewholder.UserOrdersViewHolder;


public class UserOrdersAdapter extends RecyclerView.Adapter {


    private static final int SPACE = 0;
    private static final int LAYOUT = 1;

    int size = 0;

    public void add(){
        size=10;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == SPACE)
            return new SpaceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.space_layout, viewGroup, false));
        else
            return new UserOrdersViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_orders_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? SPACE : LAYOUT;
    }

    @Override
    public int getItemCount() {
        return 1 +size;
    }


}
