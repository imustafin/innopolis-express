package com.mfq.foodle.models.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price implements Parcelable {

    @SerializedName("value")
    @Expose
    private double value;
    @SerializedName("formattedValue")
    @Expose
    private String formattedValue;
    @SerializedName("Discounts")
    @Expose
    private Discounts discounts;

    public double getValue() {
        return value;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public Discounts getDiscounts() {
        return discounts;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.value);
        dest.writeString(this.formattedValue);
        dest.writeParcelable(this.discounts, flags);
    }

    public Price() {
    }

    protected Price(Parcel in) {
        this.value = in.readDouble();
        this.formattedValue = in.readString();
        this.discounts = in.readParcelable(Discounts.class.getClassLoader());
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
