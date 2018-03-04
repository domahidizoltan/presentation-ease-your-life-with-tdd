package com.example.currencyexhange.currency.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

@RunWith(SpringRunner.class)
public class CurrencyRepositoryTest {

    private static final String ANY_FROM_CCY = "eur";
    private static final String ANY_TO_CCY = "huf";
    private static final double ANY_EXCHANGE_RATE = 123.4;
    private static final String ANY_EXCHANGE_URL = "http://upstreamservice/";

    private CurrencyRepository underTest;

    @Mock
    private RestTemplate restTemplateMock;

    @Before
    public void setUp() {
        underTest = new CurrencyRepository(restTemplateMock, ANY_EXCHANGE_URL);
    }

    @Test
    public void shouldGetRateFromUpstreamService() {
        given(restTemplateMock.getForObject(ANY_EXCHANGE_URL + ANY_FROM_CCY + "/" + ANY_TO_CCY, String.class))
            .willReturn(String.valueOf(ANY_EXCHANGE_RATE));

        double actualResponse = underTest.getRate(ANY_FROM_CCY, ANY_TO_CCY);
        
        assertEquals(ANY_EXCHANGE_RATE, actualResponse, 0);
    }

    @Test(expected = DataAccessException.class)
    public void shouldThrowExceptionWhenCouldNotFetchData() {
        given(restTemplateMock.getForObject(anyString(), eq(String.class)))
            .willThrow(new RestClientException("Operation failed"));

        underTest.getRate(ANY_FROM_CCY, ANY_TO_CCY);
    }

}
