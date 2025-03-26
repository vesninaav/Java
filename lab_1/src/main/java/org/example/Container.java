package org.example;

/**
 * Контейнер для хранения целых чисел на основе массива
 */
public class Container {
    private int[] nums;
    private int size;
    private static final int INIT_CAPACITY = 5;

    /**
     * Создает новый пустой контейнер с емкостью по умолчанию.
     */
    public Container() {
        this(INIT_CAPACITY);
    }

    /**
     * Создает новый пустой контейнер с указанной начальной емкостью.
     *
     * @param initCapacity начальная емкость контейнера
     * @throws IllegalArgumentException если указана отрицательная емкость
     */
    public Container(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Invalid capacity: " + initCapacity);
        }
        this.nums = new int[initCapacity];
        this.size = 0;
    }
}