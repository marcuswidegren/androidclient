package com.visma.cash.controller.rest;

import android.util.Log;

import com.google.common.base.Optional;
import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Account;

public final class FindAccountCommand implements Runnable {

    private final RestClient restClient;
    private final AccountModel model;

    FindAccountCommand(RestClient restClient, AccountModel model) {
        this.restClient = restClient;
        this.model = model;
    }

    @Override
    public void run() {
        Account account = restClient.findExistingAccount(model.getAccount());
        model.setAccount(account);
    }

}
