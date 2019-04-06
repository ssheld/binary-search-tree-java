package com.ssheld.BinarySearchTree;

import java.util.NoSuchElementException;

/**
 * Author: Stephen Sheldon 3/23/2019
 *
 * Binary Search Tree implementation utilizing key value pairs
 *
 * Time Complexity:
 * O(logn) - Best case
 * O(n) - Worst case
 * Complexity will depend on the order in which keys are inserted. In the
 * best case we have a balanced tree and our time complexity is logn. On the
 * worst case there could be n nodes on the search path. Typically we are closer
 * to the best case than we are the worst case if we assume that the keys are
 * (uniformly) randomly inserted.
 *
 */

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // Our BST root

    private class Node {
        private Key key; // key
        private Value val; // value
        private Node left, right; // left and right subtrees
        private int n; // number of nodes in subtree rooted here

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    // Get the size of the tree
    public int size() {
        return size(root);
    }

    // Private size method
    private int size(Node x) {
        // Check if our node is null
        if (x == null)
            return 0;
        // Otherwise return n inside node, the # of subtrees
        else
            return x.n;
    }

    // public facing method to return the value of a specific key
    public Value get(Key key) {
        return get(root, key);
    }

    // Private overloaded method to return value of specific key
    public Value get(Node x, Key key) {
        if (x == null)
            return null; // no subtree at this node
        // Compare the keys
        int cmp = key.compareTo(x.key);

        // Our key is smaller than current node tree so traverse left
        if (cmp < 0)
            return get(x.left, key);
        // Otherwise our key is greater than our current node so traverse right
        else if (cmp > 0)
            return get(x.right, key);
        // We've found the key! Return the associated value
        return x.val;
    }

    // Public facing method to put key value pair into tree
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    // Private method to put key value pair into tree
    private Node put(Node x, Key key, Value val) {
        // Base case: our node is null so insert here
        if (x == null)
            return new Node(key, val, 1);

        // Check to see if key is less or greater than current node
        int cmp = key.compareTo(x.key);
        // Our key is smaller than current node key so move left
        if (cmp < 0)
            x.left = put(x.left, key, val);
        // Our key is greater than current key so move right
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        // If the key we wish to insert is equal to the key of current node
        // then let's change the value
        else
            x.val = val;
        // Update the size. We add a +1 to each node up the tree
        // to account for our new node we've inserted
        x.n = size(x.left) + size(x.right) + 1;
        // Return node x which resets the link of each parent to it's child
        // all the way up to our root.
        return x;
    }

    // Get the min key in tree
    public Key min() {
        // Check if tree is empty
        if (isEmpty())
            throw new NoSuchElementException();
        Node x = min(root);
        return x.key;
    }

    // Private method to get min key in tree
    private Node min(Node x) {
        // Check if left is empty, if it is we know that
        // our current node is going to be the smallest key
        if (x.left == null)
            return x;
        // If it's not then let's keep going left
        return min(x.left);
    }

    // Return the floor of a key
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (isEmpty())
            throw new NoSuchElementException();
        return x.key;
    }

    // Private floor method
    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);

        if (cmp == 0)
            return x;
        else if (cmp < 0)
            return floor(x.left, key);

        // If key is greater than key at current node x
        // then we need to go down the right subtree
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    // Root the max key in the tree
    public Key max() {
        // Check if tree is empty
        if (isEmpty())
            throw new NoSuchElementException();
        Node x = max(root);
        return x.key;
    }

    // Private max method
    private Node max(Node x) {
        // First let's check if right subtree is null
        if (x.right == null)
            return x;
        // Otherwise let's keep going right
        return max(x.right);
    }

    // Return ceiling of key
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (isEmpty())
            throw new NoSuchElementException();
        return x.key;
    }

    // Private ceiling method
    private Node ceiling(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);

        // The keys are equal
        if (cmp == 0)
            return x;
        // The key at the current node is less than the key
        // we are looking for, so let's traverse right
        else if (cmp > 0)
            return ceiling(x.right, key);

        Node t = ceiling(x.left, key);

        if (t != null)
            return t;
        else
            return x;
    }

    // Pre-order traversal of tree that prints key and value
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node x) {
        if (x == null)
            return;
        System.out.println("Key: " + x.key + " Value: " + x.val);
        preOrder(x.left);
        preOrder(x.right);
    }

    // In-order traversal of tree that prints key and value
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node x) {
        if (x == null)
            return;
        inOrder(x.left);
        System.out.println("Key: " + x.key + " Value: " + x.val);
        inOrder(x.right);
    }

    // Post-order traversal of tree that prints key and value
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node x) {
        if (x == null)
            return;
        postOrder(x.left);
        postOrder(x.right);
        System.out.println("Key: " + x.key + " Value: " + x.val);
    }

    // Returns key with k number of keys smaller
    public Key select(int k) {
        if (k < 0 || k >= size())
            throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null)
            return x;
        // Check size of the left
        int t = size(x.left);
        // If there exist more than k nodes on the left then
        // let's traverse down there
        if (t > k)
            return select(x.left, k);
        else if (t < k)
            return select(x.right, k-t-1);
        else
            return x;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    // Return number of keys less than key in the subtree rooted at x
    private int rank(Node x, Key key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return rank(x.left, key);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(x.right, key);
        else
            return size(x.left);
    }

    // Delete min key from tree
    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size (x.right) + 1;
        return x;
    }

    // Method to delete node with a specific key
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        // First check if null
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);

        // Head left and relink the tree down the left side of current node
        if (cmp < 0)
            x.left = delete(x.left, key);
        // Head right and relink the tree down the right side of current node
        else if (cmp > 0)
            x.right = delete(x.right, key);
        // Otherwise we've found the node we wish to delete.
        else {
            // Case: Node with only right child
            if (x.left == null) {
                return x.right;
            }
            // Case : Node with only left child
            else if (x.right == null) {
                return x.left;
            }
            // Case : node has two children
            // Save reference to node to be deleted
            Node t = x;
            // The min of it's right subtree will take it's place
            x = min(t.right);
            // Now delete that min from the right subtree
            x.right = deleteMin(t.right);
            // And finally set the left link of x (min) to left subtree
            // of the node we wish to delete
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    // Return the current height of the tree (root node inclusive)
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        int left = height(node.left) + 1;
        int right = height(node.right) + 1;

        if (left > right) {
            return left;
        }
        return right;
    }

    // Check if the tree is currently empty
    public boolean isEmpty() {
        return size() == 0;
    }
}
