package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования функциональности класса {@link Person}.
 * Содержит unit-тесты для проверки создания объектов класса Person
 * и реализации метода toString().
 */
class PersonTest {

    /**
     * Тестирует создание экземпляра класса Person.
     * Проверяет, что все поля объекта корректно инициализируются,
     * а также что они возвращаются правильными методами доступа.
     */
    @Test
    void testPersonCreation() {
        Division division = new Division("Sales");
        Person person = new Person(1, "John Doe", "Male", division, 5000, "01.01.1990");

        assertEquals(1, person.getId(), "ID должен соответствовать заданному значению.");
        assertEquals("John Doe", person.getName(), "Имя должно соответствовать заданному значению.");
        assertEquals("Male", person.getGender(), "Пол должен соответствовать заданному значению.");
        assertEquals(division, person.getDivision(), "Подразделение должно соответствовать заданному значению.");
        assertEquals(5000, person.getSalary(), "Зарплата должна соответствовать заданному значению.");
        assertEquals("01.01.1990", person.getBirthDate(), "Дата рождения должна соответствовать заданному значению.");
    }

    /**
     * Тестирует метод toString() класса Person.
     * Проверяет, что строковое представление объекта Person
     * соответствует ожидаемому формату.
     */
    @Test
    void testPersonToString() {
        Division division = new Division("R&D");
        Person person = new Person(2, "Jane Smith", "Female", division, 6000, "15.05.1985");

        String expected = "Person{id=2, name='Jane Smith', gender='Female', " +
                "division=Division{id=" + division.getId() + ", name='R&D'}, " +
                "salary=6000, birthDate='15.05.1985'}";
        assertEquals(expected, person.toString(), "Метод toString() должен возвращать корректное строковое представление объекта.");
    }
}