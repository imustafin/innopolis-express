package com.mfq.foodle.events;

import com.mfq.foodle.models.product.Product;

public class SelectedProductEvent  {
    public final Product mProduct;

    public SelectedProductEvent(Product product) {
        mProduct = product;
    }
}
