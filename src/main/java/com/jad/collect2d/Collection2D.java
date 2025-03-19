package com.jad.collect2d;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class Collection2D<E extends Collection2DElement> extends HashMap<Point, List<E>> {
    public void add(final E element) {

    }

    public E remove(E element) {
        return null;
    }

    public boolean contains(final E element) {
        return false;
    }

    public Dimension getDimension() {
        return null;
    }

    public List<E> toList() {
        return null;
    }
}
