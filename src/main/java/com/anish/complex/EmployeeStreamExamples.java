package com.anish.complex;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

public class EmployeeStreamExamples {

    // simple model classes
    static class Address {
        String city;
        String state;
        String type; // "home", "office"
        public String getCity() { return city; }
        public String getState() { return state; }
        public String getType() { return type; }

        public Address(String city, String state, String type) {
            this.city = city; this.state = state; this.type = type;
        }
        @Override
        public String toString() { return String.format("%s (%s) - %s", city, state, type); }
    }

    static class Employee {
        int id;
        String name;
        int age;
        double salary;
        List<Address> addresses; // has-a relationship
        public int getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public double getSalary() { return salary; }
        public List<Address> getAddresses() { return addresses; }

        public Employee(int id, String name, int age, double salary, List<Address> addresses) {
            this.id = id; this.name = name; this.age = age; this.salary = salary; this.addresses = addresses;
        }
        @Override
        public String toString() {
            return String.format("Employee{id=%d,name=%s,age=%d,salary=%.2f,addresses=%s}", id, name, age, salary, addresses);
        }
    }

    // sample data
    static List<Employee> sampleEmployees() {
        return Arrays.asList(
                new Employee(1, "Alice", 34, 120000, Arrays.asList(
                        new Address("Mumbai", "MH", "home"),
                        new Address("Pune", "MH", "office"))),
                new Employee(2, "Bob", 28, 90000, Arrays.asList(
                        new Address("Bengaluru", "KA", "home"))),
                new Employee(3, "Charlie", 40, 150000, Arrays.asList(
                        new Address("Mumbai", "MH", "home"),
                        new Address("Delhi", "DL", "office"))),
                new Employee(4, "Diana", 30, 90000, Arrays.asList(
                        new Address("Hyderabad", "TG", "home"),
                        new Address("Bengaluru", "KA", "office"))),
                new Employee(5, "Eve", 40, 130000, Arrays.asList(
                        new Address("Chennai", "TN", "home"))),
                // include an employee with no address to test null/empty handling
                new Employee(6, "Frank", 25, 60000, Collections.emptyList())
        );
    }

    // 1) Flatten addresses: get distinct cities where employees live/work
    static void distinctCities() {
        System.out.println("Distinct cities:");
        List<String> cities = sampleEmployees().stream()
                .flatMap(e -> e.addresses == null ? Stream.empty() : e.addresses.stream())
                .map(a -> a.city)
                .distinct()
                .collect(toList());
        System.out.println(cities);
    }

    // 2) Employees grouped by age (Map<Integer, List<Employee>>)
    static void groupByAge() {
        System.out.println("Group by age:");
        Map<Integer, List<Employee>> byAge = sampleEmployees().stream()
                .collect(groupingBy(e -> e.age));
        byAge.forEach((age, list) -> System.out.println(age + " -> " + list));
    }

    // 3) Highest paid employee (Optional<Employee>)
    static void highestPaidEmployee() {
        System.out.println("Highest paid:");
        sampleEmployees().stream()
                .max(Comparator.comparingDouble(e -> e.salary))
                .ifPresent(System.out::println);
    }

    // 4) Sum of salaries using reduce and using collector (two ways)
    static void sumSalaries() {
        System.out.println("Sum of salaries:");
        double sum1 = sampleEmployees().stream()
                .mapToDouble(e -> e.salary).sum();
        double sum2 = sampleEmployees().stream()
                .map(e -> e.salary)
                .reduce(0.0, Double::sum);
        System.out.println(sum1 + " / " + sum2);
    }

