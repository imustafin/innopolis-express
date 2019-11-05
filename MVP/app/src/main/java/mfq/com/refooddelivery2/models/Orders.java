package mfq.com.refooddelivery2.models;

import java.util.List;

class Orders {
    String orderId;
    List<Product> productList;
    String date;
    Totals mTotals;
    User mUser;
    boolean isApproved;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Totals getTotals() {
        return mTotals;
    }

    public void setTotals(Totals totals) {
        mTotals = totals;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
