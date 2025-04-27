package org.example;

import org.example.beans.SomeBean;
import org.example.injector.Injector;

public class Main {
    public static void main(String[] args) throws Exception {
        Injector injector = new Injector();
        SomeBean sb = injector.inject(new SomeBean());
        sb.foo();
    }
}