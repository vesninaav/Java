package org.example.beans;

import org.example.annotation.AutoInjectable;
import org.example.interfaces.SomeInterface;
import org.example.interfaces.SomeOtherInterface;

/**
 * Класс-биан, демонстрирующий работу автоматического внедрения зависимостей.
 * <p>
 * Содержит поля, которые должны быть автоматически инициализированы
 * через механизм внедрения зависимостей с использованием аннотации {@link org.example.annotation.AutoInjectable}.
 * </p>
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * SomeBean bean = new Injector().inject(new SomeBean());
 * bean.foo();
 * }</pre>
 * </p>
 *
 * @see org.example.annotation.AutoInjectable Аннотация для автоматического внедрения
 * @see org.example.injector.Injector Класс для внедрения зависимостей
 * @since 1.0
 */
public class SomeBean {
    /**
     * Поле для внедрения реализации интерфейса SomeInterface.
     * <p>
     * Конкретная реализация определяется в файле конфигурации.
     * </p>
     */
    @AutoInjectable
    private SomeInterface field1;

    /**
     * Поле для внедрения реализации интерфейса SomeOtherInterface.
     * <p>
     * Конкретная реализация определяется в файле конфигурации.
     * </p>
     */
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Выполняет действия, используя внедренные зависимости.
     * <p>
     * Вызывает методы {@link SomeInterface#doSomething()} и
     * {@link SomeOtherInterface#doSomeOther()} на внедренных реализациях.
     * </p>
     *
     * @throws IllegalStateException если зависимости не были внедрены
     *         перед вызовом метода
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}