package mfq.com.refooddelivery2.models;


import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import androidx.annotation.RequiresApi;

public class Cart {

    List<Product> mProducts = new ArrayList<>();
    String cartId;
    Totals totals;
    User user;


    private static Cart sInstance = new Cart();


    private Cart() {
    }

    public static Cart getInstance() {
        return sInstance;
    }


    public void addProduct(Product product) {
        if (mProducts.contains(product)) {
            Product product1 = mProducts.get(mProducts.indexOf(product));
            product1.setQuantity(product1.getQuantity() + product.getQuantity());
        }else{
            mProducts.add(product);
        }
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public double getTotalSum() {
        double total = 0;
        for (Product product : mProducts) {
            total += product.getPrice().getValue() * product.getQuantity();
        }
        return total;
    }

    public int getTotalQuantity() {
        int total = 0;
        for (Product product : mProducts) {
            total += product.getQuantity();
        }
        return total;
    }

    public void deleteProduct(Product product) {
        sInstance.getProducts().remove(product);
    }
}
