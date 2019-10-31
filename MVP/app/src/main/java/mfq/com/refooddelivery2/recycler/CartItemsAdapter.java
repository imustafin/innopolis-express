package mfq.com.refooddelivery2.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.models.Product;


public class CartItemsAdapter extends RecyclerView.Adapter {

    List<Product> mProducts = new ArrayList<>();

    public void setProductList(List<Product> mealList) {
        mProducts = mealList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CartItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        ((CartItemViewHolder) viewHolder).bind(mProducts.get(i));
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
