import com.binarySearchTree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.addItemNonRecursive("6");
        bst.addItemNonRecursive("1");
        bst.addItemNonRecursive("9");
        bst.addItemNonRecursive("2");
        bst.addItemNonRecursive("8");
        bst.addItemNonRecursive("3");
        bst.addItemNonRecursive("7");
        bst.addItemNonRecursive("4");
        bst.addItemNonRecursive("5");
        bst.addItemNonRecursive("5");


        System.out.println("\n\n");
        bst.traverseList();
        System.out.println();
        bst.traverseWithStackAndNoRecursion();
        System.out.println();
        bst.traverseNoStackNoRecursion();
    }
}
