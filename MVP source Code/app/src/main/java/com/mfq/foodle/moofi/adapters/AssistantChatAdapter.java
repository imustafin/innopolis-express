package com.mfq.foodle.moofi.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfq.foodle.R;
import com.mfq.foodle.adapters.viewholder.FoodViewHolder;
import com.mfq.foodle.adapters.viewholder.FoodleDriveViewHolder;
import com.mfq.foodle.adapters.viewholder.GifViewHolder;
import com.mfq.foodle.adapters.viewholder.UserAddressesViewHolder;
import com.mfq.foodle.models.product.Product;
import com.mfq.foodle.moofi.Assistant;
import com.mfq.foodle.moofi.ChatViewHolder;
import com.mfq.foodle.moofi.adapters.viewholder.MoofiCanDoViewHolder;
import com.mfq.foodle.moofi.adapters.viewholder.MoofiCartItemViewHolder;
import com.mfq.foodle.moofi.adapters.viewholder.MoofiCartViewViewHolder;
import com.mfq.foodle.moofi.adapters.viewholder.MoofiInvoiceViewHolder;
import com.mfq.foodle.moofi.adapters.viewholder.MoofiProductListViewHolder;

import java.util.ArrayList;
import java.util.List;


public class AssistantChatAdapter extends RecyclerView.Adapter {

    private final static int TYPE_GIF = 1;
    private final static int TYPE_CHAT = 2;
    private final static int TYPE_PRODUCT_LIST = 3;
    private final static int TYPE_VIEW_PRODUCT = 4;
    private final static int TYPE_DEFAULT_ADDRESS = 5;
    private final static int TYPE_ADDRESS_LIST = 6;
    private final static int TYPE_FOODLE_DRIVE = 7;
    private final static int TYPE_CART_ITEM = 8;
    private final static int TYPE_CART_VIEW = 9;
    private final static int TYPE_INVOICE_VIEW = 10;
    private final static int TYPE_MOOFI_LIST = 11;

    List<Assistant> mAssistants = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_MOOFI_LIST:
                return new MoofiCanDoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.moofi_can_do, viewGroup, false));
            case TYPE_INVOICE_VIEW:
                return new MoofiInvoiceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.moofi_invoice, viewGroup, false));
            case TYPE_CART_VIEW:
                return new MoofiCartViewViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_container, viewGroup, false));
            case TYPE_CART_ITEM:
                return new MoofiCartItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.moofi_cart_item, viewGroup, false));
            case TYPE_FOODLE_DRIVE:
                return new FoodleDriveViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.moofi_foodle_drive_item, viewGroup, false));
            case TYPE_ADDRESS_LIST:
                return new UserAddressesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.moofi_profile_adresses_list, viewGroup, false));
            case TYPE_DEFAULT_ADDRESS:
                return new UserAddressesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.moofi_profile_adresses_item, viewGroup, false));
            case TYPE_PRODUCT_LIST:
                return new MoofiProductListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false));
            case TYPE_VIEW_PRODUCT:
                return new FoodViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_card_list_item_horizental, viewGroup, false));
            case TYPE_GIF:
                return new GifViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gif_layout, viewGroup, false));
            default:
            case TYPE_CHAT:
                return new ChatViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_layout, viewGroup, false));

        }
    }


    public void addFoodleDrive() {
        Assistant e = new Assistant(TYPE_FOODLE_DRIVE);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }

    public void addMoofiCanDo() {
        Assistant e = new Assistant(TYPE_MOOFI_LIST);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }

    public void addListAddress() {
        Assistant e = new Assistant(TYPE_ADDRESS_LIST);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }

    public void addDefaultAddress() {
        Assistant e = new Assistant(TYPE_DEFAULT_ADDRESS);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }


    public void addProduct(Product product) {
        Assistant e = new Assistant(TYPE_VIEW_PRODUCT);
        e.setProduct(product);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }


    public void addProductList(List<Product> products) {
        Assistant e = new Assistant(TYPE_PRODUCT_LIST);
        e.setProducts(products);
        mAssistants.add(e);
        notifyDataSetChanged();
    }


    public void addUserChat(String text) {
        Assistant e = new Assistant(text, Assistant.FROM_USER);
        e.setType(TYPE_CHAT);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }

    public void addAssistantChat(String text) {
        Assistant e = new Assistant(text, Assistant.FROM_ASSISTANT);
        e.setType(TYPE_CHAT);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }

    public void addGif(String imgURL) {
        Assistant e = new Assistant(imgURL);
        e.setType(TYPE_GIF);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }

    public void addInvoiceView(List<Product> products) {
        Assistant e = new Assistant(TYPE_INVOICE_VIEW);
        e.setProducts(products);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }


    public void addCartView(List<Product> products, String foodleDrive, String Phone, String address) {
        Assistant e = new Assistant(TYPE_CART_VIEW);
        e.setProducts(products);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }

    public void addCartItems(List<Product> products) {
        Assistant e = new Assistant(TYPE_CART_ITEM);
        e.setProducts(products);
        mAssistants.add(e);
        notifyItemInserted(mAssistants.size() - 1);
    }


    @Override
    public int getItemViewType(int position) {
        return mAssistants.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case TYPE_INVOICE_VIEW:
                ((MoofiInvoiceViewHolder) viewHolder).bind(mAssistants.get(i).getProducts());
                break;
            case TYPE_CART_VIEW:
                ((MoofiCartViewViewHolder) viewHolder).bind(mAssistants.get(i).getProducts());
                break;
            case TYPE_VIEW_PRODUCT:
                ((FoodViewHolder) viewHolder).bind(mAssistants.get(i).getProduct());
                break;
            case TYPE_CART_ITEM:
                ((MoofiCartItemViewHolder) viewHolder).bind(mAssistants.get(i).getProducts());
                break;
            case TYPE_PRODUCT_LIST:
                ((MoofiProductListViewHolder) viewHolder).bind(mAssistants.get(i).getProducts());
                break;
            case TYPE_GIF:
                ((GifViewHolder) viewHolder).bind(mAssistants.get(i).getGifURL());
                break;
            case TYPE_CHAT:
                ((ChatViewHolder) viewHolder).bind(mAssistants.get(i));


        }
    }


    @Override
    public int getItemCount() {
        return mAssistants.size();
    }


}
