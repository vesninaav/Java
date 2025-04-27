package org.example.implementations;

import org.example.interfaces.SomeInterface;

/**
 * Реализация интерфейса {@link org.example.interfaces.SomeInterface},
 * выводящая букву "B" при выполнении действия.
 * <p>
 * Данная реализация является одной из возможных вариантов реализации интерфейса
 * и может быть использована для внедрения зависимостей через механизм
 * {@link org.example.annotation.AutoInjectable}.
 * </p>
 *
 * <p><b>Пример конфигурации для внедрения:</b>
 * <pre>{@code
 * org.example.interfaces.SomeInterface=org.example.implementations.OtherImpl
 * }</pre>
 * </p>
 *
 * @see org.example.interfaces.SomeInterface Интерфейс, который реализуется
 * @see org.example.implementations.SomeImpl Альтернативная реализация
 * @since 1.0
 */
public class OtherImpl implements SomeInterface {

    /**
     * Выполняет действие, выводящее символ "B" в стандартный вывод.
     * <p>
     * Реализация метода из интерфейса {@link org.example.interfaces.SomeInterface#doSomething()}.
     * </p>
     *
     * @implSpec
     * Текущая реализация всегда выводит "B" без каких-либо дополнительных действий.
     */
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}