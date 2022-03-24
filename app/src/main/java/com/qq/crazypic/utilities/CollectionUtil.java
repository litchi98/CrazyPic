package com.qq.crazypic.utilities;

import java.util.List;

public class CollectionUtil {
    private CollectionUtil() {
    }

    /**
     * TODO: test
     * NOTICE:  this method is unverified.
     *
     * @param a   a list of T
     * @param b   another list of T
     * @param <T> the type of elements in these list
     * @return true if a and b are content equal
     */
    public static <T> boolean elementsEquals(List<T> a, List<T> b) {
        if (a == b) {
            return true;
        }
        if (a != null && b != null && a.size() == b.size()) {
            for (T t : a) {
                if (!b.contains(t)) {
                    return false;
                }
            }
        }
        return false;
    }
}