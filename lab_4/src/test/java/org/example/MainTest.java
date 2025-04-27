package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования функциональности чтения CSV-файлов в классе {@link Main}.
 * Содержит unit-тесты для проверки корректности парсинга CSV-файлов
 * и обработки данных о людях.
 */
class MainTest {

    /**
     * Тестирует чтение корректного CSV-файла с данными о людях.
     * Проверяет:
     * - что все записи из файла были прочитаны
     * - что данные первой записи соответствуют ожидаемым значениям
     * - что подразделение было создано корректно
     *
     * Использует тестовый файл test_foreign_names.csv, который должен содержать:
     * id;name;gender;BirtDate;Division;Salary
     * 1;John;Male;01.01.1990;IT;5000
     * 2;Jane;Female;02.02.1991;HR;6000
     */
    @Test
    void testReadPeopleFromCsv() {
        List<Person> people = Main.readPeopleFromCsv("test_foreign_names.csv", ';');

        assertEquals(2, people.size(), "Должно быть прочитано 2 человека");

        Person first = people.get(0);
        assertEquals(1, first.getId(), "Неверный ID первого человека");
        assertEquals("John", first.getName(), "Неверное имя первого человека");
        assertEquals("IT", first.getDivision().getName(), "Неверное подразделение");
    }

    /**
     * Тестирует чтение CSV-файла с некорректными данными.
     * Проверяет, что записи с ошибками не добавляются в результирующий список.
     *
     * Использует тестовый файл test_invalid.csv, который должен содержать:
     * id;name;gender;BirtDate;Division;Salary
     * invalid;;Male;01.01.1990;IT;5000
     * 2;Jane;Female;02.02.1991;HR;not_a_number
     */
    @Test
    void testReadPeopleFromCsvWithInvalidData() {
        List<Person> people = Main.readPeopleFromCsv("test_invalid.csv", ';');

        assertTrue(people.isEmpty(), "Некорректные данные не должны добавляться");
    }
}