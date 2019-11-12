package mfq.com.refooddelivery2.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.fragments.CartFragment;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Product;

/**
 * Use case: Order Detail invoice
 * Link: https://github.com/BatyrSeven/innopolis-express/blob/master/Requirements/UseCases.md#order-detail-invoice
 */
public class InvoiceActivity extends AppCompatActivity {

    private TextView mOrderDate;
    private TextView mTotal;


    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        mOrderDate = findViewById(R.id.date);
        mTotal = findViewById(R.id.total);

        Cart cart = Cart.getInstance();
        mTotal.setText(cart.getTotal() + " \u20BD");

        Date date = Calendar.getInstance().getTime();
        mOrderDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));

        TableLayout tableLayout = findViewById(R.id.table);
        List<Product> productList = cart.getProducts();

        for (int i=0;i<productList.size();i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            tableRow.setPadding(0 , 10, 0, 0);

            TextView tvName = new TextView(this);
            tvName.setTextColor(Color.BLACK);
            tvName.setId(i + 10000);
            tvName.setGravity(Gravity.START|Gravity.CENTER);
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
            tvPrice.setGravity(Gravity.END|Gravity.CENTER);
            tvPrice.setText(String.valueOf(productList.get(i).getPrice().getValue()));
            tableRow.addView(tvPrice);


            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public void onCancelClick(View v) {
        Cart cart = Cart.getInstance();
        cart.getProducts().clear();
        super.finish();
    }
}
