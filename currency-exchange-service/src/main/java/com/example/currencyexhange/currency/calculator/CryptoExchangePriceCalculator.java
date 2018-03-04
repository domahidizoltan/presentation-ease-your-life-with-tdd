package com.example.currencyexhange.currency.calculator;

import com.example.currencyexhange.currency.CurrencyRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CryptoExchangePriceCalculator implements ExchangePriceCalculator {

    private CurrencyRepository currencyRepository;
    private double benefit;
    private String baseCurrency;

    public CryptoExchangePriceCalculator(CurrencyRepository currencyRepository, double benefit, String baseCurrency) {
        this.currencyRepository = currencyRepository;
        this.benefit = benefit;
        this.baseCurrency = baseCurrency;
    }

    @Override
    public BigDecimal calculate(String fromCcy, String toCcy, int exchangeQty) {
        double cryptoRate = currencyRepository.getRate(fromCcy, baseCurrency);
        double rate = currencyRepository.getRate(baseCurrency, toCcy);
        BigDecimal exchangePrice = BigDecimal.valueOf(cryptoRate * rate * exchangeQty * benefit).setScale(2, RoundingMode.UP);
        return exchangePrice;
    }
}
