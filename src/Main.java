public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(2);
        binaryTree.insert(4);
        binaryTree.insert(7);
        binaryTree.insert(6);
        binaryTree.insert(8);

        binaryTree.displayTree();

        binaryTree.height();
        binaryTree.is_empty();

        System.out.println();

        System.out.println("Removing 3:");
        binaryTree.remove(3);
        binaryTree.displayTree();
        System.out.println();
        binaryTree.height();
        System.out.println(binaryTree.findMax());
        System.out.println(binaryTree.findMin());
        System.out.println();

        binaryTree.findEvenAndOdd();

        binaryTree.delete_tree();
        binaryTree.is_empty();


        binaryTree.height();
    }
}
