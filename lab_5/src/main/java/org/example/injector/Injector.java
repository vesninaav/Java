package org.example.injector;

import org.example.annotation.AutoInjectable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для автоматического внедрения зависимостей на основе аннотации {@link org.example.annotation.AutoInjectable}.
 * <p>
 * Осуществляет поиск полей, помеченных аннотацией {@code AutoInjectable}, и инициализирует их
 * экземплярами классов, указанных в файле конфигурации {@code config.properties}.
 * </p>
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * SomeBean bean = new Injector().inject(new SomeBean());
 * }</pre>
 * </p>
 *
 * <p><b>Формат файла конфигурации:</b>
 * <pre>{@code
 * полное.имя.интерфейса=полное.имя.реализации
 * }</pre>
 * </p>
 *
 * @see org.example.annotation.AutoInjectable Аннотация для пометки полей
 * @since 1.0
 */
public class Injector {
    private Properties properties;

    /**
     * Создает новый экземпляр Injector и загружает конфигурацию из файла.
     *
     * @throws IOException если файл конфигурации не найден или не может быть прочитан
     */
    public Injector() throws IOException {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            properties.load(input);
        }
    }

    /**
     * Внедряет зависимости в переданный объект.
     * <p>
     * Находит все поля, помеченные аннотацией {@link org.example.annotation.AutoInjectable},
     * и инициализирует их соответствующими реализациями из конфигурации.
     * </p>
     *
     * @param <T> тип объекта для внедрения зависимостей
     * @param object объект, в который нужно внедрить зависимости
     * @return тот же объект с внедренными зависимостями
     * @throws Exception если произошла ошибка при:
     *         <ul>
     *           <li>доступе к полям объекта</li>
     *           <li>создании экземпляра реализации</li>
     *           <li>установке значения поля</li>
     *         </ul>
     *
     * @see #Injector() Конструктор для создания инжектора
     */
    public <T> T inject(T object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implementationClassName = properties.getProperty(fieldType.getName());
                if (implementationClassName != null) {
                    Class<?> implementationClass = Class.forName(implementationClassName);
                    Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(object, implementationInstance);
                }
            }
        }
        return object;
    }
}