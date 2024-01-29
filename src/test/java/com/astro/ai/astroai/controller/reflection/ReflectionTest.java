package com.astro.ai.astroai.controller.reflection;

import com.astro.ai.astroai.model.practice.Bird;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReflectionTest {

    @Test
    public void givenClass_whenGet_constructorThen_correct() throws ClassNotFoundException, NoSuchMethodException {

        Class<?> clazz = Class.forName("com.astro.ai.astroai.model.practice.Goat");

        Constructor<?> [] constructors = clazz.getConstructors();
        assertEquals(1, constructors.length);
        assertEquals(constructors[0].getName(), "com.astro.ai.astroai.model.practice.Goat");

    }

    @Test
    public void givenClass_whenGetMethod_thenCorrect() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.astro.ai.astroai.model.practice.Animal");
        Method[] methods = clazz.getDeclaredMethods();
        List<String> actualMethods = getActualMethodName(methods);
        assertEquals(4, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("getSound")));
    }

    private static List<String> getActualMethodName(Method[] methods) {

        List<String> actualMethods = new ArrayList<>();
        for (Method method : methods) {

            actualMethods.add(method.getName());
        }

        return actualMethods;
    }

    @Test
    public void givenClass_whenAllConstructor_ThenCorrect() throws ClassNotFoundException {

        Class<?> birdClass = Class.forName("com.astro.ai.astroai.model.practice.Bird");
        Constructor<?>[] constructors =  birdClass.getConstructors();

        assertEquals(constructors.length, 3);
    }

    @Test
    public void givenClass_whenIntializeObject_atRunTIme_thenCorrect() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<?> birdClass = Class.forName("com.astro.ai.astroai.model.practice.Bird");
        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance("Crow");
        Bird bird3 = (Bird) cons3.newInstance("Hen", true);

        assertEquals("bird", bird1.getName());
        assertEquals(bird2.getName(), "Crow");
        assertEquals(bird3.getName(), "Hen");
        assertTrue(bird3.isWalks());

    }

    @Test
    public void givenClassField_whenSetsAndGetsValues_thenCorrect() throws Exception {

        Class<?> birdClass = Class.forName("com.astro.ai.astroai.model.practice.Bird");
        Bird bird = (Bird)birdClass.getConstructor().newInstance();

        Field field = birdClass.getDeclaredField("walks");
        field.setAccessible(true);
        assertFalse(field.getBoolean(bird));
        assertFalse(bird.isWalks());

        field.set(bird, true);

        assertTrue(field.getBoolean(bird));
        assertTrue(bird.isWalks());


    }

    @Test
    public void givenClass_whenGetAllMethod_thenCorrectOrNot() throws Exception{

        Class<?> birdClass = Class.forName("com.astro.ai.astroai.model.practice.Bird");
        Method []methods = birdClass.getMethods();

        List<String> methodNames = getActualMethodName(methods);

        assertTrue(methodNames.contains(Arrays.asList("equals", "notifyAll", "hashCode", "walks", "eats", "toString")));
    }

    @Test
    public void givenClass_whenGetDeclaredMtehodThen_correct() throws Exception{

        Class<?> birdClass = Class.forName("com.astro.ai.astroai.model.practice.Bird");
        Method []methods = birdClass.getDeclaredMethods();
        List<String> methodNames = getActualMethodName(methods);
        assertTrue(methodNames.containsAll(Arrays.asList("setWalks", "isWalks", "getSound", "eats")));
    }

    @Test
    public void givenClass_whenInvokes_thenCorrect() throws Exception{

        Class<?> birdClass = Class.forName("com.astro.ai.astroai.model.practice.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
        Method isWalksMethod = birdClass.getDeclaredMethod("isWalks");
        boolean walks = (boolean) isWalksMethod.invoke(bird);
        assertFalse(walks);
        assertFalse(bird.isWalks());

        setWalksMethod.invoke(bird,true);
        boolean walks2 = (boolean) isWalksMethod.invoke(bird);
        assertTrue(walks2);
        assertTrue(bird.isWalks());

    }
}
