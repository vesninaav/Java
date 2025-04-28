package org.example;

import org.example.annotation.AutoInjectable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Тестовый класс для {@link org.example.injector.Injector}.
 * <p>
 * Содержит unit-тесты, проверяющие корректность работы механизма внедрения зависимостей.
 * </p>
 *
 * <p><b>Тестируемые сценарии:</b>
 * <ul>
 *   <li>Успешное внедрение зависимостей</li>
 *   <li>Обработка аннотированных и неаннотированных полей</li>
 *   <li>Обработка ошибок конфигурации</li>
 * </ul>
 * </p>
 */
class InjectorTest {

    /**
     * Тестовый класс-бин с аннотированными и обычными полями.
     */
    static class TestBean {
        /** Аннотированное поле для внедрения зависимости */
        @AutoInjectable
        private Runnable dependency;

        /** Обычное поле, не предназначенное для внедрения */
        private String notInjected;
    }

    /**
     * Тест успешного внедрения зависимостей при валидной конфигурации.
     *
     * @param tempDir временная директория для создания тестового файла конфигурации
     * @throws Exception в случае ошибок выполнения теста
     */
    @Test
    void inject_ShouldInjectDependencies_WhenConfigValid(@TempDir Path tempDir) throws Exception {
        // Создаем временный конфигурационный файл
        Path configFile = tempDir.resolve("config.properties");
        Files.writeString(configFile,
                "java.lang.Runnable=java.lang.Thread");

        TestInjector injector = new TestInjector(configFile.toString());
        TestBean bean = new TestBean();

        TestBean result = injector.inject(bean);

        assertSame(bean, result);
        assertNotNull(bean.dependency);
        assertTrue(bean.dependency instanceof Thread);
        assertNull(bean.notInjected);
    }

    /**
     * Тест проверяет, что аннотированные поля заполняются зависимостями.
     *
     * @throws Exception в случае ошибок выполнения теста
     */
    @Test
    void inject_ShouldInjectAnnotatedFields() throws Exception {
        Properties props = new Properties();
        props.put("java.lang.Runnable", "java.lang.Thread");

        TestInjector injector = new TestInjector(props);
        TestBean bean = new TestBean();

        injector.inject(bean);

        assertNotNull(bean.dependency);  // Аннотированное поле должно быть заполнено
        assertNull(bean.notInjected);   // Неаннотированное поле должно остаться null
    }

    /**
     * Тест проверяет, что неаннотированные поля игнорируются.
     *
     * @throws Exception в случае ошибок выполнения теста
     */
    @Test
    void inject_ShouldSkipNonAnnotatedFields() throws Exception {
        /**
         * Внутренний тестовый класс без аннотаций.
         */
        class BeanWithoutAnnotations {
            private Runnable notInjectable;
        }

        Properties props = new Properties();
        props.put("java.lang.Runnable", "java.lang.Thread");

        TestInjector injector = new TestInjector(props);
        BeanWithoutAnnotations bean = new BeanWithoutAnnotations();

        injector.inject(bean);

        assertNull(bean.notInjectable);  // Поле без аннотации должно остаться null
    }

    /**
     * Тест проверяет обработку случая, когда класс реализации не найден.
     *
     * @throws Exception в случае ошибок выполнения теста
     */
    @Test
    void inject_ShouldThrow_WhenClassNotFound() throws Exception {
        Properties props = new Properties();
        props.put("java.lang.Runnable", "non.existing.Class");

        TestInjector injector = new TestInjector(props);
        TestBean bean = new TestBean();

        assertThrows(ClassNotFoundException.class, () -> {
            injector.inject(bean);
        });
    }

    /**
     * Тест проверяет обработку случая, когда конфигурационный файл не найден.
     */
    @Test
    void constructor_ShouldThrow_WhenConfigMissing() {
        assertThrows(IOException.class, () -> {
            new TestInjector("missing.properties");
        });
    }

    /**
     * Тестовая реализация {@link org.example.injector.Injector} для целей тестирования.
     * <p>
     * Позволяет тестировать функционал инжектора без зависимости от файловой системы.
     * </p>
     */
    static class TestInjector {
        private final Properties properties;

        /**
         * Создает инжектор с конфигурацией из файла.
         *
         * @param configPath путь к конфигурационному файлу
         * @throws IOException если файл не найден или не может быть прочитан
         */
        public TestInjector(String configPath) throws IOException {
            this.properties = new Properties();
            this.properties.load(Files.newInputStream(Path.of(configPath)));
        }

        /**
         * Создает инжектор с готовым набором свойств.
         *
         * @param properties предустановленные свойства конфигурации
         */
        public TestInjector(Properties properties) {
            this.properties = properties;
        }

        /**
         * Выполняет внедрение зависимостей в переданный объект.
         *
         * @param <T> тип объекта
         * @param object объект для внедрения зависимостей
         * @return тот же объект с внедренными зависимостями
         * @throws Exception в случае ошибок:
         *         <ul>
         *           <li>{@link ClassNotFoundException} - если класс реализации не найден</li>
         *           <li>{@link ReflectiveOperationException} - если не удалось создать экземпляр</li>
         *         </ul>
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
}