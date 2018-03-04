package com.example.currencyexhange.currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

    private static final String ANY_FROM_CCY = "eur";
    private static final String ANY_TO_CCY = "huf";
    private static final int ANY_EXCHANGE_QTY = 10;

    private CurrencyService underTest;

    @Before
    public void setUp() {
        underTest = new CurrencyService();
    }

    @Test
    public void shouldGetRateFromUpstreamService() {
        double actualExchangePrice = underTest.exchangeCurrencies(ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);

        assertEquals(100.1, actualExchangePrice, 0);
    }


}
