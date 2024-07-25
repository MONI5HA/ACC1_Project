package com.example.androidapp_1;

import java.util.HashMap;
import java.util.Map;

public class SearchFrequencyManager {
    private AVLTree avlTree;
    private Map<String, Integer> queryLog;

    public SearchFrequencyManager() {
        this.avlTree = new AVLTree();
        this.queryLog = new HashMap<>();
    }

    public AVLTree getAvlTree() {
        return avlTree;
    }

    public void insertQuery(String query) {
        String normalizedQuery = query.trim().toLowerCase();
        avlTree.insert(normalizedQuery);
    }

    public boolean searchQuery(String query) {
        String normalizedQuery = query.trim().toLowerCase();
        AVLTree.Node result = avlTree.search(normalizedQuery);
        if (result == null) {
            System.out.println("The query you are searching for does not exist.");
            return false;
        } else {
            avlTree.incrementFrequency(normalizedQuery);
            queryLog.put(normalizedQuery, result.frequency);
            System.out.println("Query found. Frequency updated.");
            return true;
        }
    }

    public Map<String, Integer> getQueryLog() {
        return queryLog;
    }

    public void displayAVLTree() {
        System.out.println("Current AVL Tree (Pre-Order Traversal):");
        avlTree.display();
    }

    public void displayQueryLog() {
        System.out.println("\nSearch Query Log:");
        queryLog.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    public void checkBalance(AVLTree.Node node) {
        if (node == null) return;
        int balance = avlTree.getBalance(node);
        System.out.println("Node: " + node.key + ", Balance: " + balance);
        checkBalance(node.left);
        checkBalance(node.right);
    }
}
