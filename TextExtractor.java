package com.example.androidapp_1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class TextExtractor {

    // Declare patterns as static constants
    private static final Pattern DATE_PATTERN = Pattern.compile("\\b\\d{4}-\\d{2}-\\d{2}\\b");
    private static final Pattern URL_PATTERN = Pattern.compile("(http|https)://[a-zA-Z0-9./?=_-]+");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\b\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{4}\\b");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
    private static final Pattern PRICE_PATTERN = Pattern.compile("\\$?\\d+\\.\\d{2}\\s?(USD|CAD)?");

    public static List<String> extractDates(String text) {
        return extractPattern(text, DATE_PATTERN);
    }

    public static List<String> extractURLs(String text) {
        return extractPattern(text, URL_PATTERN);
    }

    public static List<String> extractPhoneNumbers(String text) {
        return extractPattern(text, PHONE_PATTERN);
    }

    public static List<String> extractEmailAddresses(String text) {
        return extractPattern(text, EMAIL_PATTERN);
    }

    public static List<String> extractPrices(String text) {
        return extractPattern(text, PRICE_PATTERN);
    }

    private static List<String> extractPattern(String text, Pattern pattern) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }
}
