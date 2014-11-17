package com.visma.cash.controller.rest;

import android.util.Log;

import com.visma.cash.model.AccountModel;
import com.visma.cash.restclient.RestClient;

final class AvailabilityCommand implements Runnable {

    static final String ENDPOINT = "http://192.168.226.125:8080";
    static final RestClient restClient = new RestClient(ENDPOINT);
    private final AccountModel model;

    AvailabilityCommand(AccountModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        Log.i(this.getClass().getName(), "Checking availability");
        boolean serverAvailable = restClient.isServerAvailable();
        Log.i(this.getClass().getName(), "Server available: " + serverAvailable);
        if(serverAvailable) {
            model.setWebServerAvailable(true);
        } else if( !serverAvailable) {
            model.setWebServerAvailable(false);
        }
    }
}
