package pgu.client.utils.guava;

import java.util.ArrayList;
import java.util.HashMap;

public class HashBiMaps<K, V> {

    private HashBiMaps() {
    }

    private final HashMap<K, ArrayList<V>> map = new HashMap<K, ArrayList<V>>();
    private final HashMap<V, ArrayList<K>> inverse = new HashMap<V, ArrayList<K>>();

    public static <K, V> HashBiMaps<K, V> create() {
        return new HashBiMaps<K, V>();
    }

    public ArrayList<V> get(final K key) {
        return map.get(key);
    }

    public HashMap<V, ArrayList<K>> inverse() {
        return inverse;
    }

    public void put(final K k, final V v) {

        if (map.containsKey(k)) {
            map.get(k).add(v);

        } else {
            map.put(k, Lists.newArrayList(v));
        }

        if (inverse.containsKey(v)) {
            inverse.get(v).add(k);

        } else {
            inverse.put(v, Lists.newArrayList(k));

        }
    }

}
