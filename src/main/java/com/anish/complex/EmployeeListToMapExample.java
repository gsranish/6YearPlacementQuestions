package com.anish.complex;

import com.anish.design_pattern.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeListToMapExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(101, "Alice",null),
                new Employee(102, "Bob",null),
                new Employee(103, "Charlie",null)
        );

        // ✅ Convert List<Employee> → Map<Integer, Employee>
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, e -> e));

        // Print the map
        employeeMap.forEach((id, emp) ->
                System.out.println("ID: " + id + ", Name: " + emp.getName()));
    }
}
