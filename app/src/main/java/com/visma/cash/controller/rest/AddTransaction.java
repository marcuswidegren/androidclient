package com.visma.cash.controller.rest;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Account;
import com.visma.cash.restmodel.Transaction;

final class AddTransaction {

    private AddTransaction(){}

    static Runnable restCommand(final Transaction transaction, final AccountModel model) {
        return new RestCommand() {
            @Override
            void executeRestCommand() {
                restClient.addTransaction(model.getAccount(), transaction);
            }
        };
    }

    static Runnable localCommand(final Transaction transaction, final AccountModel model) {
        return new Runnable() {
            @Override
            public void run() {
                model.getAccount().addTransaction(transaction);
                model.invalidate();
            }
        };
    }

}
