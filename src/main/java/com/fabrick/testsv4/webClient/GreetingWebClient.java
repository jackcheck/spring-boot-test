package com.fabrick.testsv4.webClient;

import com.fabrick.testsv4.model.MoneyTransfer;
import com.fabrick.testsv4.model.Transactions;
import com.fabrick.testsv4.utils.Const;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;


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

    public Mono<Transactions> getTransactionList() {
        return client.get()
                .uri(Const.TRANS_LIST_URI)
                .header("Api-Key", Const.API_KEY)
                .header("Auth-Schema", Const.AUTH_SCHEMA)
                .accept(MediaType.TEXT_PLAIN)
                .retrieve().bodyToMono(Transactions.class);
    }

/*
    public Mono<Balance> getAccountBalance(String idAccount) {
        return client.get()
                .uri(Const.ACC_BAL_URI + idAccount)
                .header("Api-Key", Const.API_KEY)
                .header("Auth-Schema", Const.AUTH_SCHEMA)
                .retrieve()
                .bodyToMono(Balance.class);
    }
*/

    public String getAccountBalance(String idAccount) {
        Mono<ClientResponse> accBal = client.get()
                .uri(Const.ACC_BAL_URI + idAccount)
                .header("Api-Key", Const.API_KEY)
                .header("Auth-Schema", Const.AUTH_SCHEMA)
                .accept(MediaType.TEXT_PLAIN)
                .exchange();

        return ">> result = " + accBal.flatMap(res -> res.bodyToMono(String.class)).block();
    }

    public String executeMoneyTransfer(MoneyTransfer moneyTransfer, String idAccount) {
        Mono<ClientResponse> accBal = client.post()
                .uri(Const.MON_TRANS_URI + idAccount + Const.MON_TRANS_URI_PART2)
                .body(Mono.just(moneyTransfer), MoneyTransfer.class)
                .header("Api-Key", Const.API_KEY)
                .header("Auth-Schema", Const.AUTH_SCHEMA)
                .accept(MediaType.TEXT_PLAIN)
                .exchange();

        return ">> result = " + accBal.flatMap(res -> res.bodyToMono(String.class)).block();
    }


}