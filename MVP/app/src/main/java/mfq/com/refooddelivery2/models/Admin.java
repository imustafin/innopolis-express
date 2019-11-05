package mfq.com.refooddelivery2.models;

import java.util.List;

public class Admin extends User {

    List<Orders> mOrdersList;


    public List<Orders> getOrdersList() {
        return mOrdersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        mOrdersList = ordersList;
    }
}
