package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Демонстрация работы IntContainer ===");

        // 1. Создание контейнера
        System.out.print("Введите начальную емкость контейнера (или 0 для значения по умолчанию): ");
        int capacity = scanner.nextInt();
        Container container = capacity > 0 ? new Container(capacity) : new Container();
        System.out.println("Создан контейнер. Размер: " + container.size());

        // 2. Добавление элементов
        System.out.println("\nДобавление элементов (введите числа, для завершения введите 'стоп'):");
        scanner.nextLine();
        while (true) {
            System.out.print("Введите число (или 'стоп'): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("стоп")) {
                break;
            }
            try {
                int num = Integer.parseInt(input);
                container.add(num);
                System.out.println("Добавлено: " + num + ". Текущий размер: " + container.size());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число или 'стоп'");
            }
        }
        System.out.println("Текущее состояние: " + container);

        // 3. Получение элементов
        if (!container.isEmpty()) {
            System.out.println("\nПолучение элементов по индексу:");
            System.out.print("Введите индекс для получения (0-" + (container.size()-1) + "): ");
            int index = scanner.nextInt();
            try {
                System.out.println("Элемент с индексом " + index + ": " + container.get(index));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // 4. Удаление элементов
        if (!container.isEmpty()) {
            System.out.println("\nУдаление элемента по индексу:");
            System.out.print("Введите индекс для удаления (0-" + (container.size()-1) + "): ");
            int removeIndex = scanner.nextInt();
            try {
                int removedValue = container.get(removeIndex);
                container.remove(removeIndex);
                System.out.println("Удален элемент " + removedValue + ". Новое состояние: " + container);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // 5. Проверка на пустоту
        System.out.println("\nПроверка состояния контейнера:");
        System.out.println("Контейнер пустой? " + container.isEmpty());

        // 6. Очистка контейнера
        System.out.print("\nОчистить контейнер? (да/нет): ");
        scanner.nextLine(); // Очистка буфера
        String clearChoice = scanner.nextLine();
        if (clearChoice.equalsIgnoreCase("да")) {
            container.clear();
            System.out.println("Контейнер очищен. Новое состояние: " + container);
        }

        System.out.println("\n=== Демонстрация завершена ===");
        scanner.close();
    }
}