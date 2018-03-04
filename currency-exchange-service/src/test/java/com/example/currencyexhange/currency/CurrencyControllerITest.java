package com.example.currencyexhange.currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerITest {

    private static final RequestBuilder EXCHANGE_REQUEST = get("?{fromCcy}-to-{toCcy}?qty={qty}", ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);
    public static final String OPERATION_FAILED = "Operation failed";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnExchangePriceWithCurrencyName() throws Exception {
        mockMvc.perform(EXCHANGE_REQUEST)
            .andExpect(status().isOk())
            .andExpect(content().string("255.57 Hungarian Forints"));
    }

    @Test
    public void shouldReturnNotFoundWhenCouldNotFetchRateFromUpstreamService() throws Exception {
        mockMvc.perform(EXCHANGE_REQUEST)
            .andExpect(status().isNotFound())
            .andExpect(content().string(OPERATION_FAILED));
    }
}
