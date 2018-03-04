package com.example.currencyexhange.currency.calculator;

import com.example.currencyexhange.currency.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.ANY_EXCHANGE_QTY;
import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.ANY_FROM_CCY;
import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.ANY_TO_CCY;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class SimpleExchangePriceCalculatorTest {

    private ExchangePriceCalculator underTest;

    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @Before
    public void setUp() {
        underTest = new SimpleExchangePriceCalculator(currencyRepositoryMock);
    }

    @Test
    public void shouldCalculateExchangePriceUsingUpstreamService() {
        given(currencyRepositoryMock.getRate(ANY_FROM_CCY, ANY_TO_CCY)).willReturn(10.0);

        BigDecimal actualExchangePrice = underTest.calculate(ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);

        assertEquals(100.1, actualExchangePrice.doubleValue(), 0);
    }

}
