package pgu.client.utils.guava;

public class HashTriMap<L, F, S> {

    private HashTriMap() {
    }

    public static <L, F, S> HashTriMap<L, F, S> create() {
        return new HashTriMap<L, F, S>();
    }

    private final HashBiMaps<L, F> latin2foreign = HashBiMaps.create();
    private final HashBiMaps<F, S> foreign2sign = HashBiMaps.create();
    private final HashBiMaps<S, L> sign2latin = HashBiMaps.create();

    public void put(final L latin, final F foreign, final S sign) {
        latin2foreign.put(latin, foreign);
        foreign2sign.put(foreign, sign);
        sign2latin.put(sign, latin);
    }
}
