package com.example.currencyexchange.helper;

public class CurrencyNameHelper {

    public static String getCurrencyName(String ccy) {
        String currency = "";
        if ("huf".equals(ccy)) {
            currency = "Hungarian Forints";
        } else if ("usd".equals(ccy)) {
            currency = "US Dollars";
        } else if ("eur".equals(ccy)) {
            currency = "Euro";
        }
        return currency;
    }

}
