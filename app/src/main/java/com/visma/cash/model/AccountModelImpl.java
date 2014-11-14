package com.visma.cash.model;

import android.content.Context;


import com.visma.cash.restmodel.Account;

import java.util.List;
import java.util.Vector;

final class AccountModelImpl
        implements AccountModel {

    private final List<Observer<AccountModel>> observers = new Vector<>();
    private Account account;

    AccountModelImpl() {}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
        notifyObservers();
    }

    public void addObserver(Observer<AccountModel> observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for(Observer<AccountModel> obs : observers) {
            obs.update(this);
        }
    }
}
