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
            get(x.left, key);
        // Otherwise our key is greater than our current node so traverse right
        else
            get(x.right, key);
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
            put(x.left, key, val);
        // Our key is greater than current key so move right
        else if (cmp > 0)
            put(x.right, key, val);
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
        postOrder(x.right);
        System.out.println("Key: " + x.key + " Value: " + x.val);
        postOrder(x.left);
    }

    // Check if the tree is currently empty
    public boolean isEmpty() {
        return size() == 0;
    }
}
