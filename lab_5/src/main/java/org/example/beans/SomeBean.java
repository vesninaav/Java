package org.example.beans;

import org.example.annotation.AutoInjectable;
import org.example.interfaces.SomeInterface;
import org.example.interfaces.SomeOtherInterface;

public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}