package com.astro.ai.astroai.service;

import java.util.concurrent.Callable;

public class TrackingClass implements Callable<Boolean> {

    private int x;

    public TrackingClass(int x) {
        this.x = x;
    }

    @Override
    public Boolean call() throws Exception {

        System.out.println("Inside the TrackingClass");
        return true;
    }
}
