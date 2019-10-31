package com.mfq.foodle.moofi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Moofi {

    @SerializedName("display_prompt")
    @Expose
    private  String display_prompt ="";


    public String getDisplayPrompt() {
        return display_prompt;
    }

    /**
     * Action the Moofi sends
     */
    public static class Actions {

        public static final String ACTION_ENTER_PHONE="ACTION_ENTER_PHONE";
        public static final String ACTION_EXIT="ACTION_EXIT";
        public static final String ACTION_ADD_TO_CART="ACTION_ADD_TO_CART";
        public static final String ACTION_ORDER_PRODUCT="ACTION_ORDER_PRODUCT";






        /**
         * SENDS ACTIONS
         */
        public static final String ACTION_SEND_GIF = "SEND_GIF";
    }
    public static class Events {
        public static final String EVENT_WELCOME = "Welcome";
    }
    public static class Entity {
            public static final String ENTITY_MEALS = "entity-meals";
            public static final String ENTITY_DESSERT = "entity-dessert";
            public static final String ENTITY_NUMBER = "number";
    }



}
