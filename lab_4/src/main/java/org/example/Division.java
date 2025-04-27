package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.*;

public class Division {
    private static int nextId = 1;
    private final int id;
    private final String name;

    public Division(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Division{id=" + id + ", name='" + name + "'}";
    }
}
