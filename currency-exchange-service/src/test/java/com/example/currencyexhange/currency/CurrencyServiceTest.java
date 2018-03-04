package com.example.currencyexhange.currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.ANY_FROM_CCY;
import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.ANY_TO_CCY;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

    private static final int ANY_EXCHANGE_QTY = 10;
    private static final double BENEFIT = 1.001;

    private CurrencyService underTest;

    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @Before
    public void setUp() {
        underTest = new CurrencyService(currencyRepositoryMock, BENEFIT);
    }

    @Test
    public void shouldGetRateFromUpstreamService() {
        given(currencyRepositoryMock.getRate(ANY_FROM_CCY, ANY_TO_CCY)).willReturn(10.0);

        BigDecimal actualExchangePrice = underTest.exchangeCurrencies(ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);

        assertEquals(100.1, actualExchangePrice.doubleValue(), 0);
    }


}
