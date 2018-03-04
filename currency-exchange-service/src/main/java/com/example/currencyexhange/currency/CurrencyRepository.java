package com.example.currencyexhange.currency;

import com.example.currencyexhange.currency.exception.DataAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CurrencyRepository {

    private RestTemplate restTemplate;
    private String exchangeUrl;

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
}
