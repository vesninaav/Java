package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "foreign_names.csv";
        char separator = ';';

        List<Person> people = readPeopleFromCsv(csvFilePath, separator);
        people.forEach(System.out::println);
    }

    public static List<Person> readPeopleFromCsv(String csvFilePath, char separator) {
        List<Person> people = new ArrayList<>();
        Map<String, Division> divisions = new HashMap<>();

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream(csvFilePath);
             InputStreamReader reader = new InputStreamReader(in);
             CSVReader csvReader = in == null ? null : new CSVReaderBuilder(reader)
                     .withCSVParser(new com.opencsv.CSVParserBuilder()
                             .withSeparator(separator)
                             .build())
                     .build()) {

            if (csvReader == null) {
                throw new FileNotFoundException(csvFilePath);
            }

            // Пропускаем заголовок
            csvReader.readNext();

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length >= 6) {
                    try {
                        int id = Integer.parseInt(nextLine[0].trim());
                        String name = nextLine[1].trim();
                        String gender = nextLine[2].trim();
                        String birthDate = nextLine[3].trim();
                        String divisionName = nextLine[4].trim();
                        int salary = Integer.parseInt(nextLine[5].trim());

                        Division division = divisions.computeIfAbsent(
                                divisionName.isEmpty() ? "Unknown" : divisionName,
                                Division::new
                        );

                        people.add(new Person(id, name, gender, division, salary, birthDate));
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка преобразования числа в строке: " + Arrays.toString(nextLine));
                    }
                } else {
                    System.err.println("Пропущена строка: недостаточно полей - " + Arrays.toString(nextLine));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return people;
    }
}