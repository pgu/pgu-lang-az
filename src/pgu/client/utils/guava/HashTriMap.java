package pgu.client.utils.guava;

import java.util.ArrayList;

public class HashTriMap<F, S, T> {

    private HashTriMap() {
    }

    public static <F, S, T> HashTriMap<F, S, T> create() {
        return new HashTriMap<F, S, T>();
    }

    private final HashBiMaps<F, S> first2second = HashBiMaps.create();
    private final HashBiMaps<S, T> second2third = HashBiMaps.create();
    private final HashBiMaps<T, F> third2first = HashBiMaps.create();
    private final ArrayList<Entry<F, S, T>> entries = new ArrayList<Entry<F, S, T>>();

    public void put(final F first, final S second, final T third) {

        entries.add(new Entry<F, S, T>().first(first).second(second).third(third));

        first2second.put(first, second);
        second2third.put(second, third);
        third2first.put(third, first);
    }

    public ArrayList<Entry<F, S, T>> entryList() {
        return entries;
    }

    public static class Entry<F, S, T> {
        private F first;
        private S second;
        private T third;

        public Entry<F, S, T> first(final F first) {
            this.first = first;
            return this;
        }

        public Entry<F, S, T> second(final S second) {
            this.second = second;
            return this;
        }

        public Entry<F, S, T> third(final T third) {
            this.third = third;
            return this;
        }

        public F first() {
            return first;
        }

        public S second() {
            return second;
        }

        public T third() {
            return third;
        }
    }

    public ArrayList<F> getFirstsFromSecond(final S second) {
        return first2second.inverse().get(second);
    }

    public ArrayList<F> getFirstsFromThird(final T third) {
        return third2first.get(third);
    }

    public ArrayList<S> getSecondsFromFirst(final F first) {
        return first2second.get(first);
    }

    public ArrayList<S> getSecondsFromThird(final T third) {
        return second2third.inverse().get(third);
    }

    public ArrayList<T> getThirdsFromFirst(final F first) {
        return third2first.inverse().get(first);
    }

    public ArrayList<T> getThirdsFromSecond(final S second) {
        return second2third.get(second);
    }

    public ArrayList<T> getThirdsFromFirstAndSecond(final F first, final S second) {
        final ArrayList<T> thirdsFromFirst = getThirdsFromFirst(first);
        final ArrayList<T> thirdsFromSecond = getThirdsFromSecond(second);
        thirdsFromFirst.retainAll(thirdsFromSecond);
        return thirdsFromFirst;
    }

    public ArrayList<S> getSecondsFromFirstAndThird(final F first, final T third) {
        final ArrayList<S> secondsFromFirst = getSecondsFromFirst(first);
        final ArrayList<S> secondsFromThird = getSecondsFromThird(third);
        secondsFromFirst.retainAll(secondsFromThird);
        return secondsFromFirst;
    }

    public ArrayList<F> getFirstsFromSecondAndThird(final S second, final T third) {
        final ArrayList<F> firstsFromSecond = getFirstsFromSecond(second);
        final ArrayList<F> firstsFromThird = getFirstsFromThird(third);
        firstsFromSecond.retainAll(firstsFromThird);
        return firstsFromSecond;
    }

}
