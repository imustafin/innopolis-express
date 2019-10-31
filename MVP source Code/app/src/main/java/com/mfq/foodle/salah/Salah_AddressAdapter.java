package com.mfq.foodle.salah;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.mfq.foodle.R;

public class Salah_AddressAdapter extends RecyclerView.Adapter<Salah_AddressAdapter.ViewHolder>{


    private Context mContext;

    public Salah_AddressAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Salah_AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.salah_row_addresses_view, viewGroup , false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull Salah_AddressAdapter.ViewHolder viewHolder, int i) {

        switch (i){
            case 0:
                viewHolder.imgAddressIcon.setImageResource(R.drawable.salah_briefcase);
                break;

                case 1:
                viewHolder.imgAddressIcon.setImageResource(R.drawable.salah_round_home_black_24dp);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAddressIcon;
        TextView txtAddressDetails;
        TextView txtAddressTitle;
        Switch btnSwitchAddress;
        Button btnAddressDelete;
        Button btnAddressEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAddressIcon = itemView.findViewById(R.id.imgAddressIcon);
            txtAddressDetails = itemView.findViewById(R.id.txtAddressDetails);
            txtAddressTitle = itemView.findViewById(R.id.txtAddressTitle);
            btnSwitchAddress = itemView.findViewById(R.id.btnSwitchAddress);
            btnAddressDelete = itemView.findViewById(R.id.btnAddressDelete);
            btnAddressEdit = itemView.findViewById(R.id.btnAddressEdit);

        }
    }
}
