package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void testGetFunctionsList() {
        Set<String> functions = calculator.getFunctionsList();
        assertAll(
                () -> assertTrue(functions.contains("sin")),
                () -> assertTrue(functions.contains("cos")),
                () -> assertTrue(functions.contains("sqrt")),
                () -> assertTrue(functions.contains("pow")),
                () -> assertEquals(9, functions.size())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "2+3, 5.0",
            "10-4, 6.0",
            "3*4, 12.0",
            "15/3, 5.0",
            "2^3, 8.0",
            "3+4*2, 11.0",
            "(3+4)*2, 14.0",
            "2+3*4-5/2, 11.5",
            "-5+3, -2.0",
            "3*-4, -12.0"
    })
    void testBasicOperations(String expression, double expected) {
        double result = calculator.evaluate(expression, Collections.emptyMap());
        assertEquals(expected, result, 0.0001);
    }

    @ParameterizedTest
    @CsvSource({
            "sin(0), 0.0",
            "cos(0), 1.0",
            "tan(0), 0.0",
            "sqrt(4), 2.0",
            "'pow(2,3)', 8.0",
            "ln(1), 0.0",
            "'log10(100)', 2.0",
            "abs(-5), 5.0"
    })
    void testFunctions(String expression, double expected) {
        double result = calculator.evaluate(expression, Collections.emptyMap());
        assertEquals(expected, result, 0.0001);
    }

    @Test
    void testVariables() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 2.0);
        variables.put("y", 3.0);

        assertAll(
                () -> assertEquals(5.0, calculator.evaluate("x+y", variables), 0.0001),
                () -> assertEquals(6.0, calculator.evaluate("x*y", variables), 0.0001),
                () -> assertEquals(8.0, calculator.evaluate("pow(x,y)", variables), 0.0001)
        );
    }

    @Test
    void testFindVariables() {
        Set<String> variables = calculator.findVariables("x + sin(y) + sqrt(z)");
        assertAll(
                () -> assertEquals(3, variables.size()),
                () -> assertTrue(variables.contains("x")),
                () -> assertTrue(variables.contains("y")),
                () -> assertTrue(variables.contains("z")),
                () -> assertFalse(variables.contains("sin")),
                () -> assertFalse(variables.contains("sqrt"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "\t\n"
    })
    void testEmptyExpression(String expression) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.evaluate(expression, Collections.emptyMap()));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2 + * 3",
            "sin()",
            "x + ",
            "3..5 + 2",
            "unknown(5)"
    })
    void testInvalidExpressions(String expression) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.evaluate(expression, Collections.emptyMap()));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.evaluate("5/0", Collections.emptyMap()));
    }

    @Test
    void testNegativeSquareRoot() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.evaluate("sqrt(-1)", Collections.emptyMap()));
    }

    @Test
    void testSpacesInExpression() {
        double result = calculator.evaluate(" 2 + 3 * 4 ", Collections.emptyMap());
        assertEquals(14.0, result, 0.0001);
    }
}