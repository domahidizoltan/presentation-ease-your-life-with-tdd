package com.example.currencyexhange.currency;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyService {

    private CurrencyRepository currencyRepository;
    private double benefit;

    public CurrencyService(CurrencyRepository currencyRepository, @Value("@{config.exchange.benefit}") double benefit) {
        this.currencyRepository = currencyRepository;
        this.benefit = benefit;
    }

    public BigDecimal exchangeCurrencies(String fromCcy, String toCcy, int exchangeQty) {
        double rate = currencyRepository.getRate(fromCcy, toCcy);
        BigDecimal exchangePrice = BigDecimal.valueOf(rate * exchangeQty * benefit).setScale(2, RoundingMode.UP);
        return exchangePrice;
    }

}
