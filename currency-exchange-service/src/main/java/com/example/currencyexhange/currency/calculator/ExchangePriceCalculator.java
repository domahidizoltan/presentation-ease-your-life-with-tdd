package com.example.currencyexhange.currency.calculator;

import java.math.BigDecimal;

public interface ExchangePriceCalculator {

    BigDecimal calculate(String fromCcy, String toCcy, int exchangeQty);

}
