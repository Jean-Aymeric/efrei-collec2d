package com.jad.collect2d;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Collection2DTest {
    private static final int WIDTH_TEST = 100;
    private static final int HEIGHT_TEST = 100;
    private static final int NB_ELEMENTS_BY_TILE = 10;
    private static final Collection2D<Collection2DElementTest> COLLECTION = new Collection2D<>();
    private static final List<Collection2DElementTest> ELEMENTS_ADDED = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        Collection2DTest.COLLECTION.clear();
        Collection2DTest.ELEMENTS_ADDED.clear();

        for (int numElement = 0; numElement < Collection2DTest.NB_ELEMENTS_BY_TILE; numElement++) {
            for (int row = 0; row < Collection2DTest.WIDTH_TEST; row++) {
                for (int column = 0; column < Collection2DTest.HEIGHT_TEST; column++) {
                    Collection2DElementTest element = new Collection2DElementTest();
                    Collection2DTest.ELEMENTS_ADDED.add(element);
                    element.setLocation(new Point(column, row));
                    Collection2DTest.COLLECTION.add(element);
                }
            }
        }
    }

    @Test
    void addAndGet() {
        for (Collection2DElementTest element : Collection2DTest.ELEMENTS_ADDED) {
            List<Collection2DElementTest> elements = Collection2DTest.COLLECTION.get(element.getLocation());
            assertNotNull(elements,
                          "Element's list location at location " + element.getLocation() + " cannot be null.");
            assertTrue(elements.contains(element),
                       "Element's location list at location " + element.getLocation() + " does not contains the element.");
            assertEquals(Collection2DTest.COLLECTION, element.getCollection(),
                         "Element's collection is not the same as the collection which contains the element.");
        }
        assertThrows(IllegalArgumentException.class, () -> Collection2DTest.COLLECTION.add(null),
                     "Element cannot be null.");
    }

    @Test
    void remove() {
        for (Collection2DElementTest element : Collection2DTest.ELEMENTS_ADDED) {
            assertTrue(Collection2DTest.COLLECTION.remove(element),
                       "Remove an element present in the collection should return true.");
            List<Collection2DElementTest> elements = Collection2DTest.COLLECTION.get(element.getLocation());
            if (elements != null) {
                assertFalse(elements.contains(element),
                            "Element's location list at location " + element.getLocation() + " should not contain the element.");
            }
            assertNull(element.getCollection(),
                       "Element's collection should be null after being removed from the collection.");
        }
        assertThrows(IllegalArgumentException.class, () -> Collection2DTest.COLLECTION.remove(null),
                     "Element cannot be null.");
    }

    @Test
    void contains() {
        for (Collection2DElementTest element : Collection2DTest.ELEMENTS_ADDED) {
            assertTrue(Collection2DTest.COLLECTION.contains(element),
                       "Collection should contain the element.");
        }
    }

    @Test
    void getDimension() {
        assertEquals(new Dimension(Collection2DTest.WIDTH_TEST, Collection2DTest.HEIGHT_TEST),
                     Collection2DTest.COLLECTION.getDimension(),
                     "Collection's dimension is not the same as the expected dimension.");
        Collection2DTest.COLLECTION.clear();
        assertEquals(new Dimension(0, 0), Collection2DTest.COLLECTION.getDimension(),
                     "Collection's dimension is not the same as the expected dimension.");
        Collection2DTest.COLLECTION.add(new Collection2DElementTest(new Point(10, 100)));
        assertEquals(new Dimension(10 + 1, 100 + 1), Collection2DTest.COLLECTION.getDimension(),
                     "Collection's dimension is not the same as the expected dimension.");
        Collection2DTest.COLLECTION.add(new Collection2DElementTest(new Point(-10, -100)));
        assertEquals(new Dimension(10 + 10 + 1, 100 + 100 + 1), Collection2DTest.COLLECTION.getDimension(),
                     "Collection's dimension is not the same as the expected dimension.");
    }

    @Test
    void toList() {
        assertNotNull(Collection2DTest.COLLECTION.toList(),
                      "Collection's list cannot be null.");
        assertEquals(Collection2DTest.ELEMENTS_ADDED.size(), Collection2DTest.COLLECTION.toList().size(),
                     "Collection's list size is not the same as the expected size.");
        assertTrue(Collection2DTest.ELEMENTS_ADDED.containsAll(Collection2DTest.COLLECTION.toList()),
                   "Collection's list has some elements that are not in the elements added.");
        assertTrue(Collection2DTest.COLLECTION.toList().containsAll(Collection2DTest.ELEMENTS_ADDED),
                   "Collection's list does not contain all the elements.");
    }

    @Test
    void isEmpty() {
        assertFalse(Collection2DTest.COLLECTION.isEmpty(),
                    "Collection should not be empty.");
        Collection2DTest.COLLECTION.clear();
        assertTrue(new Collection2D<>().isEmpty(),
                   "Collection should be empty.");
    }

    @Test
    void elementHasMoved() {
        Collection2DElementTest element = Collection2DTest.ELEMENTS_ADDED.getFirst();
        Point oldPosition = new Point(element.getLocation());
        element.setLocation(new Point(oldPosition.x + 10, oldPosition.y - 100));
        final List<Collection2DElementTest> collection2DElementTests = Collection2DTest.COLLECTION.get(oldPosition);
        if (collection2DElementTests != null) {
            assertFalse(collection2DElementTests.contains(element),
                        "Element's list should not contain the element at the old position.");
        }
        final List<Collection2DElementTest> actualPosition = Collection2DTest.COLLECTION.get(element.getLocation());
        assertNotNull(actualPosition,
                      "Collection should contain the element at the new position.");
        assertTrue(actualPosition.contains(element),
                   "Collection should contain the element at the new position.");
    }

    private static class Collection2DElementTest implements Collection2DElement<Collection2DElementTest> {
        private Point location;
        private Collection2D<Collection2DElementTest> collection;

        public Collection2DElementTest() {
            this(new Point(0, 0));
        }

        public Collection2DElementTest(final Point location) {
            this.location = location;
        }

        public void setLocation(final Point newLocation) {
            this.notifyCollectionAnLocationUpdate(this.getLocation(), newLocation);
            this.location = newLocation;
        }

        @Override
        public Point getLocation() {
            return this.location;
        }

        @Override
        public Collection2D<Collection2DElementTest> getCollection() {
            return this.collection;
        }

        @Override
        public void setCollection(final Collection2D<Collection2DElementTest> collection) {
            this.collection = collection;
        }

        public void notifyCollectionAnLocationUpdate(final Point oldLocation, final Point newLocation) {
            if (this.collection == null) return;
            if (oldLocation != null && this.getCollection() != null) {
                this.collection.updateElementPosition(this, oldLocation, newLocation);
            }
        }
    }
}