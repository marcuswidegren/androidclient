package com.visma.cash.controller.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.visma.cash.model.AccountModel;
import com.visma.cash.restmodel.Transaction;

import org.springframework.http.HttpStatus;

public final class Controller {

    private final RestExecutor restExecutor;

    public Controller(AccountModel model, Context context) {
        restExecutor = new RestExecutor(model);
        createAccountIfNeeded(context);
    }

    private void createAccountIfNeeded(Context context) {
        SharedPreferences pref = context.getSharedPreferences("account_id", 0);
        long accountId = pref.getLong("account_id", -1);
        restExecutor.findOrCreateAccount(accountId);
    }

    public void addTransaction(Transaction transaction) {
        restExecutor.addTransaction(transaction);
    }

    public void refreshAccount() {
        restExecutor.refreshAccount();
    }

    public void deleteTransaction(Transaction transaction) {
        restExecutor.deleteTransaction(transaction);
    }

}
