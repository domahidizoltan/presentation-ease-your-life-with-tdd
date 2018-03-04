package com.example.currencyexhange.currency;

import com.example.currencyexhange.currency.exception.DataAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CurrencyRepository {

    private RestTemplate restTemplate;
    private String exchangeUrl;

    private static final Map<String, String> currencyNameMap = new HashMap<>();
    static {
        currencyNameMap.put("huf", "Hungarian Forints");
        currencyNameMap.put("usd", "US Dollars");
        currencyNameMap.put("eur", "Euro");
    }

    public CurrencyRepository(RestTemplate restTemplate, String exchangeUrl) {
        this.restTemplate = restTemplate;
        this.exchangeUrl = exchangeUrl;
    }

    public double getRate(String fromCcy, String toCcy) {
        try {
            String rate = restTemplate.getForObject(exchangeUrl + fromCcy + "/" + toCcy, String.class);
            return Double.valueOf(rate);
        } catch (RestClientException ex) {
            throw new DataAccessException(ex.getMessage(), ex.getCause());
        }
    }

    public String getCurrencyName(String ccy) {
        return currencyNameMap.get(ccy);
    }
}
