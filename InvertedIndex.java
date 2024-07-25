package com.example.androidapp_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndex {

    // Map from term to a list of document IDs where the term appears
    private Map<String, List<Integer>> indexMap;

    public InvertedIndex() {
        indexMap = new HashMap<>();
    }

    // Add a document with its content to the inverted index
    public void addDocument(int docId, String content) {
        String[] terms = content.split("\\s+");
        for (String term : terms) {
            term = term.toLowerCase(); // Normalize to lowercase
            indexMap.computeIfAbsent(term, k -> new ArrayList<>()).add(docId);
        }
    }

    // Method to retrieve the inverted index
    public Map<String, List<Integer>> getIndexMap() {
        return indexMap;
    }
}
