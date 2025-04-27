package org.example.interfaces;

/**
 * Интерфейс для выполнения дополнительного действия в системе.
 * <p>
 * Определяет контракт для операций, которые являются дополнением к основному функционалу,
 * реализуемому через {@link SomeInterface}. Используется вместе с механизмом внедрения зависимостей.
 * </p>
 *
 * <p><b>Пример реализации:</b>
 * <pre>{@code
 * public class SODoer implements SomeOtherInterface {
 *     @Override
 *     public void doSomeOther() {
 *         System.out.println("Performing secondary action");
 *     }
 * }
 * }</pre>
 * </p>
 *
 * @see org.example.implementations.SODoer Стандартная реализация
 * @see SomeInterface Основной интерфейс системы
 * @see org.example.annotation.AutoInjectable Аннотация для DI
 * @since 1.0
 */
public interface SomeOtherInterface {

    /**
     * Выполняет дополнительное действие.
     * <p>
     * Метод должен реализовывать специфическую логику, дополняющую основной функционал системы.
     * </p>
     *
     * @implSpec
     * Реализации должны:
     * <ul>
     *   <li>Иметь детерминированное поведение</li>
     *   <li>Не бросать неожиданных исключений</li>
     *   <li>Выполнять действие за разумное время</li>
     * </ul>
     *
     * @implNote
     * Стандартная реализация {@link org.example.implementations.SODoer} выводит "C".
     * Для кастомизации поведения создайте новую реализацию интерфейса.
     */
    void doSomeOther();
}