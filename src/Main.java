import com.binarySearchTree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.addItem("6");
        bst.addItem("1");
        bst.addItem("9");
        bst.addItem("2");
        bst.addItem("8");
        bst.addItem("3");
        bst.addItem("7");
        bst.addItem("4");
        bst.addItem("5");

        System.out.println("\n\n");
        bst.traverseList();
        System.out.println();
        bst.traverseWithStackAndNoRecursion();
        System.out.println();
        bst.traverseNoStackNoRecursion();
    }
}
