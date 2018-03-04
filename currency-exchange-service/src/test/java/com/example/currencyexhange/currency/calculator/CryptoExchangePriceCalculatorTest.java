package com.example.currencyexhange.currency.calculator;

import com.example.currencyexhange.currency.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CryptoExchangePriceCalculatorTest {

    private ExchangePriceCalculator underTest;

    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @Before
    public void setUp() {
        underTest = new CryptoExchangePriceCalculator(currencyRepositoryMock, 1.0015, ANY_FROM_CCY);
    }

    @Test
    public void shouldCalculateExchangePriceUsingUpstreamService() {
        given(currencyRepositoryMock.getRate(ANY_FROM_XCCY, ANY_FROM_CCY)).willReturn(100.0);
        given(currencyRepositoryMock.getRate(ANY_FROM_CCY, ANY_TO_CCY)).willReturn(10.0);

        BigDecimal actualExchangePrice = underTest.calculate(ANY_FROM_XCCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);

        assertEquals(10015, actualExchangePrice.doubleValue(), 0);
    }

}
