package com.paladinzzz.game.screens.worldobjects.Iterator;

import com.paladinzzz.game.screens.worldobjects.IObject;

import java.util.ArrayList;

public class ObjectIterator implements IIteratorObject {

    private ArrayList<IObject> list;
    private int currentPos;

    public ObjectIterator() {
        this.currentPos = -1;
        this.list = new ArrayList<IObject>();
    }

    @Override
    public void add(IObject item) {
        this.list.add(item);
    }

    @Override
    public IObject getNext() {
        System.out.println("Element defined");
        currentPos += 1;
        return this.list.get(currentPos);
        }

    @Override
    public void reset() {
        this.currentPos = -1;
    }

    @Override
    public boolean hasNext() {
        System.out.println(list.size());
        int checkPos = currentPos;
        if((checkPos + 1) >= this.list.size()) {
            System.out.println("Returning false!");
            return false;
        }
        else {
            System.out.println("Returning true!");
            return true;
        }
    }
}
