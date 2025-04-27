package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования функциональности класса {@link Division}.
 * Содержит unit-тесты для проверки корректности создания подразделений,
 * генерации уникальных идентификаторов и строкового представления.
 */
class DivisionTest {

    /**
     * Тестирует создание подразделения с указанным названием.
     * Проверяет:
     * - что название подразделения установлено корректно
     * - что идентификатор подразделения больше 0
     */
    @Test
    void testDivisionCreation() {
        Division division = new Division("IT");
        assertEquals("IT", division.getName(), "Название подразделения должно соответствовать переданному значению");
        assertTrue(division.getId() > 0, "Идентификатор подразделения должен быть положительным числом");
    }

    /**
     * Тестирует генерацию уникальных идентификаторов для разных подразделений.
     * Проверяет, что разные подразделения получают разные идентификаторы.
     * Перед тестом сбрасывает счетчик идентификаторов.
     */
    @Test
    void testDivisionIdsAreUnique() {
        Division.resetIdCounter();
        Division div1 = new Division("HR");
        Division div2 = new Division("Finance");
        assertNotEquals(div1.getId(), div2.getId(),
                "Разные подразделения должны иметь разные идентификаторы");
    }

    /**
     * Тестирует строковое представление подразделения.
     * Проверяет, что метод toString() возвращает строку в ожидаемом формате:
     * "Division{id=[id], name='[name]'}"
     */
    @Test
    void testDivisionToString() {
        Division division = new Division("Marketing");
        String expected = "Division{id=" + division.getId() + ", name='Marketing'}";
        assertEquals(expected, division.toString(),
                "Строковое представление подразделения должно соответствовать заданному формату");
    }
}