package pgu.client.myguava;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class HashBiMap<K, V> {

    private final HashMap<K, V> map = new HashMap<K, V>();
    private final HashMap<V, K> inverse = new HashMap<V, K>();

    public static <K, V> HashBiMap<K, V> create() {
        return new HashBiMap<K, V>();
    }

    public V get(final K key) {
        return map.get(key);
    }

    public HashMap<V, K> inverse() {
        return inverse;
    }

    public void put(final K k, final V v) {
        map.put(k, v);
        inverse.put(v, k);
    }

    public boolean containsKey(final K k) {
        return map.containsKey(k);
    }

    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

}
