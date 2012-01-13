package pgu.client.place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import pgu.client.utils.guava.Lists;

public class PlaceHelper {

    public static final String GRANULARITY = "granularity";
    public static final String LANGUAGE = "language";
    public static final String THEME = "theme";
    public static final String SUBSELECTIONS = "subselections";

    private final HashMap<String, String> k2v = new HashMap<String, String>();

    public PlaceHelper() {
    }

    public PlaceHelper(final String token) {
        final String[] kvs = token.split("&");
        for (final String kv : kvs) {
            final String[] k_v = kv.split("=");
            k2v.put(k_v[0], k_v[1]);
        }
    }

    public PlaceHelper put(final String key, final String v) {
        k2v.put(key, v);
        return this;
    }

    public String getToken() {
        final StringBuilder sb = new StringBuilder();
        for (final Entry<String, String> e : k2v.entrySet()) {
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
            sb.append("&");
        }
        sb.deleteCharAt(sb.length()); // removes trailing '&'
        return sb.toString();
    }

    public String get(final String key) {
        return k2v.get(key);
    }

    public ArrayList<String> list(final String key) {
        String v = k2v.get(key);
        v = v.substring(0, v.length());

        final String[] parts = v.split(",");

        final ArrayList<String> values = Lists.newArrayList();
        for (final String part : parts) {
            values.add(part.trim());
        }
        return values;
    }
}
