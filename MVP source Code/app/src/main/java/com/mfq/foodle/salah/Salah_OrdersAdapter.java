package com.mfq.foodle.salah;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfq.foodle.R;

public class Salah_OrdersAdapter extends RecyclerView.Adapter<Salah_OrdersAdapter.ViewHolder>{


    private Context mContext;

    public Salah_OrdersAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Salah_OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.salah_row_my_orders_profile, viewGroup , false);
        return new Salah_OrdersAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull Salah_OrdersAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
