package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для сравнения производительности операций ArrayList и LinkedList.
 * Содержит методы для тестирования различных операций над списками и вывода результатов.
 */
public class PerformanceTable {
    /** Количество итераций для основных тестов */
    private static final int ITERATIONS = 10000;

    /** Количество итераций для разогрева JVM */
    private static final int WARMUP_ITERATIONS = 1000;

    /**
     * Запускает все тесты производительности и выводит результаты в табличном виде.
     */
    public void runBenchmarks() {
        warmUp();

        System.out.println("\n=== Сравнение производительности ArrayList и LinkedList ===");
        System.out.printf("%-18s | %-10s | %-15s | %-15s%n",
                "Операция", "Итерации", "ArrayList (нс)", "LinkedList (нс)");
        System.out.println("---------------------------------------------------------------");

        testAddToEnd();
        testAddToBeginning();
        testGetRandom();
        testRemoveFromBeginning();
        testRemoveFromEnd();
    }

    /**
     * Метод для "разогрева" JVM перед проведением замеров производительности.
     * Выполняет основные операции над списками, чтобы JIT-компилятор оптимизировал код.
     */
    private void warmUp() {
        List<Integer> warmupList1 = new ArrayList<>();
        List<Integer> warmupList2 = new LinkedList<>();

        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            warmupList1.add(i);
            warmupList2.add(i);

            if (!warmupList1.isEmpty()) {
                warmupList1.get(i % warmupList1.size());
            }
            if (!warmupList2.isEmpty()) {
                warmupList2.get(i % warmupList2.size());
            }

            if (!warmupList1.isEmpty()) {
                warmupList1.remove(warmupList1.size() - 1);
            }
            if (!warmupList2.isEmpty()) {
                warmupList2.remove(warmupList2.size() - 1);
            }
        }
    }

    /**
     * Тестирует производительность операции добавления элементов в конец списка.
     */
    private void testAddToEnd() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            linkedList.add(i);
        }
        long linkedListTime = System.nanoTime() - start;

        printResult("add (конец)", arrayListTime, linkedListTime);
    }

    /**
     * Тестирует производительность операции добавления элементов в начало списка.
     */
    private void testAddToBeginning() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.add(0, i);
        }
        long arrayListTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            linkedList.add(0, i);
        }
        long linkedListTime = System.nanoTime() - start;

        printResult("add (начало)", arrayListTime, linkedListTime);
    }

    /**
     * Тестирует производительность операции получения элементов по индексу.
     */
    private void testGetRandom() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.get(i % ITERATIONS);
        }
        long arrayListTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            linkedList.get(i % ITERATIONS);
        }
        long linkedListTime = System.nanoTime() - start;

        printResult("get", arrayListTime, linkedListTime);
    }

    /**
     * Тестирует производительность операции удаления элементов из начала списка.
     */
    private void testRemoveFromBeginning() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.remove(0);
        }
        long arrayListTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            linkedList.remove(0);
        }
        long linkedListTime = System.nanoTime() - start;

        printResult("remove (начало)", arrayListTime, linkedListTime);
    }

    /**
     * Тестирует производительность операции удаления элементов из конца списка.
     */
    private void testRemoveFromEnd() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            arrayList.remove(arrayList.size() - 1);
        }
        long arrayListTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            linkedList.remove(linkedList.size() - 1);
        }
        long linkedListTime = System.nanoTime() - start;

        printResult("remove (конец)", arrayListTime, linkedListTime);
    }

    /**
     * Выводит результаты теста в форматированном виде.
     *
     * @param operation название тестируемой операции
     * @param arrayListTime время выполнения операции для ArrayList в наносекундах
     * @param linkedListTime время выполнения операции для LinkedList в наносекундах
     */
    private void printResult(String operation, long arrayListTime, long linkedListTime) {
        System.out.printf("%-18s | %-10d | %-15d | %-15d%n",
                operation, ITERATIONS, arrayListTime, linkedListTime);
    }
}