package com.visma.cash.controller.rest;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Account;
import com.visma.cash.restmodel.Transaction;

final class AddTransactionCommand implements Runnable {

    private final RestClient restClient;
    private final Transaction transaction;
    private final AccountModel model;

    AddTransactionCommand(RestClient restClient, AccountModel model, Transaction transaction) {
        this.restClient = restClient;
        this.model = model;
        this.transaction = transaction;
    }

    @Override
    public void run() {
        restClient.addTransaction(model.getAccount(), transaction);
        Account account = restClient.findExistingAccount(model.getAccount());
        model.setAccount(account);
    }
}
