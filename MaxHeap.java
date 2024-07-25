package com.example.androidapp_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap {
    private List<Page> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Method to insert a page into the heap
    public void insert(Page page, int pageNumber) {
        heap.add(page);
        heapifyUp(heap.size() - 1);
    }

    // Method to extract the maximum (top) element from the heap
    public Page extractMax() {
        if (heap.isEmpty()) return null;

        // Swap the first and last element
        Collections.swap(heap, 0, heap.size() - 1);
        Page maxPage = heap.remove(heap.size() - 1);
        heapifyDown(0);

        return maxPage;
    }

    // Helper method to maintain the heap property while adding an element
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).rank <= heap.get(parentIndex).rank) break;

            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
        }
    }

    // Helper method to maintain the heap property while removing the top element
    private void heapifyDown(int index) {
        int size = heap.size();
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        if (leftChild < size && heap.get(leftChild).rank > heap.get(largest).rank) {
            largest = leftChild;
        }

        if (rightChild < size && heap.get(rightChild).rank > heap.get(largest).rank) {
            largest = rightChild;
        }

        if (largest != index) {
            Collections.swap(heap, index, largest);
            heapifyDown(largest);
        }
    }

    // Method to check if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Method to get the size of the heap
    public int size() {
        return heap.size();
    }
}
