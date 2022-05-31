package com.binarySearchTree;

import java.util.Stack;

public class BinarySearchTree implements List {
    private Item root = null;
    private boolean foundSearchedItem = false;
    private Item lastFoundItem;

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
                    System.out.println(addedValue + " added successfully");
                    quit = true;
                } else {
                    currentItem = currentItem.previous();
                }
            } else if(comparison < 0) {
                if(currentItem.next() == null) {
                    currentItem.setNext(addedItem);
                    System.out.println(addedValue + " added successfully");
                    quit = true;
                } else {
                    currentItem = currentItem.next();
                }
            } else {       // no duplicates allowed
                System.out.println(addedValue + " already exists in the tree. Duplicates not allowed");
                quit = true;
            }
        }
    }

    @Override
    public void removeItem(Object valueToRemove) {
        if(root == null) {
            System.out.println("The list is empty, nothing to remove here");
            return;
        }
        
        Item itemToRemove = new Node(valueToRemove);
        removeItemRecursively(root, itemToRemove);

        if(foundSearchedItem) {
            System.out.println("\"" + valueToRemove + "\" removed successfully");
            foundSearchedItem = false;
        } else {
            System.out.println("\"" + valueToRemove + "\" not found in the tree");
        }
    }

    private Item removeItemRecursively(Item currentItem, Item itemToRemove) {
        if(currentItem == null) {
            return null;
        }

        int comparison = currentItem.compareTo(itemToRemove);

        if(comparison > 0) {
            currentItem.leftLink = removeItemRecursively(currentItem.leftLink, itemToRemove);
            return currentItem;

        } else if(comparison < 0) {
            currentItem.rightLink = removeItemRecursively(currentItem.rightLink, itemToRemove);
            return currentItem;
        }

        return processRemoval(currentItem);
    }

    private Item processRemoval(Item removedItem) {
        foundSearchedItem = true;

        if(removedItem.previous() == null && removedItem.next() == null) {     // when removed item has no children
            return null;
        } else if(removedItem.previous() != null && removedItem.next() == null) {   // when removed item has left subtree only
            return removedItem.previous();
        } else if(removedItem.previous() == null && removedItem.next() != null) {   // when removed item has right subtree only
            return removedItem.next();
        } else {
            Item smallestRightItem = findSmallestRightItem(removedItem.rightLink);
            removedItem.setValue(smallestRightItem.getValue());
            removedItem.rightLink = removeItemRecursively(removedItem.rightLink, smallestRightItem);
            return removedItem;
        }
    }

    private Item findSmallestRightItem(Item item) {
        return item.leftLink == null ? item : findSmallestRightItem(item.leftLink);
    }

    @Override
    public Item containsItem(Object searchedValue) {
        if(root == null) {
            System.out.println("The list is empty, nothing to remove here");
            return null;
        }

        Item searchedItem = new Node(searchedValue);
        containsItemRecursive(root, searchedItem);

        if(foundSearchedItem) {
            System.out.println(searchedValue + " found in the tree");
            foundSearchedItem = false;
        } else {
            System.out.println(searchedValue + " not found in the tree");
        }
        return null;        // no further processing expected using this method; containsItemRecursive(Item, Item) planned to be used for removeItem(Object)
    }


    private Item containsItemRecursive(Item currentItem, Item searchedItem) {
        if(currentItem == null) {
            return null;
        }

        lastFoundItem = null;
        int comparison = currentItem.compareTo(searchedItem);

        if(comparison > 0) {
            currentItem.leftLink = containsItemRecursive(currentItem.leftLink, searchedItem);
        } else if(comparison < 0) {
            currentItem.rightLink = containsItemRecursive(currentItem.rightLink, searchedItem);
        } else {
            foundSearchedItem = true;
            lastFoundItem = currentItem;
        }

        return currentItem;  // in case the searchedValue equals the currentItem value
    }

    public void getContainsItemLastResult() {
        if(lastFoundItem != null) {
            System.out.println("Last containsItem() value was: " + lastFoundItem.getValue());
        } else {
            System.out.println("No item found in recent search");
        }
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












