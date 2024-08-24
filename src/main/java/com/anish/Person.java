package com.anish;

import jdk.jfr.DataAmount;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Person {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // Constructor, getters, and setters

    public static List<Person> sortPersonsByAgeDescending(List<Person> persons) {
        return persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge).reversed()).toList();
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(10);
        p.setName("Aish");
        System.out.println(sortPersonsByAgeDescending(Arrays.asList(p)));
    }
}
