package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.regex.*;

public class Calculator {
    private final Map<String, Function<double[], Double>> functions = new HashMap<>();

    public Calculator() {
        initializeFunctions();
    }

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

    public double evaluate(String expression, Map<String, Double> variables) throws IllegalArgumentException {
        String processedExpr = preprocessExpression(expression);
        return evaluateExpression(processedExpr, variables);
    }

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

    private String preprocessExpression(String expr) {
        String processed = expr.replaceAll("\\s+", "");
        if (processed.isEmpty()) {
            throw new IllegalArgumentException("Пустое выражение");
        }
        return processed;
    }

    private double evaluateExpression(String expr, Map<String, Double> variables) {
        try {
            String exprWithValues = substituteVariables(expr, variables);
            return parseExpression(exprWithValues);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка вычисления выражения: " + e.getMessage());
        }
    }

    private double parseExpression(String expr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Неожиданный символ: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

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

    private String substituteVariables(String expr, Map<String, Double> variables) {
        String result = expr;
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            result = result.replaceAll(Pattern.quote(entry.getKey()), entry.getValue().toString());
        }
        return result;
    }
}
