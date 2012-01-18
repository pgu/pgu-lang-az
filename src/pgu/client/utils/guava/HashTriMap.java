package pgu.client.utils.guava;

import java.util.ArrayList;

public class HashTriMap<L, F, S> {

    private HashTriMap() {
    }

    public static <L, F, S> HashTriMap<L, F, S> create() {
        return new HashTriMap<L, F, S>();
    }

    private final HashBiMaps<L, F> latin2foreign = HashBiMaps.create();
    private final HashBiMaps<F, S> foreign2sign = HashBiMaps.create();
    private final HashBiMaps<S, L> sign2latin = HashBiMaps.create();
    private final ArrayList<Entry<L, F, S>> entries = new ArrayList<Entry<L, F, S>>();

    public void put(final L latin, final F foreign, final S symbol) {

        entries.add(new Entry<L, F, S>().latin(latin).foreign(foreign).symbol(symbol));

        latin2foreign.put(latin, foreign);
        foreign2sign.put(foreign, symbol);
        sign2latin.put(symbol, latin);
    }

    public ArrayList<Entry<L, F, S>> entryList() {
        return entries;
    }

    public static class Entry<L, F, S> {
        private L latin;
        private F foreign;
        private S symbol;

        public Entry<L, F, S> latin(final L latin) {
            this.latin = latin;
            return this;
        }

        public Entry<L, F, S> foreign(final F foreign) {
            this.foreign = foreign;
            return this;
        }

        public Entry<L, F, S> symbol(final S symbol) {
            this.symbol = symbol;
            return this;
        }

        public L latin() {
            return latin;
        }

        public F foreign() {
            return foreign;
        }

        public S symbol() {
            return symbol;
        }
    }

}
