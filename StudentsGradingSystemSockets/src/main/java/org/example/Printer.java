package org.example;

import java.util.LinkedHashMap;

public class Printer {
    public static <T> void print(LinkedHashMap<String , T> hashMap, String... messages) {
        for (String message: messages) {
            System.out.println(message);
        }
        hashMap.forEach(
                (key, value) -> System.out.println(key + ": " + value)
        );
    }

    public static void printCommands() {
        System.out.println("1- Get all marks");
        System.out.println("2- Get all courses names");
        System.out.println("3- Get a course info");
        System.out.println("0- Logout");
    }
}
