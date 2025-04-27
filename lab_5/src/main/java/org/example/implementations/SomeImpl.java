package org.example.implementations;

import org.example.interfaces.SomeInterface;

/**
 * Реализация интерфейса {@link org.example.interfaces.SomeInterface},
 * выводящая символ "A" при выполнении действия.
 * <p>
 * Данный класс представляет собой стандартную реализацию интерфейса SomeInterface,
 * используемую по умолчанию в системе при внедрении зависимостей.
 * </p>
 *
 * <p><b>Пример конфигурации для внедрения:</b>
 * <pre>{@code
 * org.example.interfaces.SomeInterface=org.example.implementations.SomeImpl
 * }</pre>
 * </p>
 *
 * @see org.example.interfaces.SomeInterface Базовый интерфейс
 * @see org.example.implementations.OtherImpl Альтернативная реализация
 * @see org.example.annotation.AutoInjectable Аннотация для внедрения зависимостей
 * @since 1.0
 */
public class SomeImpl implements SomeInterface {

    /**
     * Реализация метода интерфейса, выводящая символ "A".
     * <p>
     * Метод выполняет контракт интерфейса {@link org.example.interfaces.SomeInterface#doSomething()},
     * выводя в стандартный поток вывода символ "A".
     * </p>
     *
     * @implSpec
     * Данная реализация всегда выводит "A" без дополнительной обработки.
     * Поведение может быть изменено в подклассах при необходимости.
     *
     * @implNote
     * Для изменения выводимого значения следует использовать {@link org.example.implementations.OtherImpl}
     * или создать новую реализацию интерфейса.
     */
    @Override
    public void doSomething() {
        System.out.println("A");
    }
}