package com.paladinzzz.game.screens.worldobjects.Iterator;

import com.paladinzzz.game.screens.worldobjects.IObject;

/**
 * Created by aaron on 25-Jun-17.
 */

public interface IIteratorObject {
    IObject getNext();

    void reset();

    void add(IObject item);

    boolean hasNext();
}
