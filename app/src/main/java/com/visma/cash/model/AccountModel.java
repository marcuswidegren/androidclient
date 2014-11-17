package com.visma.cash.model;

import com.visma.cash.restmodel.Account;

public interface AccountModel extends ObservableModel<AccountModel> {

    public Account getAccount();

    public void setAccount(Account account);

    public void invalidate();

    public boolean isWebServerAvailable();

    public void setWebServerAvailable(boolean webServerAvailable);

}
