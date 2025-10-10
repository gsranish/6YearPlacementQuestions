package com.anish.design_pattern;

public class Address implements Cloneable {
    private String city;
    private String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    // ✅ Override clone() to support deep copy
    @Override
    public Address clone() {
        return new Address(this.city, this.state);
    }

    @Override
    public String toString() {
        return city + ", " + state;
    }
}