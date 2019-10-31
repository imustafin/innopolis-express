package com.mfq.foodle.moofi;

import com.mfq.foodle.models.product.Product;

import java.util.List;

public class Assistant {

    public static final int FROM_ASSISTANT = 0;
    public static final int FROM_USER = 1;

    private String chatText;
    private int from;
    private int type;
    private String gifURL;
    List<Product> mProducts;
    Product mProduct;

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public String getGifURL() {
        return gifURL;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Assistant(int type) {
        this.type = type;
    }

    public Assistant(String chatText, int from) {
        this.chatText = chatText;
        this.from = from;
    }

    public Assistant(String gifURL) {
        this.gifURL = gifURL;
    }

    public int getType() {
        return type;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
