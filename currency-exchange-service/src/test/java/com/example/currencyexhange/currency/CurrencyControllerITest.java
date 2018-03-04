package com.example.currencyexhange.currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
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

    private static final RequestBuilder EXCHANGE_REQUEST = get("/{fromCcy}-to-{toCcy}?qty={qty}", ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);
    private static final String OPERATION_FAILED = "Operation failed";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplateMock;

    @Test
    public void shouldReturnExchangePriceWithCurrencyName() throws Exception {
        given(restTemplateMock.getForObject(anyString(), eq(String.class)))
            .willReturn("255.31");

        mockMvc.perform(EXCHANGE_REQUEST)
            .andExpect(status().isOk())
            .andExpect(content().string("2555.66 " + ANY_TO_CCY_NAME));
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
