package com.example.currencyexhange.currency.calculator;

import com.example.currencyexhange.currency.CurrencyRepository;

import java.math.BigDecimal;

public class CryptoExchangePriceCalculator implements ExchangePriceCalculator {

    private CurrencyRepository currencyRepository;

    public CryptoExchangePriceCalculator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public BigDecimal calculate(String fromCcy, String toCcy, int exchangeQty) {
        return null;
    }
}
