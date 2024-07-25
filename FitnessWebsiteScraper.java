package com.example.androidapp_1;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FitnessWebsiteScraper {

    // Method to scrape data from a fitness website
    public static FitnessData scrapeFitnessWebsite(String url) {
        FitnessData fitnessData = new FitnessData();

        try {
            Document doc = Jsoup.connect(url).get();

            // Extract name
            Element nameElement = doc.select("selector_for_name").first();
            if (nameElement != null) {
                fitnessData.setName(nameElement.text());
            }

            // Extract logo URL
            Element logoElement = doc.select("dk-lg itm").first();
            if (logoElement != null) {
                fitnessData.setLogoUrl(logoElement.absUrl("src"));
            }

            // Extract benefits
            Elements benefitsElements = doc.select("selector_for_benefits");
            List<String> benefitsList = new ArrayList<>();
            for (Element element : benefitsElements) {
                benefitsList.add(element.text());
            }
            fitnessData.setBenefits(benefitsList);

            // Extract multiple price plans
            Elements priceElements = doc.select("selector_for_price");
            List<String> pricePlans = new ArrayList<>();
            for (Element element : priceElements) {
                pricePlans.add(element.text());
            }
            fitnessData.setPricePlans(pricePlans);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fitnessData;
    }
}