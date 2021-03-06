import com.ssheld.BinarySearchTree.BST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Author: Stephen Sheldon 3/24/2019
 *
 * Reads in a file of people in the following format
 *
 * line x: name
 * line x+1: age
 *
 * Where name is a string with no spaces (only first name)
 * and age is an integer 1 < i < 100.
 */

public class PeopleTree {

    public static void main(String[] args) {

        BST peopleTree = new BST();
        String name;
        Integer age;
        int choice;
        String choiceName;
        Scanner fin;
        Scanner stdin;

        try {
            fin = new Scanner(new File("people.txt"));

            while(fin.hasNext()) {
                name = fin.nextLine();
                age = Integer.valueOf(Integer.parseInt(fin.nextLine()));
                peopleTree.put(name, age);
            }

            // Close the scanner
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find people.txt file to read in from.");
        }

        stdin = new Scanner(System.in);
        System.out.println("Hi there, please select from the following.");
        do {
            System.out.println("1. Delete a person from the tree");
            System.out.println("2. Print the tree in-order");
            System.out.println("3. Print the tree pre-order");
            System.out.println("4. Print the tree post-order");
            System.out.println("5. Get the age of a person");
            System.out.println("6. Print a list of people at each level of tree");
            System.out.println("7. Get the height of the tree");
            System.out.println("8. Get the distance a person is from root node");
            System.out.println("0. Exit the program");
            choice = stdin.nextInt();
            stdin.nextLine();
            System.out.println();
            if (choice == 1) {
                System.out.println("Please enter the name of the person you wish to delete.");
                choiceName = stdin.nextLine();
                peopleTree.delete(choiceName);
            }
            else if (choice == 2)
                peopleTree.inOrder();
            else if (choice == 3)
                peopleTree.preOrder();
            else if (choice == 4)
                peopleTree.postOrder();
            else if (choice == 5) {
                System.out.println("Please enter the name of the person");
                choiceName = stdin.nextLine();
                System.out.printf("The age of %s is %d%n", choiceName, peopleTree.get(choiceName));
            }
            else if (choice == 6) {
                LinkedList<String>[] depthList = peopleTree.depthLists();
                for (int i = 0; i < depthList.length; i++) {
                    System.out.printf("List at Depth of %d:", i);
                    for(String w : depthList[i]) {
                        System.out.printf(" %s", w);
                    }
                    System.out.println();
                }
            }
            else if (choice == 7) {
                System.out.printf("Current height of the BST is %d%n", peopleTree.height());
            }
            else if (choice == 8) {
                System.out.println("Please enter the name of the person you wish to search for.");
                choiceName = stdin.nextLine().toLowerCase();
                System.out.printf("%s is %d levels from root node%n", choiceName, peopleTree.distanceFromRoot(choiceName));
            }
            System.out.println();
        } while(choice != 0);
    }
}
