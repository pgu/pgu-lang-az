package pgu.client.myguava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Lists {

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    public static <E> List<E> newArrayList(final Set<E> set) {
        return new ArrayList<E>(set);
    }
}
