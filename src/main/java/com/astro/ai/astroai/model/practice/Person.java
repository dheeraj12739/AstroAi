package com.astro.ai.astroai.model.practice;

import com.astro.ai.astroai.annotation.Init;
import com.astro.ai.astroai.annotation.JsonElement;
import com.astro.ai.astroai.annotation.JsonSerializable;

@JsonSerializable
public class Person {

    @JsonElement
    private String name;

    @JsonElement
    private String lastname;

    @JsonElement(key = "personAge")
    private int age;

    private String gender;

    @Init
    private void changeValues() {
        this.name = name + " " + name;
        this.lastname = lastname + " " + lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
