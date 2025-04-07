package org.example;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PerformanceTable {
    private static final int ITERATIONS = 10000;
    private static final int WARMUP_ITERATIONS = 1000;

    public void runBenchmarks() {
        warmUp();

        System.out.println("\n=== Сравнение производительности ArrayList и LinkedList ===");
        System.out.printf("%-18s | %-10s | %-15s | %-15s%n",
                "Операция", "Итерации", "ArrayList (нс)", "LinkedList (нс)");
        System.out.println("---------------------------------------------------------------");

        testAddToEnd();
        testAddToBeginning();
        testGetRandom();
    }

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
        }
    }

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

    private void printResult(String operation, long arrayListTime, long linkedListTime) {
        System.out.printf("%-18s | %-10d | %-15d | %-15d%n",
                operation, ITERATIONS, arrayListTime, linkedListTime);
    }
}