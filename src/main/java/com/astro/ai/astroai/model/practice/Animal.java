package com.astro.ai.astroai.model.practice;

public abstract class Animal implements Eating{

    public static String Category = "domestic";

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
    }

    protected abstract String getSound();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
