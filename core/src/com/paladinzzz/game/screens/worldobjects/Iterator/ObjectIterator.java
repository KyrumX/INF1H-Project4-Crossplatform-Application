package com.paladinzzz.game.screens.worldobjects.Iterator;

import java.util.ArrayList;

//De objectIterator gebruiken we om door alle wereld objecten heen te lopen

public class ObjectIterator implements IIteratorObject {

    private ArrayList<com.paladinzzz.game.screens.worldobjects.IObject> list;
    private int currentPos;

    public ObjectIterator() {
        this.currentPos = -1;
        this.list = new ArrayList<com.paladinzzz.game.screens.worldobjects.IObject>();
    }

    @Override
    public void add(com.paladinzzz.game.screens.worldobjects.IObject item) {
        this.list.add(item);
    }

    @Override
    public com.paladinzzz.game.screens.worldobjects.IObject getNext() {
        currentPos += 1;
        return this.list.get(currentPos);
        }

    @Override
    public void reset() {
        this.currentPos = -1;
    }

    @Override
    public boolean hasNext() {
        int checkPos = currentPos;
        if((checkPos + 1) >= this.list.size()) {
            return false;
        }
        else {
            return true;
        }
    }
}
