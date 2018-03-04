package com.example.currencyexhange.currency;

import com.example.currencyexhange.currency.calculator.ExchangePriceCalculator;
import com.example.currencyexhange.currency.calculator.ExchangePriceCalculatorFactory;
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
public class CurrencyServiceTest {


    private CurrencyService underTest;

    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @Mock
    private ExchangePriceCalculatorFactory exchangePriceCalculatorFactoryMock;

    @Mock
    private ExchangePriceCalculator exchangePriceCalculatorMock;

    @Before
    public void setUp() {
        underTest = new CurrencyService(currencyRepositoryMock, exchangePriceCalculatorFactoryMock);
    }

    @Test
    public void shouldGetRateFromUpstreamService() {
        given(exchangePriceCalculatorFactoryMock.getCalculator(ANY_FROM_CCY)).willReturn(exchangePriceCalculatorMock);
        given(exchangePriceCalculatorMock.calculate(ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY)).willReturn(new BigDecimal(100.1));

        BigDecimal actualExchangePrice = underTest.exchangeCurrencies(ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);

        assertEquals(100.1, actualExchangePrice.doubleValue(), 0);
    }

    @Test
    public void shouldGetCurrencyName() {
        given(currencyRepositoryMock.getCurrencyName(ANY_TO_CCY)).willReturn(ANY_TO_CCY_NAME);

        String actualCurrencyName = underTest.getNameOfCurrency(ANY_TO_CCY);

        assertEquals(ANY_TO_CCY_NAME, actualCurrencyName);
    }

}
