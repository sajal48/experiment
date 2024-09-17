package com.sajal.experiment.ds.BTree;


import java.util.Collections;


public class BTreePrinter {
    private static final int INDENT_FACTOR = 4;

    private static <T extends Comparable<T>> void printNode(BTreeNode<T> node, int indentLevel, StringBuilder output) {
        if (node == null) {
            return;
        }

        output.append(String.join("", Collections.nCopies(indentLevel * INDENT_FACTOR, " ")));

        for (int i = 0; i < node.keys.size(); i++) {
            output.append(node.keys.get(i));
            if (i < node.keys.size() - 1) {
                output.append(" ");
            }
        }

        output.append("\n");

        for (int i = 0; i < node.children.size(); i++) {
            printNode(node.children.get(i), indentLevel + 1, output);
        }
    }

    public static <T extends Comparable<T>> void printTree(BTree<T> tree) {
        if (tree.root == null) {
            System.out.println("Tree is empty");
            return;
        }

        StringBuilder output = new StringBuilder();
        printNode(tree.root, 0, output);
        System.out.print(output.toString());
    }
}