package com.example.currencyexhange.currency.calculator;

import com.example.currencyexhange.currency.CurrencyRepository;

import java.math.BigDecimal;

public class SimpleExchangePriceCalculator implements ExchangePriceCalculator {

    private CurrencyRepository currencyRepository;

    public SimpleExchangePriceCalculator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public BigDecimal calculate(String fromCcy, String toCcy, int exchangeQty) {
        return null;
    }
}
