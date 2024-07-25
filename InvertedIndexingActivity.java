package com.example.androidapp_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndexingActivity extends AppCompatActivity {

    private EditText searchQueryEditText;
    private Button searchButton;
    private TextView resultsTextView;

    private static List<Product> products = new ArrayList<>();
    private static Trie invertedIndex = new Trie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverted_indexing);

        searchQueryEditText = findViewById(R.id.search_query_edit_text);
        searchButton = findViewById(R.id.search_button);
        resultsTextView = findViewById(R.id.results_text_view);

        String csvFile = "path/to/your/pageText.txt";
        readProductData(csvFile);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchQueryEditText.getText().toString();
                Map<String, Integer> searchResults = searchProducts(searchQuery);
                displayResults(searchResults);
            }
        });
    }

    private void readProductData(String csvFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming CSV format is the same as your provided structure
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String category = parts[0].trim();
                    String name = parts[1].trim();
                    String imageUrl = parts[2].trim();
                    String annualFee = parts[3].trim();
                    String purchaseRate = parts[4].trim();
                    String link = parts[5].trim();

                    Product product = new Product(category, name, imageUrl, annualFee, purchaseRate, link);
                    products.add(product);
                    insertProductIntoTrie(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertProductIntoTrie(Product product) {
        String[] attributes = {
                product.getName(),
                product.getCategory(),
                product.getAnnualFee(),
                product.getPurchaseRate()
        };

        for (String attribute : attributes) {
            String[] tokens = tokenize(attribute);
            for (String token : tokens) {
                invertedIndex.insert(token, product.getLink());
            }
        }
    }

    private Map<String, Integer> searchProducts(String query) {
        List<String> searchKeywords = Arrays.asList(query.toLowerCase().split("\\s+"));
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String keyword : searchKeywords) {
            List<String> matchedLinks = invertedIndex.search(keyword);
            for (String link : matchedLinks) {
                frequencyMap.put(link, frequencyMap.getOrDefault(link, 0) + 1);
            }
        }

        return frequencyMap;
    }

    private void displayResults(Map<String, Integer> searchResults) {
        StringBuilder results = new StringBuilder("Inverted Indexing Results:\n");
        for (Map.Entry<String, Integer> entry : searchResults.entrySet()) {
            results.append("Link: ").append(entry.getKey())
                    .append(" | Frequency: ").append(entry.getValue()).append("\n");
        }
        resultsTextView.setText(results.toString());
    }

    private String[] tokenize(String text) {
        return text.toLowerCase().split("\\s+|(?=[^a-zA-Z0-9])|(?<=[^a-zA-Z0-9])");
    }

    static class Product {
        private String category;
        private String name;
        private String imageUrl;
        private String annualFee;
        private String purchaseRate;
        private String link;

        public Product(String category, String name, String imageUrl, String annualFee, String purchaseRate, String link) {
            this.category = category;
            this.name = name;
            this.imageUrl = imageUrl;
            this.annualFee = annualFee;
            this.purchaseRate = purchaseRate;
            this.link = link;
        }

        public String getCategory() { return category; }
        public String getName() { return name; }
        public String getImageUrl() { return imageUrl; }
        public String getAnnualFee() { return annualFee; }
        public String getPurchaseRate() { return purchaseRate; }
        public String getLink() { return link; }
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<String> links = new ArrayList<>();
    }

    static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String word, String link) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children.computeIfAbsent(c, k -> new TrieNode());
            }
            node.links.add(link);
        }

        public List<String> search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children.get(c);
                if (node == null) {
                    return Collections.emptyList();
                }
            }
            return node.links;
        }
    }
}
