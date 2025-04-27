package org.example;

public class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final Division division;
    private final int salary;
    private final String birthDate;

    public Person(int id, String name, String gender, Division division, int salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", division=" + division +
                ", salary=" + salary +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public Division getDivision() { return division; }
    public int getSalary() { return salary; }
    public String getBirthDate() { return birthDate; }
}
