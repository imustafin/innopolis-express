package mfq.com.refooddelivery2.models;

import android.os.Parcel;
import android.os.Parcelable;

class Totals implements Parcelable {

    String subTotals;
    String deliveryCharge;


    public String getSubTotals() {
        return subTotals;
    }

    public void setSubTotals(String subTotals) {
        this.subTotals = subTotals;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.subTotals);
        dest.writeString(this.deliveryCharge);
    }

    public Totals() {
    }

    protected Totals(Parcel in) {
        this.subTotals = in.readString();
        this.deliveryCharge = in.readString();
    }

    public static final Creator<Totals> CREATOR = new Creator<Totals>() {
        @Override
        public Totals createFromParcel(Parcel source) {
            return new Totals(source);
        }

        @Override
        public Totals[] newArray(int size) {
            return new Totals[size];
        }
    };
}
