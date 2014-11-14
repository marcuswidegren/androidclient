package com.visma.cash.model;

public final class Model {

    public static AccountModel newModel() {
        return new AccountModelImpl();
    }

    private Model() {}
}
