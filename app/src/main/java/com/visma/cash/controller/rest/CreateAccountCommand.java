package com.visma.cash.controller.rest;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;

final class CreateAccountCommand extends RestCommand {

    private final AccountModel model;

    CreateAccountCommand(AccountModel model) {
        this.model = model;
    }

    @Override
    void executeRestCommand() {
        model.setAccount(restClient.createAccount());
    }

}
