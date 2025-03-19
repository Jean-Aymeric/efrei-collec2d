package com.jad.collect2d;

import java.awt.*;

public interface Collection2DElement {
    Point getLocation();

    Collection2D<Collection2DElement> getCollection();

    void setCollection(Collection2D<Collection2DElement> collection);
}
