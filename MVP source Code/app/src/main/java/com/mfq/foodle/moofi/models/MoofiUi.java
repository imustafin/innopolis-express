package com.mfq.foodle.moofi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoofiUi {

    @SerializedName("chips")
    @Expose
    private List<String> mSuggestionChips = new ArrayList<>();

    @SerializedName("recycler_item_view")
    @Expose
    private String mRecyclerItemView = "";

    public List<String> getSuggestionChips() {
        return mSuggestionChips;
    }

    public String getRecyclerItemView() {
        return mRecyclerItemView;
    }


    public static class MoofiRecyclerItemType {
        // MAKE ADDRESS JUST HOME AND WORK
        public static final String ITEM_VIEW_ADDRESS="address-list";
        public static final String ITEM_VIEW_DESSERT="dessert-list";
        public static final String ITEM_VIEW_MEALS="meals-list";
        public static final String ITEM_VIEW_ORDERED="ordered-product";
        public static final String ITEM_VIEW_DEFAULT_ADDRESS="default-address";
        public static final String ITEM_VIEW_FOODLE_DRIVE="foodle-drive";
        public static final String ITEM_VIEW_CART_ITEM="cart-items";
        public static final String ITEM_VIEW_CART_VIEW="cart-view";
        public static final String ITEM_VIEW_INVOICE="invoice-view";
        public static final String ITEM_VIEW_MOOFI="moofi-list";
    }

    String ff = "  هل تريد إضافة <?$meals.join(\"و\")?>  الى سلة التسوق";



}
