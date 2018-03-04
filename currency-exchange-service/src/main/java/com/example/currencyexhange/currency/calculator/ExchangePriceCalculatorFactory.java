package com.example.currencyexhange.currency.calculator;

import java.util.Map;

public class ExchangePriceCalculatorFactory {

    private Map<String, ExchangePriceCalculator> calculatorMap;

    public ExchangePriceCalculatorFactory(Map<String, ExchangePriceCalculator> calculatorMap) {
        this.calculatorMap = calculatorMap;
    }

    public ExchangePriceCalculator getCalculator(String fromCcy) {
        String key = "simple";
        if (fromCcy.toLowerCase().startsWith("x")) {
            key = "crypto";
        }

        return calculatorMap.get(key);
    }

}
