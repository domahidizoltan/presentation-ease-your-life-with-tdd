package com.example.currencyexhange.currency.calculator;

import java.util.Map;

public class ExchangePriceCalculatorFactory {

    private Map<String, ExchangePriceCalculator> calculatorMap;

    public ExchangePriceCalculatorFactory(Map<String, ExchangePriceCalculator> calculatorMap) {
        this.calculatorMap = calculatorMap;
    }

    public ExchangePriceCalculator getCalculator(String fromCcy) {
        return null;
    }

}
