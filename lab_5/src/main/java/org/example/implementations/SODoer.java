package org.example.implementations;

import org.example.interfaces.SomeOtherInterface;

/**
 * Конкретная реализация интерфейса {@link org.example.interfaces.SomeOtherInterface},
 * которая выводит символ "C" при выполнении действия.
 * <p>
 * Данная реализация используется для демонстрации механизма внедрения зависимостей
 * и является одной из возможных реализаций интерфейса SomeOtherInterface.
 * </p>
 *
 * <p><b>Пример конфигурации для внедрения:</b>
 * <pre>{@code
 * org.example.interfaces.SomeOtherInterface=org.example.implementations.SODoer
 * }</pre>
 * </p>
 *
 * @see org.example.interfaces.SomeOtherInterface Интерфейс, который реализуется
 * @see org.example.injector.Injector Класс для внедрения зависимостей
 * @since 1.0
 */
public class SODoer implements SomeOtherInterface {

    /**
     * Выполняет действие, определенное интерфейсом, выводя символ "C".
     * <p>
     * Реализация метода {@link org.example.interfaces.SomeOtherInterface#doSomeOther()}.
     * Всегда выводит "C" в стандартный поток вывода.
     * </p>
     *
     * @implSpec
     * Текущая реализация просто выводит "C" без дополнительной логики.
     * Может быть переопределена в подклассах для изменения поведения.
     */
    @Override
    public void doSomeOther() {
        System.out.println("C");
    }
}