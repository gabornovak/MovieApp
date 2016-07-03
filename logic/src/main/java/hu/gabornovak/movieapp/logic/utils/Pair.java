package hu.gabornovak.movieapp.logic.utils;

/**
 * Created by gnovak on 7/3/2016.
 */

public class Pair<K, V> {
    private final K element0;
    private final V element1;

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getLeft() {
        return element0;
    }

    public V getRight() {
        return element1;
    }
}
