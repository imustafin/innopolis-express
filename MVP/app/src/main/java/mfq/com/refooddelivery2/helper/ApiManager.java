package mfq.com.refooddelivery2.helper;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import mfq.com.refooddelivery2.models.Product;

public class ApiManager {

    public static List<Product> loadFood(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("json_data");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            return GsonHelper.parseGsonArray(json, Product[].class);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
