package com.python;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Python {
    public interface Showable {
        public String getValue();
    }

    public static class Student implements Showable {

        private String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String getValue() {
            return name;
        }

    }

    public static class Phone implements Showable {

        private String trademark;
        private String id;

        public Phone(String id, String trademark) {
            this.trademark = trademark;
        }

        @Override
        public String getValue() {
            return trademark;
        }

        public String getId() {
            return id;
        }

    }

    public static void main(String[] args) {
        List<Showable> items = new ArrayList<>();
        items.add(new Student("jun4rui"));
        items.add(new Student("ffcactus"));
        items.add(new Phone("001", "Apple"));
        items.add(new Phone("002", "Andriod"));
        items.add(new Phone("003", "Apple"));

        System.out.println("Search student:");
        items.forEach(item -> {
            if (item instanceof Student) {
                System.out.println("Item: " + item.getValue());
            }
        });

        System.out.println("Count Apple Phone:");
        Predicate<Showable> isPhone = item -> item instanceof Phone;
        Predicate<Showable> isApple = item -> item.getValue().equals("Apple");
        System.out.println(items.stream().filter(isPhone).filter(isApple).count());

    }
}
