package datastructures;

public class AVLTree<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left, right;
        int height;

        public Node(T data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;
    private int height(Node node) {return (node == null) ? 0 : node.height;}
    private int getBalance(Node node) {return (node == null) ? 0 : height(node.left) - height(node.right);}

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(T data) {root = insertRec(root, data);}
    private Node insertRec(Node node, T data) {
        if (node == null) {return new Node(data);}

        if (data.compareTo(node.data) < 0) {node.left = insertRec(node.left, data);} 
        else if (data.compareTo(node.data) > 0) {node.right = insertRec(node.right, data);} 
        else {return node;}

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {node.left = rotateLeft(node.left);}
            return rotateRight(node);
        }
        if (balance < -1) {
            if (getBalance(node.right) > 0) {node.right = rotateRight(node.right);}
            return rotateLeft(node);
        }
        return node;
    }

    public boolean search(T data) {return searchRec(root, data);}
    private boolean searchRec(Node node, T data) {
        if (node == null) return false;
        if (data.compareTo(node.data) < 0) return searchRec(node.left, data);
        else if (data.compareTo(node.data) > 0) return searchRec(node.right, data);
        else return true;
    }

    private Node findMin(Node node) {
        while (node.left != null) {node = node.left;}
        return node;
    }

    public void delete(T data) {root = deleteRec(root, data);}
    private Node deleteRec(Node node, T data) {
        if (node == null) return node;

        if (data.compareTo(node.data) < 0) {node.left = deleteRec(node.left, data);} 
        else if (data.compareTo(node.data) > 0) {node.right = deleteRec(node.right, data);} 
        else {
            if (node.left == null) {return node.right;} 
            else if (node.right == null) {return node.left;}

            Node temp = findMin(node.right);
            node.data = temp.data;
            node.right = deleteRec(node.right, temp.data);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    public void inOrderTraversal() {inOrderRec(root);}
    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.data);
            inOrderRec(node.right);
        }
    }
}
