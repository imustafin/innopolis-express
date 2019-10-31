package com.mfq.foodle.models.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MealPreference implements Parcelable {

    public static final String TYPE_FILTER = "filter";
    public static final String TYPE_SINGLE = "single";
    public static final String TYPE_ENTRY = "entry";

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("selection_type")
    @Expose
    private String selectionType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("Preference")
    @Expose
    private List<Preference> preference = null;


    public String getType() {
        return type;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Preference> getPreference() {
        return preference;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.selectionType);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeList(this.preference);
    }

    public MealPreference() {
    }

    protected MealPreference(Parcel in) {
        this.type = in.readString();
        this.selectionType = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.preference = new ArrayList<Preference>();
        in.readList(this.preference, Preference.class.getClassLoader());
    }

    public static final Parcelable.Creator<MealPreference> CREATOR = new Parcelable.Creator<MealPreference>() {
        @Override
        public MealPreference createFromParcel(Parcel source) {
            return new MealPreference(source);
        }

        @Override
        public MealPreference[] newArray(int size) {
            return new MealPreference[size];
        }
    };
}
