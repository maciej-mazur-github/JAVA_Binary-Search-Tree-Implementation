package com.binarySearchTree;

public class BinarySearchTree implements List {
    private Item root = null;

    @Override
    public Item getRoot() {
        return root;
    }

    @Override
    public void addItem(Object addedValue) {
        Item addedItem = new Node(addedValue);

        if(root == null) {
            root = addedItem;
            return;
        } else {
            addItemRecursive(root, addedItem);
        }
    }

    private void addItemRecursive(Item currentItem, Item addedItem) {
        int comparison = currentItem.compareTo(addedItem);

        if(comparison > 0) {
            if(currentItem.previous() == null) {
                currentItem.setPrevious(addedItem);
            } else {
                addItemRecursive(currentItem.previous(), addedItem);
            }
        } else if(comparison < 0) {
            if(currentItem.next() == null) {
                currentItem.setNext(addedItem);
            } else {
                addItemRecursive(currentItem.next(), addedItem);
            }
        } else {
            System.out.println("Value " + addedItem.getValue() + " already exists in BST. Adding not processed");
            return;
        }

        System.out.println("Value " + addedItem.getValue() + " added successfully");
    }

    @Override
    public void removeItem(Object item) {

    }

    @Override
    public Item containsItem(Object item) {
        return null;
    }

    @Override
    public void traverseList() {
        if(root == null) {
            System.out.println("The list is empty");
            return;
        }
    }

    private void traverseList(Item currentItem) {
        if(currentItem.previous() != null) {
            traverseList(currentItem.previous());
        } else {
            System.out.println(currentItem.getValue() + " ");
        }

        if(currentItem.next() != null) {
            traverseList(currentItem.next());
        }
    }
}
