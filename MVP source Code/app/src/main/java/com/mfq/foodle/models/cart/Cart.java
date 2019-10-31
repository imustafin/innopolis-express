package com.mfq.foodle.models.cart;

import com.mfq.foodle.models.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static Cart sInstance = new Cart();

    private Cart() {
    }

    public static Cart getInstance() {
        return sInstance;
    }


    List<Product> mProducts = new ArrayList<>();

    public void addProduct(Product product) {
        if (!mProducts.contains(product))
            mProducts.add(product);
    }

    public List<Product> getProducts() {
        return mProducts;
    }
}
