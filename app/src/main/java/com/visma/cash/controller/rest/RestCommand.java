package com.visma.cash.controller.rest;

import android.util.Log;

import com.visma.cash.restclient.RestClient;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.io.EOFException;

abstract class RestCommand implements Runnable {

    static final String ENDPOINT = "http://192.168.226.125:8080";
    static final RestClient restClient = new RestClient(ENDPOINT);

    @Override
    public final void run() {
        while(true) {
            try {
                executeRestCommand();
                return;
            } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException e) {
                Log.e(this.getClass().getName(), e.toString());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    abstract void executeRestCommand();
}
