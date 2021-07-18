package com.fabrick.testsv4.webClient;

import com.fabrick.testsv4.exception.ClientDataException;
import com.fabrick.testsv4.exception.TransactionServiceException;
import com.fabrick.testsv4.model.*;
import com.fabrick.testsv4.service.TransactionService;
import com.fabrick.testsv4.utils.Const;
import com.fabrick.testsv4.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
public class GreetingWebClient {

    HttpClient httpClient = HttpClient.create()
            .tcpConfiguration(client ->
                    client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Const.CONNECT_TIMEOUT_MILLIS)
                            .doOnConnected(conn -> conn
                                    .addHandlerLast(new ReadTimeoutHandler(Const.READ_TIMEOUT))
                                    .addHandlerLast(new WriteTimeoutHandler(Const.WRITE_TIMEOUT))));

    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

    private WebClient client = WebClient.builder()
            .baseUrl(Const.BASE_URL)
            .clientConnector(connector)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public Response getTransactionList(String idAccount, String fromAccountingDate, String toAccountingDate) throws JsonProcessingException {
        try {
            return client.get()
                .uri(Const.TRANS_LIST_URI + "?accountId=" + idAccount + "&fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate )
                .header("Api-Key", Const.API_KEY)
                .header("Auth-Schema", Const.AUTH_SCHEMA)
                .retrieve()
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> Utils.handle5xxErrorResponse(clientResponse))
                    .bodyToMono(Response.class)
                .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            return Utils.setResult(ex);
        } catch (Exception ex) {
            log.error("Exception ", ex);
            throw ex;
        }
    }


    public Response getAccountBalance(String idAccount) throws JsonProcessingException {
        try {
        return client.get()
                .uri(Const.ACC_BAL_URI + "?accountId=" + idAccount)
                .header("Api-Key", Const.API_KEY)
                .header("Auth-Schema", Const.AUTH_SCHEMA)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Utils.handle5xxErrorResponse(clientResponse))
                .bodyToMono(Response.class)
                .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            return Utils.setResult(ex);

        } catch (Exception ex) {
            log.error("Exception ", ex);
            throw ex;
        }
    }

    public Response executeMoneyTransfer(MoneyTransfer moneyTransfer, String idAccount) throws JsonProcessingException {
        MoneyTransferRes monTrans = new MoneyTransferRes();
        try {
             monTrans = client.post()
                    .uri(Const.MON_TRANS_URI + idAccount + Const.MON_TRANS_URI_PART2)
                    .body(Mono.just(moneyTransfer), MoneyTransfer.class)
                    .header("Api-Key", Const.API_KEY)
                    .header("Auth-Schema", Const.AUTH_SCHEMA)
                    .accept(MediaType.TEXT_PLAIN)
                    .retrieve()
                    //.onStatus(HttpStatus::is4xxClientError, clientResponse -> handle4xxErrorResponse(clientResponse))
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> Utils.handle5xxErrorResponse(clientResponse))
                    .bodyToMono(MoneyTransferRes.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            return Utils.setResult(ex);
        } catch (Exception ex) {
            log.error("Exception ", ex);
            throw ex;
        }

        Response response = new Response();
        Payload payload = new Payload();
        response.setStatus("OK");
        payload.setMoneyTransferRes(monTrans);
        response.setPayload(payload);

        return response;

    }




}