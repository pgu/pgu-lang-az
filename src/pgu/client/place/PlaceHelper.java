package pgu.client.place;

import java.util.HashMap;
import java.util.Map.Entry;

public class PlaceHelper {

    public static final String GRANULARITY = "granularity";
    public static final String LANGUAGE = "language";

    private final HashMap<String, String> k2v = new HashMap<String, String>();
    private String token;

    public PlaceHelper() {
    }

    public PlaceHelper(final String token) {
        this.token = token;

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
}
