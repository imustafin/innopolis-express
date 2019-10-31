package com.mfq.foodle.models.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discounts implements Parcelable {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("priceAfterDiscount")
    @Expose
    private String priceAfterDiscount;

    public String getDescription() {
        return description;
    }

    public String getPriceAfterDiscount() {
        return priceAfterDiscount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.priceAfterDiscount);
    }

    public Discounts() {
    }

    protected Discounts(Parcel in) {
        this.description = in.readString();
        this.priceAfterDiscount = in.readString();
    }

    public static final Parcelable.Creator<Discounts> CREATOR = new Parcelable.Creator<Discounts>() {
        @Override
        public Discounts createFromParcel(Parcel source) {
            return new Discounts(source);
        }

        @Override
        public Discounts[] newArray(int size) {
            return new Discounts[size];
        }
    };
}
