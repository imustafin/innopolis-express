package mfq.com.refooddelivery2.models.cart;


import java.util.ArrayList;
import java.util.List;

import mfq.com.refooddelivery2.models.product.Product;

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
