package com.mfq.foodle.moofi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoofiPayload {

    @SerializedName("moofi")
    @Expose
    Moofi mMoofi;

    @SerializedName("ui")
    @Expose
    MoofiUi mMoofiUi;


    public Moofi getMoofi() {
        return mMoofi;
    }

    public MoofiUi getMoofiUi() {
        return mMoofiUi;
    }
}
