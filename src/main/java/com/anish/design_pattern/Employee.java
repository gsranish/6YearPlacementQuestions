package com.anish.design_pattern;

public final class Employee {

    private final int id;
    private final String name;
    private final Address address;

    public Employee(int id, String name, Address address) {
        this.id = id;
        this.name = name;

        // ✅ Defensive deep copy during construction
        this.address = address.clone();
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public Address getAddress() {
        // ✅ Return a clone instead of the actual reference
        return address.clone();
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
}