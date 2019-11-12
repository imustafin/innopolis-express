package mfq.com.refooddelivery2.utils;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage {

    private static List<String> credentials;

    static {
        credentials = new ArrayList<>();
        credentials.add("foo@example.com:hello");
        credentials.add("bar@example.com:bye");
    }

    public static List<String> getCredentials() {
        return credentials;
    }

}
