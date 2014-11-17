package com.visma.cash.controller.rest;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Account;

public final class FindAccountCommand extends RestCommand {

    private final AccountModel model;

    FindAccountCommand(AccountModel model) {
        this.model = model;
    }

    @Override
    void executeRestCommand() {
        Account account = restClient.findExistingAccount(model.getAccount());
        model.setAccount(account);
    }

}
