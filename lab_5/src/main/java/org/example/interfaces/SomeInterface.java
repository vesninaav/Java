package org.example.interfaces;

/**
 * Интерфейс, определяющий контракт для выполнения некоторого действия.
 * <p>
 * Данный интерфейс используется как абстракция для различных реализаций,
 * которые могут быть внедрены через механизм DI с помощью {@link org.example.annotation.AutoInjectable}.
 * </p>
 *
 * <p><b>Пример реализации:</b>
 * <pre>{@code
 * public class SomeImpl implements SomeInterface {
 *     @Override
 *     public void doSomething() {
 *         System.out.println("Action performed");
 *     }
 * }
 * }</pre>
 * </p>
 *
 * @see org.example.implementations.SomeImpl Базовая реализация
 * @see org.example.implementations.OtherImpl Альтернативная реализация
 * @see org.example.annotation.AutoInjectable Аннотация для внедрения зависимостей
 * @since 1.0
 */
public interface SomeInterface {

    /**
     * Выполняет определенное действие согласно реализации.
     * <p>
     * Конкретное поведение метода определяется классами, реализующими данный интерфейс.
     * Метод не имеет параметров и не возвращает значений.
     * </p>
     *
     * @implSpec
     * Реализации должны обеспечивать четко определенное поведение,
     * которое может включать вывод информации, выполнение вычислений
     * или другие действия.
     *
     * @implNote
     * Для стандартного использования рассмотрите:
     * <ul>
     *   <li>{@link org.example.implementations.SomeImpl} - вывод "A"</li>
     *   <li>{@link org.example.implementations.OtherImpl} - вывод "B"</li>
     * </ul>
     */
    void doSomething();
}