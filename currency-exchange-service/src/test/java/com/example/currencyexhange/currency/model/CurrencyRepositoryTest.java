package com.example.currencyexhange.currency.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CurrencyRepositoryTest {

    private static final String ANY_FROM_CCY = "eur";
    private static final String ANY_TO_CCY = "huf";
    private static final String ANY_EXCHANGE_RATE = "123.4";

    private CurrencyRepository underTest;

    @Before
    public void setUp() {
        underTest = new CurrencyRepository();
    }

    @Test
    public void shouldGetRateFromUpstreamService() {
        String actualResponse = underTest.getRate(ANY_FROM_CCY, ANY_TO_CCY);
        
        assertEquals(ANY_EXCHANGE_RATE, actualResponse);
    }

    @Test(expected = DataAccessException.class)
    public void shouldThrowExceptionWhenCouldNotFetchData() {
        underTest.getRate(ANY_FROM_CCY, ANY_TO_CCY);
    }

}
