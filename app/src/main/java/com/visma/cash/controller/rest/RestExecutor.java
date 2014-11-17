package com.visma.cash.controller.rest;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;
import com.visma.cash.restmodel.Transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

final class RestExecutor {

    private final ExecutorService restCommandQueue = Executors.newSingleThreadExecutor();
    private final ExecutorService localCommandQueue = Executors.newSingleThreadExecutor();
    private final ScheduledExecutorService availabilityChecker = Executors.newSingleThreadScheduledExecutor();

    private final AccountModel model;

    RestExecutor(AccountModel model){
        this.model = model;
        availabilityChecker.scheduleWithFixedDelay(new AvailabilityCommand(model), 1, 1, TimeUnit.SECONDS);
    }

    void createAccount() {
        restCommandQueue.execute(new CreateAccountCommand(model));
    }

    void findOrCreateAccount(long id) {
        restCommandQueue.execute(new FindOrCreateAccountCommand(model, id));
    }

    void addTransaction(Transaction transaction) {
        restCommandQueue.execute(AddTransaction.restCommand(transaction, model));
        localCommandQueue.execute(AddTransaction.localCommand(transaction, model));
        refreshAccount();
    }

    void refreshAccount() {
        restCommandQueue.execute(new FindAccountCommand(model));
    }

    void deleteTransaction(Transaction transaction) {
        restCommandQueue.execute(DeleteTransactionCommand.restCommand(transaction, model));
        localCommandQueue.execute(DeleteTransactionCommand.localCommand(transaction, model));
        refreshAccount();
    }
}
