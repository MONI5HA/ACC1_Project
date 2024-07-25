package com.example.androidapp_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser {

    // Extract prices from a given URL
    public List<String> getTopPrices(String url) throws IOException {
        List<String> prices = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();

        // Example: Extract prices from elements with a class of "price"
        Elements priceElements = doc.select(".price"); // Adjust the selector based on actual HTML structure
        for (Element priceElement : priceElements) {
            prices.add(priceElement.text());
        }

        return prices;
    }
}
