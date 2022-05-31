package com.binarySearchTree;

public class Node extends Item {
    protected Node(Object value) {
        super(value);
    }



    @Override
    public Item next() {
        return rightLink;
    }

    @Override
    public Item setNext(Item item) {
        rightLink = item;
        return item;
    }

    @Override
    public Item previous() {
        return leftLink;
    }

    @Override
    public Item setPrevious(Item item) {
        leftLink = item;
        return item;
    }

    @Override
    public int compareTo(Item item) {
        return ((String) super.getValue()).compareTo((String) item.getValue());
    }
}






