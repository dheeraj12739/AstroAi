package com.astro.ai.astroai.controller;

import com.astro.ai.astroai.model.practice.Goat;
import com.astro.ai.astroai.model.practice.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class LibraryEventControllerTest {

    @Test
    public void given_object_when_getClassName_Correct() {

        Object goat = new Goat();
        Class<?> clazz = goat.getClass();
        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.astro.ai.astroai.model.practice.Goat", clazz.getName());
        assertEquals("com.astro.ai.astroai.model.practice.Goat", clazz.getCanonicalName());
    }

    @Test
    public void givenClassName_object_createdCorrectOrNot() throws ClassNotFoundException {

        Class<?> clazz = Class.forName("com.astro.ai.astroai.model.practice.Goat");
        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.astro.ai.astroai.model.practice.Goat", clazz.getName());
        assertEquals("com.astro.ai.astroai.model.practice.Goat", clazz.getCanonicalName());

    }

    @Test
    public void givenClass_whenRecognisedModifier_ThenCorrect() throws ClassNotFoundException {

        Class<?> goatClazz = Class.forName("com.astro.ai.astroai.model.practice.Goat");
        Class<?> animalClazz = Class.forName("com.astro.ai.astroai.model.practice.Animal");

        int goatModifier = goatClazz.getModifiers();
        int animalMod = animalClazz.getModifiers();

        assertTrue(Modifier.isPublic(goatModifier));
        assertTrue(Modifier.isAbstract(animalMod));
    }

    @Test
    public void givenClass_whenPackageInfo_correctOrNot() {

        Object goat = new Goat();
        Class<?> clazz = goat.getClass();
        Package pkg = clazz.getPackage();
        assertEquals("com.astro.ai.astroai.model.practice", pkg.getName());
    }

    @Test
    public void givenClass_getSuperClassCorrect_orNot() {

        Goat goat = new Goat();
        Class<?> clazz = goat.getClass();
        Class<?> superClazz = clazz.getSuperclass();

        assertEquals("Animal", superClazz.getSimpleName());
    }

    @Test
    public void givenClass_whenGetInterface_correctOrNot() throws ClassNotFoundException {

        Class<?> goatClass = Class.forName("com.astro.ai.astroai.model.practice.Goat");
        Class<?> animalClass = Class.forName("com.astro.ai.astroai.model.practice.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();
        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());


    }
}