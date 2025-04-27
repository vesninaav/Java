package org.example;

/**
 * Класс, представляющий подразделение в организации.
 * Каждое подразделение имеет уникальный идентификатор и название.
 * Уникальные идентификаторы генерируются автоматически при создании нового объекта.
 */
public class Division {
    private static int nextId = 1;
    private final int id;
    private final String name;

    /**
     * Создает новое подразделение с указанным названием.
     * Присваивает подразделению уникальный идентификатор.
     *
     * @param name название подразделения, не должно быть null
     * @throws IllegalArgumentException если name равен null
     */
    public Division(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Название подразделения не может быть null");
        }
        this.id = nextId++;
        this.name = name;
    }

    /**
     * Возвращает уникальный идентификатор подразделения.
     *
     * @return уникальный идентификатор подразделения
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает название подразделения.
     *
     * @return название подразделения
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает строковое представление подразделения в формате:
     * "Division{id=[id], name='[name]'}"
     *
     * @return строковое представление подразделения
     */
    @Override
    public String toString() {
        return "Division{id=" + id + ", name='" + name + "'}";
    }
}