package com.sajal.experiment.ds.BTree;

import java.util.ArrayList;
import java.util.List;

class BTreeNode<T extends Comparable<T>> {
    int order; // Maximum number of keys in the node
    List<T> keys; // Sorted list of keys
    List<BTreeNode<T>> children; // Child nodes

    BTreeNode(int order) {
        this.order = order;
        keys = new ArrayList<>();
        children = new ArrayList<>();
        children.add(null); // First child is null
    }

    int size() {
        return keys.size();
    }

    boolean isLeaf() {
        return children.get(0) == null;
    }
}
