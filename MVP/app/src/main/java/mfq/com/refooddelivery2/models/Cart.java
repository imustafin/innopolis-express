package mfq.com.refooddelivery2.models;


import java.util.ArrayList;
import java.util.List;

public class Cart {

    String cartId;
    List<Product> mProducts = new ArrayList<>();
    Totals totals;
    User user;


    private static Cart sInstance = new Cart();


    private Cart() {
    }

    public static Cart getInstance() {
        return sInstance;
    }


    public void addProduct(Product product) {
        if (!mProducts.contains(product))
            mProducts.add(product);
    }

    public List<Product> getProducts() {
        return mProducts;
    }
}
