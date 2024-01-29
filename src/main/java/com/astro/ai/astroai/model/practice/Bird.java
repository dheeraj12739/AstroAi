package com.astro.ai.astroai.model.practice;

public class Bird extends Animal{

    private boolean walks;

    public Bird() {
        super("bird");
    }

    public Bird(String name, boolean walks) {
        super(name);
        this.walks = walks;
    }

    public Bird(String name) {
        super(name);
    }

    public boolean isWalks() {
        return walks;
    }

    @Override
    protected String getSound() {
        return null;
    }

    public void setWalks(boolean walks) {
        this.walks = walks;
    }

    @Override
    public String eats() {
        return null;
    }
}
