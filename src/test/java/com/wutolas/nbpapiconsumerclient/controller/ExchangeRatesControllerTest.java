package com.wutolas.nbpapiconsumerclient.controller;

import com.wutolas.nbpapiconsumerclient.response.ExchangeRatesResponse;
import com.wutolas.nbpapiconsumerclient.service.ExchangeRatesService;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class ExchangeRatesControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ExchangeRatesService exchangeRatesService;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private ExchangeRatesController exchangeRatesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(exchangeRatesController).build();
    }

    @Test
    void usdRatesTillTodayInitFormShouldReturnExchangeRatesPageView() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd/new-search"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exchangeRatesRequest"))
                .andExpect(view().name("exchangeRatesPage"));
    }

    @Test
    void fetchUsdRatesTillTodayShouldReturnExchangeRatesPageView() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd"))
                .andExpect(status().isOk())
                .andExpect(view().name("exchangeRatesPage"));
    }

    @Test
    void fetchUsdRatesTillTodayShouldReturnOneErrorInModelWhenNoParam() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd"))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1));
    }

    @Test
    void fetchUsdRatesTillTodayShouldReturnOneErrorInModelWhenWrongDatePattern() throws Exception {
        //given
        String date = "02-02-1920";

        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd")
                .param("dateFrom", date))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrorCode("exchangeRatesRequest", "dateFrom", "typeMismatch"))
                .andExpect(model().errorCount(1));
    }

    @Test
    void fetchUsdRatesTillTodayShouldReturnOneErrorInModelWhenDaysLimitExceeded() throws Exception {
        //given
        String date = LocalDate.now().minusDays(368).toString();

        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd")
                .param("dateFrom", date))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrorCode("exchangeRatesRequest", "dateFrom", "DateDaysLimitConstraint"))
                .andExpect(model().errorCount(1));
    }

    @Test
    void fetchUsdRatesTillTodayShouldReturnZeroErrorsInModelWhenDateIsCorrect() throws Exception {
        //given
        String date = LocalDate.now().minusDays(1).toString();

        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd")
                .param("dateFrom", date))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().errorCount(0));
    }

    @Test
    void fetchUsdRatesTillTodayShouldReturnOneErrorInModelWhenDateIsAfterToday() throws Exception {
        //given
        String date = LocalDate.now().plusDays(1).toString();

        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd")
                .param("dateFrom", date))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrorCode("exchangeRatesRequest", "dateFrom", "DateMaxConstraint"))
                .andExpect(model().errorCount(1));
    }

    @Test
    void fetchUsdRatesTillTodayShouldAddModelAttributeWhenDateIsCorrect() throws Exception {
        //given
        String date = LocalDate.now().minusDays(1).toString();

        given(exchangeRatesService.getExchangeRatesWithDailyDifferences(anyString(), anyString(), anyString()))
                .willReturn(new ExchangeRatesResponse());

        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd")
                .param("dateFrom", date))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exchangeRatesResponse"));
    }

    @Test
    void fetchUsdRatesTillTodayShouldExecuteExceptionHandlerWhenNotFoundAndReturnExchangeRatesPage() throws Exception {
        //given
        String date = LocalDate.now().minusDays(1).toString();

        given(exchangeRatesService.getExchangeRatesWithDailyDifferences(anyString(), anyString(), anyString()))
                .willThrow(FeignException.NotFound.class);

        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd")
                .param("dateFrom", date))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("exchangeRatesPage"));
    }

    @Test
    void noDataAvailableShouldAddNoDataAvailableAttribute() throws Exception {
        //given
        String date = LocalDate.now().minusDays(1).toString();
        String message = "Data is not available";

        given(exchangeRatesService.getExchangeRatesWithDailyDifferences(anyString(), anyString(), anyString()))
                .willThrow(FeignException.NotFound.class);
        given(messageSource.getMessage(anyString(), any(), any())).willReturn(message);

        //when
        //then
        mockMvc.perform(get("/exchange-rates/usd")
                .param("dateFrom", date))
                .andDo(print())
                .andExpect(model().attribute("noDataAvailable", message));
    }

}