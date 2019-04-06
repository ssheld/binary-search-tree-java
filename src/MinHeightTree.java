import com.ssheld.BinarySearchTree.BST;

import java.util.LinkedList;

/**
 * Author: Stephen Sheldon 4/5/2019
 *
 * Problem: Given an array of unique sorted integers, construct a BST
 *          with minimum height.
 */

public class MinHeightTree {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        BST<Integer, Integer> myBST = new BST();

        minTree(arr, 0, arr.length-1, myBST);

        System.out.println("The results are in...");

        System.out.printf("The height of you're tree is %d%n%n", myBST.height());

        myBST.preOrder();

        System.out.printf("Grabbing the lists of each depth...%n%n");

        LinkedList<Integer>[] depthList = myBST.depthLists();

        for (int i = 0; i < depthList.length; i++) {
            System.out.printf("List at Depth of %d:", i);
            for(int w : depthList[i]) {
                System.out.printf(" %d", w);
            }
            System.out.println();
        }
    }

    private static void minTree(int[] arr, int lo, int hi, BST<Integer, Integer> myBST) {
        if (lo > hi) {
            return;
        }

        int mid = (hi+lo)/2;

        myBST.put(arr[mid], arr[mid]);

        minTree(arr, lo, mid-1, myBST);
        minTree(arr, mid+1, hi, myBST);
    }
}
