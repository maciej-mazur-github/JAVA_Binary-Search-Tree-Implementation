package com.binarySearchTree;

public interface List {
    Item getRoot();
    void addItem(Object item);
    void removeItem(Object item);
    Item containsItem(Object item);
    void traverseList();
}
