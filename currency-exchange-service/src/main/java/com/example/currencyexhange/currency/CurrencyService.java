package com.example.currencyexhange.currency;

import com.example.currencyexhange.currency.calculator.ExchangePriceCalculator;
import com.example.currencyexhange.currency.calculator.ExchangePriceCalculatorFactory;

import java.math.BigDecimal;

public class CurrencyService {

    private CurrencyRepository currencyRepository;
    private ExchangePriceCalculatorFactory exchangePriceCalculatorFactory;

    public CurrencyService(CurrencyRepository currencyRepository, ExchangePriceCalculatorFactory exchangePriceCalculatorFactory) {
        this.currencyRepository = currencyRepository;
        this.exchangePriceCalculatorFactory = exchangePriceCalculatorFactory;
    }

    public BigDecimal exchangeCurrencies(String fromCcy, String toCcy, int exchangeQty) {
        ExchangePriceCalculator calculator = exchangePriceCalculatorFactory.getCalculator(fromCcy);
        BigDecimal exchangePrice = calculator.calculate(fromCcy, toCcy, exchangeQty);
        return exchangePrice;
    }

    public String getNameOfCurrency(String ccy) {
        return currencyRepository.getCurrencyName(ccy);
    }
}
