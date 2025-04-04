package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.regex.*;

/**
 * Калькулятор математических выражений с поддержкой переменных и функций.
 * Поддерживает основные арифметические операции, тригонометрические функции,
 * логарифмы и другие математические операции.
 */
public class Calculator {
    private final Map<String, Function<double[], Double>> functions = new HashMap<>();

    /**
     * Конструктор калькулятора. Инициализирует встроенные математические функции.
     */
    public Calculator() {
        initializeFunctions();
    }

    /**
     * Инициализирует встроенные математические функции.
     */
    private void initializeFunctions() {
        functions.put("sin", args -> Math.sin(args[0]));
        functions.put("cos", args -> Math.cos(args[0]));
        functions.put("tan", args -> Math.tan(args[0]));
        functions.put("sqrt", args -> Math.sqrt(args[0]));
        functions.put("pow", args -> Math.pow(args[0], args[1]));
        functions.put("ln", args -> Math.log(args[0]));
        functions.put("log10", args -> Math.log10(args[0]));
        functions.put("log2", args -> Math.log(args[0])/Math.log(2));
        functions.put("abs", args -> Math.abs(args[0]));
    }

    /**
     * Вычисляет значение математического выражения.
     *
     * @param expression строка с математическим выражением
     * @param variables карта переменных (имя → значение)
     * @return результат вычисления
     * @throws IllegalArgumentException если выражение содержит ошибки
     */
    public double evaluate(String expression, Map<String, Double> variables) throws IllegalArgumentException {
        String processedExpr = preprocessExpression(expression);
        return evaluateExpression(processedExpr, variables);
    }

    /**
     * Находит все переменные в выражении (имена, не являющиеся функциями).
     *
     * @param expression строка с математическим выражением
     * @return множество имен переменных
     */
    public Set<String> findVariables(String expression) {
        Set<String> vars = new HashSet<>();
        Pattern pattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String var = matcher.group();
            if (!functions.containsKey(var)) {
                vars.add(var);
            }
        }
        return vars;
    }

    /**
     * Предварительная обработка выражения (удаление пробелов, проверка на пустоту).
     *
     * @param expr исходное выражение
     * @return обработанное выражение без пробелов
     * @throws IllegalArgumentException если выражение пустое
     */
    private String preprocessExpression(String expr) {
        String processed = expr.replaceAll("\\s+", "");
        if (processed.isEmpty()) {
            throw new IllegalArgumentException("Пустое выражение");
        }
        return processed;
    }

    /**
     * Вычисляет значение предварительно обработанного выражения.
     *
     * @param expr обработанное выражение
     * @param variables карта переменных (имя → значение)
     * @return результат вычисления
     */
    private double evaluateExpression(String expr, Map<String, Double> variables) {
        try {
            String exprWithValues = substituteVariables(expr, variables);
            return parseExpression(exprWithValues);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка вычисления выражения: " + e.getMessage());
        }
    }

    /**
     * Рекурсивный парсер математических выражений.
     *
     * @param expr выражение для разбора
     * @return результат вычисления
     */
    private double parseExpression(String expr) {
        return new Object() {
            int pos = -1, ch;

            /**
             * Переход к следующему символу выражения.
             */
            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            /**
             * Пропускает пробелы и проверяет текущий символ.
             *
             * @param charToEat символ для проверки
             * @return true если символ совпадает
             */
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            /**
             * Начинает разбор выражения.
             */
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Неожиданный символ: " + (char)ch);
                return x;
            }

            /**
             * Обрабатывает операции сложения и вычитания.
             */
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            /**
             * Обрабатывает операции умножения и деления.
             */
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            /**
             * Обрабатывает числа, скобки, функции и унарные операторы.
             */
            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expr.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = expr.substring(startPos, this.pos);
                    x = parseFactor();
                    if (functions.containsKey(func)) {
                        x = functions.get(func).apply(new double[]{x});
                    } else {
                        throw new RuntimeException("Неизвестная функция: " + func);
                    }
                } else {
                    throw new RuntimeException("Неожиданный символ: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }

    /**
     * Заменяет переменные в выражении на их значения.
     *
     * @param expr исходное выражение
     * @param variables карта переменных (имя → значение)
     * @return выражение с подставленными значениями переменных
     */
    private String substituteVariables(String expr, Map<String, Double> variables) {
        String result = expr;
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            result = result.replaceAll(Pattern.quote(entry.getKey()), entry.getValue().toString());
        }
        return result;
    }

    /**
     * Возвращает список доступных функций.
     *
     * @return множество имен доступных функций
     */
    public Set<String> getFunctionsList() {
        return functions.keySet();
    }
}