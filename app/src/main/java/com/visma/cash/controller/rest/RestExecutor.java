package com.visma.cash.controller.rest;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Transaction;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class RestExecutor {

    public static final String ENDPOINT = "http://192.168.226.125:8080";

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final AccountModel model;
    private final RestClient restClient = new RestClient(ENDPOINT);

    RestExecutor(AccountModel model){
        this.model = model;
    }

    void createAccount() {
        executorService.execute(new CreateAccountCommand(restClient, model));
    }

    void findOrCreateAccount(long id) {
        executorService.execute(new FindOrCreateAccountCommand(restClient, model, id));
    }

    void addTransaction(Transaction transaction) {
        executorService.execute(new AddTransactionCommand(restClient, model, transaction));
    }

    void refreshAccount() {
        executorService.execute(new FindAccountCommand(restClient, model));
    }

    void deleteTransaction(Transaction transaction) {
        executorService.execute(new DeleteTransactionCommand(restClient, model, transaction));
    }
}
