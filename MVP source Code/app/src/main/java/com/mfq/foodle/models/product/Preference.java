package com.mfq.foodle.models.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preference implements Parcelable {
    @SerializedName("preference_id")
    @Expose
    private String preferenceId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("is_selected")
    @Expose
    private boolean isSelected;

    public String getPreferenceId() {
        return preferenceId;
    }

    public String getText() {
        return text;
    }


    public Preference() {
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.preferenceId);
        dest.writeString(this.text);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected Preference(Parcel in) {
        this.preferenceId = in.readString();
        this.text = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<Preference> CREATOR = new Creator<Preference>() {
        @Override
        public Preference createFromParcel(Parcel source) {
            return new Preference(source);
        }

        @Override
        public Preference[] newArray(int size) {
            return new Preference[size];
        }
    };
}
