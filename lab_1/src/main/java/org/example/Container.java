package org.example;

/**
 * Контейнер для хранения целых чисел на основе массива
 */
public class Container {
    /**
     * Массив для хранения чисел.
     */
    private int[] nums;

    /**
     * Количество элементов, содержащихся в контейнере.
     */
    private int size;

    /**
     * Начальная емкость контейнера по умолчанию.
     */
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

    /**
     * Добавляет указанный элемент в конец контейнера.
     *
     * @param element элемент для добавления
     */
    public void add(int element) {
        if (size == nums.length) {
            resize();
        }
        nums[size++] = element;
    }

    /**
     * Увеличивает емкость контейнера.
     * Новая емкость равна текущей емкости, умноженной на 2.
     */
    private void resize() {
        int newCapacity = nums.length * 2;
        int[] newArray = new int[newCapacity];
        System.arraycopy(nums, 0, newArray, 0, size);
        nums = newArray;
    }

    /**
     * Возвращает количество элементов в контейнере.
     *
     * @return количество элементов в контейнере
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс запрашиваемого элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public int get(int index) {
        checkIsCorrectIndex(index);
        return nums[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public void remove(int index) {
        checkIsCorrectIndex(index);
        int elementsToShift = size - index - 1;
        if (elementsToShift > 0) {
            System.arraycopy(nums, index + 1, nums, index, elementsToShift);
        }
        size--;
    }

    /**
     * Проверяет корректность индекса.
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс недопустим
     */
    private void checkIsCorrectIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " outside the container range.");
        }
    }

    /**
     * Проверяет, пуст ли контейнер.
     *
     * @return true если контейнер не содержит элементов, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Очищает контейнер, удаляя все элементы.
     * Емкость массива при этом не изменяется.
     */
    public void clear() {
        size = 0;
    }

    /**
     * Возвращает строковое представление контейнера.
     *
     * @return строковое представление элементов контейнера
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(nums[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}