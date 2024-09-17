package com.sajal.experiment.Algo;

public class BinarySearch {

    public static int binarySearchInsertPosition(int[] array, int low, int high, int value) {
        if (low >= high) {
            if (array[low] < value) {
                return low + 1;
            } else {
                return low;
            }
        }
        int mid = (low + high) / 2;

        if (array[mid] == value) {
            return mid;
        } else if (array[mid] < value) {
            return binarySearchInsertPosition(array, mid + 1, high, value);
        } else {
            return binarySearchInsertPosition(array, low, mid - 1, value);
        }
    }
}
