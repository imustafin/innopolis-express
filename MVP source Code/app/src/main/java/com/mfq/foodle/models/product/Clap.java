package com.mfq.foodle.models.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clap implements Parcelable {

    @SerializedName("product_clap")
    @Expose
    private int productClap;
    @SerializedName("user_clap")
    @Expose
    private int userClap;





    public int getProductClap() {
        return productClap;
    }

    public void setProductClap(int productClap) {
        this.productClap = productClap;
    }

    public int getUserClap() {
        return userClap;
    }

    public void setUserClap(int userClap) {
        this.userClap = userClap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.productClap);
        dest.writeInt(this.userClap);
    }

    public Clap() {
    }

    protected Clap(Parcel in) {
        this.productClap = in.readInt();
        this.userClap = in.readInt();
    }

    public static final Parcelable.Creator<Clap> CREATOR = new Parcelable.Creator<Clap>() {
        @Override
        public Clap createFromParcel(Parcel source) {
            return new Clap(source);
        }

        @Override
        public Clap[] newArray(int size) {
            return new Clap[size];
        }
    };
}
