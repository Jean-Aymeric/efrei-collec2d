package com.jad.collect2d;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * A 2D collection of elements.
 * The elements are stored in a HashMap where the key is the element's position.
 * The position is represented by a Point object.
 * The value is a list of elements that share the same position.
 * The elements can set their position and the collection is updated accordingly.
 *
 * @param <E> - the type of elements in this collection
 */
public class Collection2D<E extends Collection2DElement<E>> extends HashMap<Point, List<E>> {
    /**
     * Constructeur par défaut pour Collection2D.
     */
    public Collection2D() {
        super();
    }

    /**
     * Updates the position of the element in the collection.
     * The element is removed from the old position and added to the new position.
     *
     * @param element     - the element to be updated
     * @param oldPosition - the old position of the element
     * @param newLocation - the new position of the element
     */
    public final void updateElementPosition(final E element, final Point oldPosition, final Point newLocation) {

    }

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param element - element whose presence in this collection is to be ensured
     */
    public final void add(final E element) {

    }

    /**
     * Remove the element from the collection.
     *
     * @param element - the element to be removed
     *
     * @return true if this collection changed as a result of the call
     */
    public final boolean remove(final E element) {
        return false;
    }

    /**
     * Returns True if the tree contains the specified value.
     *
     * @param element - element whose presence in this tree is to be tested
     *
     * @return true if the tree contains the specified value
     */
    public final boolean contains(final E element) {
        return false;
    }

    /**
     * Returns the dimension of the collection.
     * The dimension of an empty collection is 0x0.
     *
     * @return the dimension of the collection
     */
    public final Dimension getDimension() {
        return null;
    }

    /**
     * Returns a list of all elements in the collection.
     * The list is ordered by the elements' position.
     *
     * @return a list of all elements in the collection
     */
    public List<E> toList() {
        return null;
    }
}
