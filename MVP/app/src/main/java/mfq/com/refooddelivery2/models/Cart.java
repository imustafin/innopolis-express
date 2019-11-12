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


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addProduct(Product product) {
        Optional<Product> temp = mProducts.stream().filter(x -> x.getName().equals(product.getName())).findFirst();
        if (temp.isPresent()){
            Product tempProduct = temp.get();
            mProducts.get(mProducts.indexOf(tempProduct)).setQuantity(tempProduct.getQuantity() + product.getQuantity());
        } else {
            mProducts.add(product);
        }
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public double getTotal() {
        double total = 0;
        for (Product product : mProducts) {
            total += product.getPrice().getValue() * product.getQuantity();
        }
        return total;
    }
}
