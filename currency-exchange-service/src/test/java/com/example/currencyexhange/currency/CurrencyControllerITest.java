package com.example.currencyexhange.currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerITest {

    public static final String REQUEST_URL = "/{fromCcy}-to-{toCcy}?qty={qty}";
    private static final RequestBuilder EXCHANGE_REQUEST = get(REQUEST_URL, ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);
    private static final String OPERATION_FAILED = "Operation failed";

    @Value("${config.exchange.url}")
    private String exchangeUrl;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplateMock;

    @Before
    public void setUp() {
        given(restTemplateMock.getForObject(eq(exchangeUrl + ANY_FROM_CCY + "/" + ANY_TO_CCY), eq(String.class)))
            .willReturn("255.31");
        given(restTemplateMock.getForObject(eq(exchangeUrl + ANY_FROM_XCCY + "/" + ANY_FROM_CCY), eq(String.class)))
            .willReturn("15317.20");
    }

    @Test
    public void shouldReturnExchangePriceWithCurrencyName() throws Exception {
        mockMvc.perform(EXCHANGE_REQUEST)
            .andExpect(status().isOk())
            .andExpect(content().string("2555.66 " + ANY_TO_CCY_NAME));
    }

    @Test
    public void shouldReturnCryptoExchangePriceWithCurrencyName() throws Exception {
        MockHttpServletRequestBuilder cryptoExchangeRequest = get(REQUEST_URL, ANY_FROM_XCCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);
        mockMvc.perform(cryptoExchangeRequest)
            .andExpect(status().isOk())
            .andExpect(content().string("39165002.84 " + ANY_TO_CCY_NAME));
    }

    @Test
    public void shouldReturnNotFoundWhenCouldNotFetchRateFromUpstreamService() throws Exception {
        given(restTemplateMock.getForObject(anyString(), eq(String.class)))
            .willThrow(new RestClientException(OPERATION_FAILED));

        mockMvc.perform(EXCHANGE_REQUEST)
            .andExpect(status().isNotFound())
            .andExpect(content().string(OPERATION_FAILED));
    }
}
