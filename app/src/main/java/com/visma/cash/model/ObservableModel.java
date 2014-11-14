package com.visma.cash.model;

public interface ObservableModel<ObservedType extends ObservableModel> {

    public void addObserver(Observer<ObservedType> observer);

    public void notifyObservers();
}
