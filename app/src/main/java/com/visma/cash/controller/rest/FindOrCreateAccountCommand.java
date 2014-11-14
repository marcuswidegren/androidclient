package com.visma.cash.controller.rest;

import android.util.Log;

import com.visma.cash.model.AccountModel;
import com.google.common.base.Optional;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Account;

final class FindOrCreateAccountCommand implements Runnable {

    private final RestClient restClient;
    private final long id;
    private final AccountModel model;

    FindOrCreateAccountCommand(RestClient restClient, AccountModel model, long id) {
        this.restClient = restClient;
        this.model = model;
        this.id = id;
    }

    @Override
    public void run() {
        Optional<Account> account = restClient.findExistingAccount(id);
        if(account.isPresent()) {
            model.setAccount(account.get());
        } else {
            Log.e(this.getClass().getName(), "Could not find account, creating a new one");
            model.setAccount(restClient.createAccount());
        }
    }

}
