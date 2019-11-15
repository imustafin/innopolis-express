package mfq.com.refooddelivery2.models;

import android.widget.EditText;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Invoices {
    public String id;
    public String date;
    public String status;
    public String userEmail;
    public String userName;
    public List<Map<String, Object>> products;
    public String address;
    public String phoneNumber;

    public Invoices(String pending, String user_id, List<Map<String, Object>> products, EditText mAddress, EditText mPhone) {
    }

    public Invoices(String id, String date, String status, String userEmail, String userName, List<Map<String, Object>> products, String address, String phoneNumber) {
        this.date = date;
        this.id = id;
        this.status = status;
        this.userEmail = userEmail;
        this.userName = userName;
        this.products = products;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
