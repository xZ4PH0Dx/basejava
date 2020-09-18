package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume("John");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        Method method = null;
        try {
            method = Class.forName("ru.javawebinar.basejava.model.Resume").getMethod("toString");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert method != null;
            System.out.println(method.invoke(r));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(r);
    }
}