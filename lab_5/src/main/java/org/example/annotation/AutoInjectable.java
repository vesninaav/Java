package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для автоматического внедрения зависимостей.
 * <p>
 * Поля, помеченные этой аннотацией, будут автоматически инициализированы
 * соответствующими реализациями, указанными в файле конфигурации.
 * </p>
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * @AutoInjectable
 * private SomeInterface field;
 * }</pre>
 * </p>
 *
 * @see org.example.injector.Injector Класс, осуществляющий внедрение зависимостей
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {}