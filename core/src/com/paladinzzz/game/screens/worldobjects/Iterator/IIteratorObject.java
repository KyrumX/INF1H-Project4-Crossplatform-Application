package com.paladinzzz.game.screens.worldobjects.Iterator;

/**
 * Created by aaron on 25-Jun-17.
 */

public interface IIteratorObject {
    com.paladinzzz.game.screens.worldobjects.IObject getNext();

    void reset();

    void add(com.paladinzzz.game.screens.worldobjects.IObject item);

    boolean hasNext();
}
