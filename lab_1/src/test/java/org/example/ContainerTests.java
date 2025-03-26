package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContainerTests {
    private Container container;

    @BeforeEach
    void setUp() {
        container = new Container();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0, container.size());
        assertTrue(container.isEmpty());
    }

    @Test
    void testConstructorWithInitialCapacity() {
        Container customContainer = new Container(5);
        assertEquals(0, customContainer.size());
        assertTrue(customContainer.isEmpty());
    }

    @Test
    void testConstructorWithNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new Container(-1));
    }

    @Test
    void testAddAndGet() {
        container.add(1);
        container.add(25);
        container.add(74);

        assertEquals(3, container.size());
        assertEquals(1, container.get(0));
        assertEquals(25, container.get(1));
        assertEquals(74, container.get(2));
    }

    @Test
    void testGetWithInvalidIndex() {
        container.add(7);
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    @Test
    void testRemove() {
        container.add(1);
        container.add(25);
        container.add(74);

        container.remove(1);
        assertEquals(2, container.size());
        assertEquals(1, container.get(0));
        assertEquals(74, container.get(1));

        container.remove(0);
        assertEquals(1, container.size());
        assertEquals(74, container.get(0));
    }

    @Test
    void testRemoveFirstElement() {
        container.add(11);
        container.add(20);
        container.remove(0);
        assertEquals(1, container.size());
        assertEquals(20, container.get(0));
    }

    @Test
    void testRemoveLastElement() {
        container.add(15);
        container.add(12);
        container.remove(1);
        assertEquals(1, container.size());
        assertEquals(15, container.get(0));
    }

    @Test
    void testRemoveWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(0));
        container.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }

    @Test
    void testIsEmpty() {
        assertTrue(container.isEmpty());
        container.add(13);
        assertFalse(container.isEmpty());
        container.remove(0);
        assertTrue(container.isEmpty());
    }

    @Test
    void testClear() {
        container.add(1);
        container.add(27);
        container.clear();
        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

    @Test
    void testToString() {
        assertEquals("[]", container.toString());
        container.add(17);
        assertEquals("[17]", container.toString());
        container.add(2);
        container.add(30);
        assertEquals("[17, 2, 30]", container.toString());
    }

    @Test
    void testAutoResize() {
        Container container = new Container(2);
        container.add(1);
        container.add(2);
        container.add(3);

        assertEquals(3, container.size());
        assertEquals(1, container.get(0));
        assertEquals(2, container.get(1));
        assertEquals(3, container.get(2));
    }
}