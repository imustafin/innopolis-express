package mfq.com.refooddelivery2.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.product_accessories.Clap;
import mfq.com.refooddelivery2.product_accessories.MealPreference;
import mfq.com.refooddelivery2.product_accessories.Price;

public class Product implements Parcelable {

    public static final String TYPE_FOOD = "food";

    public final static int[] desserImgs = {
            R.drawable.dessert7,
            R.drawable.dessert3,
            R.drawable.dessert1,
            R.drawable.dessert2,
            R.drawable.dessert5,
            R.drawable.dessert4,
            R.drawable.dessert6
    };


    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("Price")
    @Expose
    private Price price;
    @SerializedName("MealPreferences")
    @Expose
    private List<MealPreference> mealPreferences = null;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("Clap")
    @Expose
    private Clap clap;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Price getPrice() {
        return price;
    }

    public List<MealPreference> getMealPreferences() {
        return mealPreferences;
    }


    public Product() {
    }

    public Product(Product p){
        this.productId = p.productId;
        this.name = p.name;
        this.imgUrl = p.imgUrl;
        this.description = p.description;
        this.type = p.type;
        this.price = p.price;
        this.mealPreferences = p.mealPreferences;
        this.quantity = p.quantity;
        this.isSelected = p.isSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productId);
        dest.writeString(this.name);
        dest.writeString(this.imgUrl);
        dest.writeString(this.description);
        dest.writeString(this.type);
        dest.writeParcelable(this.price, flags);
        dest.writeTypedList(this.mealPreferences);
        dest.writeInt(this.quantity);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected Product(Parcel in) {
        this.productId = in.readString();
        this.name = in.readString();
        this.imgUrl = in.readString();
        this.description = in.readString();
        this.type = in.readString();
        this.price = in.readParcelable(Price.class.getClassLoader());
        this.mealPreferences = in.createTypedArrayList(MealPreference.CREATOR);
        this.quantity = in.readInt();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