    // 5) Partition employees by whether they have an office address
    static void partitionByHasOffice() {
        System.out.println("Partition by has office address:");
        Map<Boolean, List<Employee>> partition = sampleEmployees().stream()
                .collect(partitioningBy(e -> e.addresses != null &&
                        e.addresses.stream().anyMatch(a -> "office".equalsIgnoreCase(a.type))));
        partition.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    // 6) Map of state -> count of employees having any address in that state
    static void stateToEmployeeCount() {
        System.out.println("state -> employee count:");
        Map<String, Long> counts = sampleEmployees().stream()
                .flatMap(e -> e.addresses == null ? Stream.empty() : e.addresses.stream()
                        .map(a -> new AbstractMap.SimpleEntry<>(a.state, e.id))) // (state, empId)
                .distinct() // distinct state-emp pairs (prevents double counting if employee has multiple addresses in same state)
                .collect(groupingBy(Map.Entry::getKey, counting()));
        System.out.println(counts);
    }

    // 7) Get comma-joined employee names sorted by salary desc
    static void joinedNamesBySalaryDesc() {
        System.out.println("Joined names by salary desc:");
        String joined = sampleEmployees().stream()
                .sorted(Comparator.comparingDouble((Employee e) -> e.salary).reversed()
                        .thenComparing(e -> e.name))
                .map(e -> e.name)
                .collect(joining(", "));
        System.out.println(joined);
    }

    // 8) Find employees who live in a given city (case-insensitive) using Predicate and method ref
    static void employeesInCity(String city) {
        System.out.println("Employees in city: " + city);
        Predicate<Employee> inCity = e -> e.addresses != null &&
                e.addresses.stream().anyMatch(a -> a.city.equalsIgnoreCase(city));
        List<Employee> list = sampleEmployees().stream()
                .filter(inCity)
                .collect(toList());
        System.out.println(list);
    }

    // 9) Nested grouping: Map<state, Map<type, List<Employee>>>
    static void nestedGroupingStateType() {
        System.out.println("Nested grouping state -> type -> employees:");
        Map<String, Map<String, List<Employee>>> nested = sampleEmployees().stream()
                .flatMap(e -> e.addresses == null ? Stream.empty() :
                        e.addresses.stream().map(a -> new AbstractMap.SimpleEntry<>(a, e)))
                .collect(groupingBy(entry -> entry.getKey().state,
                        groupingBy(entry -> entry.getKey().type,
                                mapping(Map.Entry::getValue, toList()))));
        System.out.println(nested);
    }

    // 10) Top N (k highest salaries) employees using stream and limit
    static void topNSalaries(int k) {
        System.out.println(" Top " + k + " salaries:");
        List<Employee> top = sampleEmployees().stream()
                .sorted(Comparator.comparingDouble((Employee e) -> e.salary).reversed())
                .limit(k)
                .collect(toList());
        System.out.println(top);
    }

    // 11) Use Collectors.summarizingDouble to get salary statistics
    static void salaryStatistics() {
        System.out.println("Salary statistics:");
        DoubleSummaryStatistics stats = sampleEmployees().stream()
                .collect(summarizingDouble(e -> e.salary));
        System.out.println(stats);
    }

    // 12) Build Map<id, Employee> but handle duplicate ids safely (keep higher salary)
    static void toMapHandleDuplicates() {
        System.out.println("Map<id,employee> keep higher salary on duplicate id:");
        // simulate duplicate ids by creating a duplicated employee
        List<Employee> data = new ArrayList<>(sampleEmployees());
        data.add(new Employee(2, "Bob-Alt", 29, 120000, Arrays.asList(new Address("Delhi","DL","home"))));
        Map<Integer, Employee> map = data.stream()
                .collect(toMap(e -> e.id, Function.identity(),
                        (e1, e2) -> e1.salary >= e2.salary ? e1 : e2));
        System.out.println(map);
    }

    // 13) Parallel stream example: compute sum of salaries in parallel (careful: avoid side-effects)
    static void parallelSumSalaries() {
        System.out.println("Parallel sum salaries:");
        double sum = sampleEmployees().parallelStream()
                .mapToDouble(e -> e.salary).sum();
        System.out.println(sum);
    }

    // 14) Custom collector (example): collect all cities into a TreeSet (sorted unique)
    static void collectCitiesToTreeSet() {
        System.out.println("Collect cities to TreeSet (sorted unique):");
        TreeSet<String> cities = sampleEmployees().stream()
                .flatMap(e -> e.addresses == null ? Stream.empty() : e.addresses.stream())
                .map(a -> a.city)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(cities);
    }

    // 15) Defensive: safe stream processing with Optionals (employee with maybe-null addresses)
    static void safeProcessingWithOptional() {
        System.out.println("Safe processing with Optional:");
        // simulate an employee with null addresses
        List<Employee> data = new ArrayList<>(sampleEmployees());
        data.add(new Employee(7, "Gina", 29, 80000, null));
        List<String> namesWithAnyAddress = data.stream()
                .filter(e -> Optional.ofNullable(e.addresses)
                        .map(list -> !list.isEmpty()).orElse(false))
                .map(e -> e.name)
                .collect(toList());
        System.out.println(namesWithAnyAddress);
    }
    // 16) Min & Max salary employee using normal method
    public static Map<String, Employee> minMaxSalaryNormal() {
        Map<String, Employee> maxMinEmp = new HashMap<>();
        maxMinEmp.put("min", sampleEmployees().stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null));
        maxMinEmp.put("max", sampleEmployees().stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null));
        return maxMinEmp;
    }

    // 16) Min & Max salary employee using teeing
    public static Map<String, Employee> minMaxSalary() {
        return sampleEmployees().stream()
                .collect(Collectors.teeing(
                        minBy(Comparator.comparingDouble(Employee::getSalary)),
                        maxBy(Comparator.comparingDouble(Employee::getSalary)),
                        (min, max) -> Map.of(
                                "min", min.orElse(null),
                                "max", max.orElse(null)
                        )
                ));
    }

    // 17) Immutable list of sorted names
    public static List<String> immutableSortedNames() {
        return sampleEmployees().stream()
                .map(Employee::getName)
                .sorted()
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    // 18) State -> set of city strings (flatMapping)
    public static Map<String, Set<String>> stateCityMapping() {
        return sampleEmployees().stream()
                .collect(groupingBy(
                        e -> "all",
                        flatMapping(
                                e -> e.getAddresses().stream()
                                        .map(a -> a.getState() + ":" + a.getCity()),
                                toSet()
                        )
                ));
    }

    // 19) Multi-level sorting
    public static List<Employee> multiLevelSorting() {
        return sampleEmployees().stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()
                        .thenComparingInt(Employee::getAge)
                        .thenComparing(Employee::getName))
                .toList();
    }

    // 20) Employee with longest name
    public static Optional<Employee> longestNameEmployee() {
        return sampleEmployees().stream()
                .reduce((e1, e2) -> e1.getName().length() >= e2.getName().length() ? e1 : e2);
    }


    public static void main(String[] args) {
        distinctCities();
        groupByAge();
        highestPaidEmployee();
        sumSalaries();
        partitionByHasOffice();
        stateToEmployeeCount();
        joinedNamesBySalaryDesc();
        employeesInCity("Mumbai");
        nestedGroupingStateType();
        topNSalaries(3);
        salaryStatistics();
        toMapHandleDuplicates();
        parallelSumSalaries();
        collectCitiesToTreeSet();
        safeProcessingWithOptional();
    }
}