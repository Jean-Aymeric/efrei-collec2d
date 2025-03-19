package com.jad.collect2d;

import java.awt.*;

/**
 * Represents an element in a Collection2D.
 * The element should have a location and be able to notify the collection when its location is updated.
 * The element should also be able to know the collection that contains it.
 * The element should be able to set the collection that contains it.
 * The element should be able to get the location of the element.
 * The element should be able to get the collection that contains it.
 * The element should be able to notify the collection that contains it when its location is updated.
 *
 * @param <E> - the type of the element
 */
public interface Collection2DElement<E extends Collection2DElement<E>> {
    /**
     * Returns the location of the element.
     *
     * @return the location of the element.
     */
    Point getLocation();

    /**
     * Returns the collection that contains the element.
     * Returns null if the element is not in any collection.
     *
     * @return the collection that contains the element.
     */
    Collection2D<E> getCollection();

    /**
     * Sets the collection that contains the element.
     * It's called by the collection when the element is added to it.
     * If the element is already in a collection, it should be removed from it by calling the remove method of the collection.
     *
     * @param collection the collection that contains the element.
     */
    void setCollection(Collection2D<E> collection);

    /**
     * Notifies the collection containing the element that the element has moved from oldLocation to newLocation.
     * It's called by the element when its location is updated.
     *
     * @param oldLocation - the old location of the element
     * @param newLocation - the new location of the element
     */
    void notifyCollectionAnLocationUpdate(final Point oldLocation, final Point newLocation);
}
