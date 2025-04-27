package org.example;

/**
 * Класс, представляющий сотрудника организации.
 * Содержит информацию о персональных данных, подразделении и зарплате сотрудника.
 */
public class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final Division division;
    private final int salary;
    private final String birthDate;

    /**
     * Создает нового сотрудника с указанными параметрами.
     *
     * @param id уникальный идентификатор сотрудника
     * @param name полное имя сотрудника
     * @param gender пол сотрудника
     * @param division подразделение, в котором работает сотрудник
     * @param salary зарплата сотрудника
     * @param birthDate дата рождения сотрудника в формате строки
     * @throws IllegalArgumentException если любой из параметров (кроме salary) равен null,
     *         или если salary отрицательное число
     */
    public Person(int id, String name, String gender, Division division, int salary, String birthDate) {
        if (name == null || gender == null || division == null || birthDate == null) {
            throw new IllegalArgumentException("Ни один из параметров (кроме salary) не может быть null");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        }

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Возвращает строковое представление сотрудника в формате:
     * "Person{id=[id], name='[name]', gender='[gender]', division=[division], salary=[salary], birthDate='[birthDate]'}"
     *
     * @return строковое представление сотрудника
     */
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

    /**
     * @return уникальный идентификатор сотрудника
     */
    public int getId() {
        return id;
    }

    /**
     * @return полное имя сотрудника
     */
    public String getName() {
        return name;
    }

    /**
     * @return пол сотрудника
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return подразделение, в котором работает сотрудник
     */
    public Division getDivision() {
        return division;
    }

    /**
     * @return зарплата сотрудника
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @return дата рождения сотрудника в формате строки
     */
    public String getBirthDate() {
        return birthDate;
    }
}