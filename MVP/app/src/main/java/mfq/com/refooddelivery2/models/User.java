package mfq.com.refooddelivery2.models;

import java.util.List;

class User {

    String userId;
    String eamil;
    String password;
    String phonenumber;
    boolean isLoggedIn;
    String fullName;
    List<Address> mAddressList;
    List<Orders> mOrdersList;
    PaymentInfo mPaymentInfo;



}
