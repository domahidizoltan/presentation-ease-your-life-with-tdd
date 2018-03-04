package com.example.currencyexhange.currency.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.ANY_FROM_CCY;
import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.ANY_FROM_XCCY;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class ExchangePriceCalculatorFactoryTest {

    private ExchangePriceCalculatorFactory underTest;

    @Before
    public void setUp() {
        Map<String, ExchangePriceCalculator> calculatorMap = new HashMap<>();
        calculatorMap.put("simple", new SimpleExchangePriceCalculator(null, 0));
        calculatorMap.put("crypto", new CryptoExchangePriceCalculator(null, 0, ""));
        underTest = new ExchangePriceCalculatorFactory(calculatorMap);
    }

    @Test
    public void shouldReturnSimplePriceCalculator() {
        ExchangePriceCalculator calculator = underTest.getCalculator(ANY_FROM_CCY);

        assertTrue(calculator instanceof SimpleExchangePriceCalculator);
    }

    @Test
    public void shouldReturnCryptoPriceCalculator() {
        ExchangePriceCalculator calculator = underTest.getCalculator(ANY_FROM_XCCY);

        assertTrue(calculator instanceof CryptoExchangePriceCalculator);
    }

}
