package com.example.currencyexhange.currency.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CurrencyRepository {

    private RestTemplate restTemplate;
    private String exchangeUrl;

    public CurrencyRepository(RestTemplate restTemplate, @Value("${config.exchangeUrl}") String exchangeUrl) {
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
}
