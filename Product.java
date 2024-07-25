// Product.java
package com.example.androidapp_1; // Replace with your actual package name

public class Product {
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

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAnnualFee() {
        return annualFee;
    }

    public String getPurchaseRate() {
        return purchaseRate;
    }

    public String getLink() {
        return link;
    }
}
