package com.visma.cash.model;

public interface Observer<ObservedType extends ObservableModel> {

    void update(ObservedType observable);

}
