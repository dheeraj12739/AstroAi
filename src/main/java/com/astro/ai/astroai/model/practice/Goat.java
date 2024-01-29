package com.astro.ai.astroai.model.practice;

public class Goat extends Animal implements Locomotion{

    public Goat() {
        super();
    }

    @Override
    protected String getSound() {
        return "beep";
    }

    @Override
    public String eats() {
        return "grass";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }
}
