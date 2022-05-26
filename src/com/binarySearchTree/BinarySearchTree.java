package com.binarySearchTree;

import java.util.Stack;

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
            System.out.println("Value " + addedItem.getValue() + " added successfully as root");
            return;
        } else {
            if(addItemRecursive(root, addedItem)) {
                System.out.println("Value " + addedItem.getValue() + " added successfully");
            } else {
                System.out.println("Value " + addedItem.getValue() + " already exists in BST. Adding not processed");
            }
        }
    }

    private boolean addItemRecursive(Item currentItem, Item addedItem) {
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
            return false;
        }

        return true;
    }


    public void addItemNonRecursive(Object addedValue) {
        Item addedItem = new Node(addedValue);

        if(root == null) {
            root = addedItem;
            System.out.println("Value " + addedItem.getValue() + " added successfully as root");
            return;
        }

        int comparison;
        Item currentItem = root;
        boolean quit = false;

        while(!quit) {
            comparison = currentItem.compareTo(addedItem);

            if(comparison > 0) {
                if(currentItem.previous() == null) {
                    currentItem.setPrevious(addedItem);
                    System.out.println((String) addedValue + " added successfully");
                    quit = true;
                } else {
                    currentItem = currentItem.previous();
                }
            } else if(comparison < 0) {
                if(currentItem.next() == null) {
                    currentItem.setNext(addedItem);
                    System.out.println((String) addedValue + " added successfully");
                    quit = true;
                } else {
                    currentItem = currentItem.next();
                }
            } else {       // no duplicates allowed
                System.out.println((String) addedValue + " already exists in the tree. Duplicates not allowed");
                quit = true;
            }
        }
    }

    @Override
    public void removeItem(Object removedValue) {
        Item removedItem = new Node(removedValue);

        if(root == null) {
            System.out.println("The list is empty, nothing to remove here");
            return;
        }
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

        traverseList(root);
    }

    private void traverseList(Item currentItem) {
        if(currentItem != null) {
            traverseList(currentItem.previous());
            System.out.print(currentItem.getValue() + " ");
            traverseList(currentItem.next());
        }
    }

    public void traverseWithStackAndNoRecursion() {
        if(root == null) {
            System.out.println("The list is empty");
            return;
        }

        Item currentItem = root;
        Stack<Item> stack = new Stack<>();

        while(currentItem != null || !stack.isEmpty()) {
            while(currentItem != null) {
                stack.push(currentItem);
                currentItem = currentItem.previous();
            }

            currentItem = stack.pop();
            System.out.print(currentItem.getValue() + " ");
            currentItem = currentItem.next();
        }
    }


    public void traverseNoStackNoRecursion() {
        if(root == null) {
            System.out.println("The list is empty");
            return;
        }

        Item currentInOrder = root;
        Item previousInOrder;

        while(currentInOrder != null) {
            if(currentInOrder.previous() == null) {
                System.out.print(currentInOrder.getValue() + " ");
                currentInOrder = currentInOrder.next();
            } else {
                previousInOrder = currentInOrder.previous();

                while(previousInOrder.next() != null && previousInOrder.next() != currentInOrder) {
                    previousInOrder = previousInOrder.next();
                }

                if(previousInOrder.next() == null) {
                    previousInOrder.setNext(currentInOrder);    // temporary change made to the original tree
                    currentInOrder = currentInOrder.previous();
                } else {
                    previousInOrder.setNext(null);              // reversing the changes made to the original tree
                    System.out.print(currentInOrder.getValue() + " ");
                    currentInOrder = currentInOrder.next();
                }

            }
        }
    }
}












