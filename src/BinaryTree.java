import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree implements Iterable<Integer> {

    private Node root;
    private int size;

    private class Node {
        private int key;
        private Node left, right;

        public Node(int key) {
            this.key = key;
        }
    }

    // конструктор
    public BinaryTree() {
        root = null;
        size = 0;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            size++;
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    public void displayTree() {
        displayTree(root);
        System.out.println();
    }

    private void displayTree(Node node) {
        if (node != null) {
            displayTree(node.left);
            System.out.print(node.key + " ");
            displayTree(node.right);
        }
    }
    public void height() {
        System.out.println("Height: " + height(root));
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        } else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public void is_empty() {
        System.out.println("Is empty: " + (size == 0));
    }

    public void remove(int key) {
        root = remove(root, key);
    }

    private Node remove(Node node, int key) {
        if (node == null) return null;
        if (key < node.key) {
            node.left = remove(node.left, key);
        } else if (key > node.key) {
            node.right = remove(node.right, key);
        } else {
            size--;
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    public int findMax() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        Node maxNode = root;
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }
        return maxNode.key;
    }
    public int findMin() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        Node minNode = root;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode.key;
    }

    public void findEvenAndOdd() {
        System.out.print("Even keys: ");
        findEvenOrOdd(root, true);
        System.out.println();
        System.out.print("Odd keys: ");
        findEvenOrOdd(root, false);
        System.out.println();
    }

    private void findEvenOrOdd(Node node, boolean even) {
        if (node != null) {
            findEvenOrOdd(node.left, even);
            if (even && node.key % 2 == 0) {
                System.out.print(node.key + " ");
            } else if (!even && node.key % 2 != 0) {
                System.out.print(node.key + " ");
            }
            findEvenOrOdd(node.right, even);
        }
    }

    public void delete_tree() {
        root = null;
        size = 0;
        System.out.println("Tree deleted");
    }

    //  inorder traversal iterator
    @Override
    public Iterator<Integer> iterator() {
        return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<Integer> {
        private Stack<Node> stack = new Stack<>();

        public BinaryTreeIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }
}
