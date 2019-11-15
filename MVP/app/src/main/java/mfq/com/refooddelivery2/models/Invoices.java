package mfq.com.refooddelivery2.models;

import android.text.Editable;
import android.widget.EditText;

import java.util.List;
import java.util.Map;

public class Invoices {
    public String status;
    public String userEmail;
    public String userName;
    public List<Map<String, Object>> products;
    public String address;
    public String phoneNumber;

    public Invoices(String pending, String user_id, List<Map<String, Object>> products, EditText mAddress, EditText mPhone) {
    }

    public Invoices(String status, String userEmail, String userName, List<Map<String, Object>> products, String address, String phoneNumber) {
        this.status = status;
        this.userEmail = userEmail;
        this.userName = userName;
        this.products = products;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
