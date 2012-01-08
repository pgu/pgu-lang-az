package pgu.client.utils.guava;

import java.util.ArrayList;
import java.util.Set;

public class Lists {

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    public static <E> ArrayList<E> newArrayList(final Set<E> set) {
        return new ArrayList<E>(set);
    }
}
