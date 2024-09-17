package com.sajal.experiment.ds.BTree;

import java.util.Collections;

public class BTree<T extends Comparable<T>> {
    private int order; // Maximum number of keys in a node
     BTreeNode<T> root;

    public BTree(int order) {
        this.order = order;
        root = null;
    }

    private BTreeNode<T> search(BTreeNode<T> node, T key) {
        int i = 0;
        while (i < node.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        if (i < node.size() && key.compareTo(node.keys.get(i)) == 0) {
            return node;
        } else if (node.isLeaf()) {
            return null;
        } else {
            return search(node.children.get(i), key);
        }
    }

    public void insert(T key) {
        if (root == null) {
            root = new BTreeNode<>(order);
            root.keys.add(key);
        } else {
            BTreeNode<T> node = root;
            if (node.size() == 2 * order - 1) {
                BTreeNode<T> newRoot = new BTreeNode<>(order);
                newRoot.children.add(root);
                root = newRoot;
                splitChild(root, 0);
                insertNonFull(root, key);
            } else {
                insertNonFull(root, key);
            }
        }
    }

    private void splitChild(BTreeNode<T> parent, int index) {
        BTreeNode<T> child = parent.children.get(index);
        BTreeNode<T> newChild = new BTreeNode<>(order);

        if (child == null) {
            // Handle the case when splitting the root node
            child = new BTreeNode<>(order);
            child.keys.addAll(parent.keys);
            parent.keys.clear();
            newChild.keys.addAll(child.keys.subList(order, child.size()));
            child.keys.retainAll(child.keys.subList(0, order - 1));
            parent.children.add(child);
            parent.children.add(newChild);
            return;
        }

        newChild.keys.addAll(child.keys.subList(order - 1, child.size()));
        child.keys.retainAll(child.keys.subList(0, order - 1));

        if (!child.isLeaf()) {
            newChild.children.addAll(child.children.subList(order, child.children.size()));
            child.children.retainAll(child.children.subList(0, order));
        }

        parent.keys.add(index, newChild.keys.get(0));
        parent.children.add(index + 1, newChild);
    }
    private void insertNonFull(BTreeNode<T> node, T key) {
        int i = node.size() - 1;
        if (node.isLeaf()) {
            node.keys.add(key);
            Collections.sort(node.keys);
        } else {
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            i++;
            BTreeNode<T> child = node.children.get(i);
            if (child.size() == 2 * order - 1) {
                splitChild(node, i);
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }
    public boolean search(T key) {
        BTreeNode<T> node = search(root, key);
        return node != null;
    }
    private void deleteFromLeaf(BTreeNode<T> node, T key) {
        int i = 0;
        while (i < node.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        if (i < node.size() && key.compareTo(node.keys.get(i)) == 0) {
            node.keys.remove(i);
            return;
        }
    }
    private void deleteFromNonLeaf(BTreeNode<T> node, T key) {
        int i = 0;
        while (i < node.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        if (i < node.size() && key.compareTo(node.keys.get(i)) == 0) {
            T predecessor = getPredecessor(node, i);
            node.keys.set(i, predecessor);
            deleteKey(root, predecessor);
        } else {
            BTreeNode<T> child = node.children.get(i);
            if (child.size() < order) {
                fill(node, i);
            }
            if (i == node.size() && !node.isLeaf()) {
                child = node.children.get(i);
                fill(node, i);
            }
            deleteKey(child, key);
        }
    }

    private T getPredecessor(BTreeNode<T> node, int index) {
        BTreeNode<T> child = node.children.get(index);
        if (child.isLeaf()) {
            return child.keys.get(child.size() - 1);
        } else {
            return getPredecessor(child, child.size());
        }
    }

    private void fill(BTreeNode<T> node, int index) {
        if (index != 0 && node.children.get(index - 1).size() >= order) {
            borrowFromLeft(node, index);
        } else if (index != node.size() && node.children.get(index + 1).size() >= order) {
            borrowFromRight(node, index);
        } else {
            if (index != node.size()) {
                merge(node, index);
            } else {
                merge(node, index - 1);
            }
        }
    }
    private void borrowFromLeft(BTreeNode<T> node, int index) {
        BTreeNode<T> child = node.children.get(index);
        BTreeNode<T> leftSibling = node.children.get(index - 1);

        child.keys.add(0, node.keys.get(index - 1));
        node.keys.set(index - 1, leftSibling.keys.remove(leftSibling.size() - 1));

        if (!child.isLeaf()) {
            child.children.add(0, leftSibling.children.remove(leftSibling.children.size() - 1));
        }
    }
    private void borrowFromRight(BTreeNode<T> node, int index) {
        BTreeNode<T> child = node.children.get(index);
        BTreeNode<T> rightSibling = node.children.get(index + 1);

        child.keys.add(node.keys.get(index));
        node.keys.set(index, rightSibling.keys.remove(0));

        if (!child.isLeaf()) {
            child.children.add(rightSibling.children.remove(0));
        }
    }
    private void merge(BTreeNode<T> node, int index) {
        BTreeNode<T> child = node.children.get(index);
        BTreeNode<T> sibling = node.children.get(index + 1);

        child.keys.add(node.keys.get(index));
        child.keys.addAll(sibling.keys);

        if (!child.isLeaf()) {
            child.children.addAll(sibling.children);
        }

        node.keys.remove(index);
        node.children.remove(index + 1);

        if (node.size() == 0 && node != root) {
            root = child;
        }
    }
    public void delete(T key) {
        if (root == null) {
            return;
        }

        deleteKey(root, key);

        if (root.size() == 0) {
            if (root.isLeaf()) {
                root = null;
            } else {
                root = root.children.get(0);
            }
        }
    }
    private void deleteKey(BTreeNode<T> node, T key) {
        int i = 0;
        while (i < node.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        if (node.isLeaf()) {
            if (i < node.size() && key.compareTo(node.keys.get(i)) == 0) {
                node.keys.remove(i);
            }
        } else {
            if (i < node.size() && key.compareTo(node.keys.get(i)) == 0) {
                deleteFromNonLeaf(node, key);
            } else {
                BTreeNode<T> child = node.children.get(i);
                if (child.size() < order) {
                    fill(node, i);
                }
                if (i == node.size() && !node.isLeaf()) {
                    child = node.children.get(i);
                    fill(node, i);
                }
                deleteKey(child, key);
            }
        }
    }



}
