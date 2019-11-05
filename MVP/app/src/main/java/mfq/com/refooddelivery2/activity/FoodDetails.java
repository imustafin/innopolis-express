package mfq.com.refooddelivery2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.helper.QuantityPicker;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Product;

public class FoodDetails extends AppCompatActivity implements View.OnClickListener, QuantityPicker.OnQtyChangedListener {
    private static final String KEY_PRODUCT = "PRODUCT";
    private View mAddToCartButton;
    private TextView mDescription;
    private TextView mPrice;
    private QuantityPicker mQty;
    private TextView mTitle;
    private Product mProduct;
    private ImageView mImg;

    public static Intent buildIntent(Context c, Product product) {
        Intent intent = new Intent(c, FoodDetails.class);
        intent.putExtra(KEY_PRODUCT, product);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAddToCartButton = findViewById(R.id.detail_add_to_cart);
        mDescription = findViewById(R.id.detail_description);
        mPrice = findViewById(R.id.detail_price);
        mQty = findViewById(R.id.detail_qty);
        mTitle = findViewById(R.id.detail_title);
        mImg = findViewById(R.id.detail_img);

        mProduct = getIntent().getParcelableExtra(KEY_PRODUCT);
        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.freakah).dontTransform();
        Glide.with(this).load(mProduct.getImgUrl()).apply(requestOptions).into(mImg);
        mTitle.setText(mProduct.getName());
        mDescription.setText(mProduct.getDescription());
        mPrice.setText(mProduct.getPrice().getFormattedValue());
        mAddToCartButton.setOnClickListener(this);
        findViewById(R.id.details_cart).setOnClickListener(this);
        mQty.setOnQtyChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.details_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return;
        }
        Cart.getInstance().addProduct(mProduct);
        Toast.makeText(this, mProduct.getQuantity() + " " + mProduct.getName() + " is added to cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQtyChanged(int qty) {
        mProduct.setQuantity(qty);
    }

    @Override
    public void onQtyIncreased(int qty) {

    }

    @Override
    public void onQtyDecreased(int qty) {

    }
}
