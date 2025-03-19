package com.jad.collect2d;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Collection2DTest {
    private static final int WIDTH_TEST = 100;
    private static final int HEIGHT_TEST = 100;
    private static final int NB_ELEMENTS_BY_TILE = 10;

    @Test
    void addAndGet() {
        Collection2D<Collection2DElementTest> collection = new Collection2D<>();
        for (int numElement = 0; numElement < Collection2DTest.NB_ELEMENTS_BY_TILE; numElement++) {
            for (int row = 0; row < Collection2DTest.WIDTH_TEST; row++) {
                for (int column = 0; column < Collection2DTest.HEIGHT_TEST; column++) {
                    Collection2DElementTest element = new Collection2DElementTest();
                    element.setLocation(new Point(column, row));
                    collection.add(element);
                    List<Collection2DElementTest> elements = collection.get(element.getLocation());
                    assertNotNull(elements,
                                  "Element's list location at location " + element.getLocation() + " cannot be null.");
                    assertTrue(elements.contains(element),
                               "Element's location list at location " + element.getLocation() + " does not contains the element.");
                    assertEquals(collection, element.getCollection(),
                                 "Element's collection is not the same as the collection which contains the element.");
                }
            }
        }
        assertThrows(IllegalArgumentException.class, () -> collection.add(null));
    }

    @Test
    void remove() {
        Collection2D<Collection2DElementTest> collection = new Collection2D<>();
        List<Collection2DElementTest> elementsAdded = new ArrayList<>();
        for (int numElement = 0; numElement < Collection2DTest.NB_ELEMENTS_BY_TILE; numElement++) {
            for (int row = 0; row < Collection2DTest.WIDTH_TEST; row++) {
                for (int column = 0; column < Collection2DTest.HEIGHT_TEST; column++) {
                    Collection2DElementTest element = new Collection2DElementTest();
                    elementsAdded.add(element);
                    element.setLocation(new Point(column, row));
                    collection.add(element);
                }
            }

        }

        for (Collection2DElementTest element : elementsAdded) {
            assertEquals(element, collection.remove(element),
                         "Element's list location at location " + element.getLocation() + " is not the same as the removed element.");
            List<Collection2DElementTest> elements = collection.get(element.getLocation());
            if (elements != null) {
                assertFalse(elements.contains(element),
                            "Element's location list at location " + element.getLocation() + " should not contain the element.");
            }
            assertNull(element.getCollection(),
                       "Element's collection is not null after remove.");
        }
        assertThrows(IllegalArgumentException.class, () -> collection.remove(null));
    }

    @Test
    void contains() {
        Collection2D<Collection2DElementTest> collection = new Collection2D<>();
        List<Collection2DElementTest> elementsAdded = new ArrayList<>();
        for (int numElement = 0; numElement < Collection2DTest.NB_ELEMENTS_BY_TILE; numElement++) {
            for (int row = 0; row < Collection2DTest.WIDTH_TEST; row++) {
                for (int column = 0; column < Collection2DTest.HEIGHT_TEST; column++) {
                    Collection2DElementTest element = new Collection2DElementTest();
                    elementsAdded.add(element);
                    element.setLocation(new Point(column, row));
                    collection.add(element);
                }
            }

        }

        for (Collection2DElementTest element : elementsAdded) {
            assertTrue(collection.contains(element),
                       "Collection should contain the element.");
        }
    }

    @Test
    void getDimension() {
        Collection2D<Collection2DElementTest> collection = new Collection2D<>();
        assertEquals(new Dimension(0, 0), collection.getDimension());
        collection.add(new Collection2DElementTest(new Point(10, 20)));
        assertEquals(new Dimension(11, 21), collection.getDimension());
        collection.add(new Collection2DElementTest(new Point(-10, -20)));
        assertEquals(new Dimension(21, 41), collection.getDimension());
    }

    @Test
    void isEmpty() {

    }

    @Test
    void toList() {

    }

    @Test
    void elementHasMoved() {

    }

    @Getter
    @Setter
    private static class Collection2DElementTest implements Collection2DElement {
        private Point location;
        private Collection2D<Collection2DElement> collection;

        public Collection2DElementTest() {
            this(new Point(0, 0));
        }

        public Collection2DElementTest(final Point location) {
            this.location = location;
        }
    }
}