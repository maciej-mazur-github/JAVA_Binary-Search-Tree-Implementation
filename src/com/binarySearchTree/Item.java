package com.binarySearchTree;

public abstract class Item {
    protected Item leftLink = null;
    protected Item rightLink = null;
    private Object value;

    Item(Object value) {
        this.value = value;
    }

    protected abstract Item next();
    protected abstract Item setNext(Item item);
    protected abstract Item previous();
    protected abstract Item setPrevious(Item item);

    protected abstract int compareTo(Item item);

    protected Object getValue() {
        return value;
    }
}
