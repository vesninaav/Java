package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Калькулятор выражений ===");
        System.out.println("Доступные функции: " + calculator.getFunctionsList());
        System.out.println("Примеры выражений:");
        System.out.println("  (2 + x) * 4 - 5");
        System.out.println("  sqrt(25) + pow(2, 3)");
        System.out.println("  log10(100) * sin(x)");
        System.out.println("Введите 'exit' для выхода\n");

        while (true) {
            System.out.print("\nВведите выражение: ");
            String expression = scanner.nextLine().trim();

            if (expression.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                Set<String> variables = calculator.findVariables(expression);
                Map<String, Double> variableValues = new HashMap<>();

                for (String var : variables) {
                    requestVariableValue(var, variableValues, scanner);
                }

                double result = calculator.evaluate(expression, variableValues);
                System.out.println("Результат: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        System.out.println("Работа калькулятора завершена.");
        scanner.close();
    }

    private static void requestVariableValue(String varName, Map<String, Double> variables, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите значение переменной " + varName + ": ");
                String input = scanner.nextLine().trim();
                double value = Double.parseDouble(input);
                variables.put(varName, value);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Неверный формат числа. Пожалуйста, введите число.");
            }
        }
    }
}