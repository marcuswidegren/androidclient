package com.visma.cash.controller.rest;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;

final class CreateAccountCommand implements Runnable {

    private final RestClient restClient;
    private final AccountModel model;

    CreateAccountCommand(RestClient restClient, AccountModel model) {
        this.restClient = restClient;
        this.model = model;
    }

    @Override
    public void run() {
        model.setAccount(restClient.createAccount());
    }

}
