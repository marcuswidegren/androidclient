package com.visma.cash.controller.rest;

import android.util.Log;

import com.visma.cash.model.AccountModel;
import com.google.common.base.Optional;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Account;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

final class FindOrCreateAccountCommand extends RestCommand {

    private final long id;
    private final AccountModel model;

    FindOrCreateAccountCommand(AccountModel model, long id) {
        this.model = model;
        this.id = id;
    }

    @Override
    void executeRestCommand() {
        Optional<Account> account = restClient.findExistingAccount(id);
        if (account.isPresent()) {
            model.setAccount(account.get());
        } else {
            Log.e(this.getClass().getName(), "Could not find account, creating a new one");
            model.setAccount(restClient.createAccount());
        }
    }

}
