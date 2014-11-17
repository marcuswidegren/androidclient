package com.visma.cash.model;

import com.visma.cash.restmodel.Account;
import com.visma.cash.restmodel.Transaction;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

final class AccountModelImpl
        implements AccountModel {

    private final List<Observer<AccountModel>> observers = new Vector<>();
    private Account account = new Account(-1l, new HashSet<Transaction>(), Money.zero(CurrencyUnit.EUR));;
    private boolean webServerAvailable = false;

    AccountModelImpl() {
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public void setAccount(Account account) {
        this.account = account;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<AccountModel> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer<AccountModel> obs : observers) {
            obs.update(this);
        }
    }

    @Override
    public void invalidate() {
        notifyObservers();
    }

    @Override
    public boolean isWebServerAvailable() {
        return webServerAvailable;
    }

    @Override
    public void setWebServerAvailable(boolean webServerAvailable) {
        this.webServerAvailable = webServerAvailable;
        invalidate();
    }
}
