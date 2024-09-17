package com.sajal.experiment.ds.BTree;

public class Example {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(3);

        // Insert keys
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(30);
        tree.insert(3);

        System.out.println("Initial B-Tree:");
        BTreePrinter.printTree(tree);
        System.out.println();

        // Search for keys
        System.out.println("Searching for key 15: " + tree.search(15));
        System.out.println("Searching for key 40: " + tree.search(40));
        System.out.println();

        // Insert more keys
        tree.insert(18);
        tree.insert(12);
        tree.insert(28);

        System.out.println("B-Tree after inserting 18, 12, and 28:");
        BTreePrinter.printTree(tree);
        System.out.println();

        // Delete keys
        tree.delete(20);
        tree.delete(5);

        System.out.println("B-Tree after deleting 20 and 5:");
        BTreePrinter.printTree(tree);
        System.out.println();
    }
}
