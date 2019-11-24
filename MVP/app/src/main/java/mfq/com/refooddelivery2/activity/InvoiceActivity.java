package mfq.com.refooddelivery2.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import ai.api.util.StringUtils;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Product;
import mfq.com.refooddelivery2.product_accessories.Price;
import mfq.com.refooddelivery2.utils.RequestStatus;


/**
 * Use case: Order Detail invoice
 * Link: https://github.com/BatyrSeven/innopolis-express/blob/master/Requirements/UseCases.md#order-detail-invoice
 */
public class InvoiceActivity extends AppCompatActivity {

    private TextView mOrderDate;
    private TextView mTotal;
    private TextView mStatus;
    private TextView mInvoiceId;
    private TextView mCancelButton;
    private String invoiceId;
    private DocumentReference docRef;
    private OrderCancelTask mCancelTask = null;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        mCancelButton = findViewById(R.id.cancel_button);
        mStatus = findViewById(R.id.status);
        mInvoiceId = findViewById(R.id.invoice_id);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String invoice_key = getIntent().getExtras().getString("invoice_key");
        Map<String, Object> data = (Map<String, Object>) getIntent().getExtras().getSerializable("data");

        docRef = db.collection("invoices").document(invoice_key);
        Log.w("Info", invoice_key);
        docRef.addSnapshotListener((snapshot, e) -> {

            if (snapshot != null) {
                invoiceId = snapshot.getData().get("id").toString();
                mInvoiceId.setText("ID: " + invoiceId);
                mStatus.setText((String) snapshot.getData().get("status"));

                String statusText = mStatus.getText().toString();
                if (statusText.equals("Delivering") ||
                        statusText.equals("Delivered")) {
                    mCancelButton.setVisibility(View.GONE);
                } else {
                    mCancelButton.setVisibility(View.VISIBLE);
                }

                if (statusText.equals("Delivered")) {
                    mStatus.setTextColor(Color.GREEN);
                } else {
                    mStatus.setTextColor(Color.rgb(240, 115,108));
                }

            }

            if (e != null) {
                Log.w("Info", "Listen failed.", e);
                return;
            }

        });


        mOrderDate = findViewById(R.id.date);
        mTotal = findViewById(R.id.total);

        Cart cart = Cart.getInstance();

        if (data != null) {
            cart.getProducts().clear();
            List<Map<String, Object>> products = (List<Map<String, Object>>) data.get("products");
            for (Map<String, Object> product : products) {
                cart.addProduct(
                        new Product(
                                product.get("name").toString(),
                                new Price(Double.parseDouble(product.get("price").toString())),
                                Integer.parseInt(product.get("quantity").toString())));
            }
        }

        mTotal.setText(cart.getTotalSum() + " \u20BD");

        Date date = Calendar.getInstance().getTime();
        if (data == null) {
            mOrderDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
        } else {
            mOrderDate.setText(data.get("date").toString().substring(0, 10));
        }

        TableLayout tableLayout = findViewById(R.id.table);
        List<Product> productList = cart.getProducts();

        for (int i = 0; i < productList.size(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            tableRow.setPadding(0, 10, 0, 0);

            TextView tvName = new TextView(this);
            tvName.setTextColor(Color.BLACK);
            tvName.setId(i + 10000);
            tvName.setGravity(Gravity.START | Gravity.CENTER);
            tvName.setText(productList.get(i).getName());
            tableRow.addView(tvName);

            TextView tvQuantity = new TextView(this);
            tvQuantity.setTextColor(Color.BLACK);
            tvQuantity.setId(i + 20000);
            tvQuantity.setGravity(Gravity.CENTER);
            tvQuantity.setText(String.valueOf(productList.get(i).getQuantity()));
            tableRow.addView(tvQuantity);

            TextView tvPrice = new TextView(this);
            tvPrice.setTextColor(Color.BLACK);
            tvPrice.setId(i + 30000);
            tvPrice.setGravity(Gravity.END | Gravity.CENTER);
            tvPrice.setText(String.valueOf(productList.get(i).getPrice().getValue()));
            tableRow.addView(tvPrice);


            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public void onCancelClick(View v) {
        mCancelTask = new InvoiceActivity.OrderCancelTask(getIntent().getExtras().getString("invoice_key"));
        mCancelTask.execute((Void) null);
    }

    public void onStatusCheck(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://admin-inno-express.firebaseapp.com/track?orderId=" + invoiceId));
        startActivity(browserIntent);
    }

    public class OrderCancelTask extends AsyncTask<Void, Void, Boolean> {

        private String invoiceKey;

        public OrderCancelTask(String invoiceKey) {
            this.invoiceKey = invoiceKey;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            AtomicReference<Boolean> result = new AtomicReference<>(false);

            try {
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> data = new HashMap<>();
                data.put("status", "Cancelled");

                Task<Void> set = db.collection("invoices").document(getIntent().getExtras().getString("invoice_key")).set(data, SetOptions.merge());

                do {
                    Thread.sleep(100);
                } while (!set.isComplete());


                if (set.isSuccessful()) {
                    result.set(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result.get();
        }

        @Override
        protected void onPostExecute(Boolean status) {
            if (status) {
                Cart cart = Cart.getInstance();
                cart.getProducts().clear();

                Toast.makeText(InvoiceActivity.this, "Order was canceled", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(InvoiceActivity.this, MainActivity.class);
                startActivity(intent);
                InvoiceActivity.super.finish();

            } else {
                Toast.makeText(InvoiceActivity.this, "Something went wrong. Try again", Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
