package com.example.currencyexhange.currency.calculator;

import com.example.currencyexhange.currency.CurrencyRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleExchangePriceCalculator implements ExchangePriceCalculator {

    private CurrencyRepository currencyRepository;
    private double benefit;

    public SimpleExchangePriceCalculator(CurrencyRepository currencyRepository, double benefit) {
        this.currencyRepository = currencyRepository;
        this.benefit = benefit;
    }

    @Override
    public BigDecimal calculate(String fromCcy, String toCcy, int exchangeQty) {
        double rate = currencyRepository.getRate(fromCcy, toCcy);
        BigDecimal exchangePrice = BigDecimal.valueOf(rate * exchangeQty * benefit).setScale(2, RoundingMode.UP);
        return exchangePrice;
    }
}
