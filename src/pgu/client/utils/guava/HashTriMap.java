package pgu.client.utils.guava;

public class HashTriMap<L, F, S> {

    private HashTriMap() {
    }

    public static <L, F, S> HashTriMap<L, F, S> create() {
        return new HashTriMap<L, F, S>();
    }

    private final HashBiMap<L, F> latin2foreign = HashBiMap.create();
    private final HashBiMap<F, S> foreign2sign = HashBiMap.create();
    private final HashBiMap<S, L> sign2latin = HashBiMap.create();

    public void put(final L latin, final F foreign, final S sign) {
        latin2foreign.put(latin, foreign);
        foreign2sign.put(foreign, sign);
        sign2latin.put(sign, latin);
    }
}
