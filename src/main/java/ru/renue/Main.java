package ru.renue;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/airports.csv"));
        List<String[]> listOfAirports = reader.readAll();
        Yaml yaml = new Yaml();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("application.yaml");
        Map<String, Integer> map = yaml.load(inputStream);
        System.out.println("Текущая настройка фильтрации: " + map);
        System.out.println("Добро пожаловать в аэропорт!");
        while (true) {
            int i = checking();
            switch (i) {
                case 1:
                    long time = System.currentTimeMillis();
                    listOfAirports.forEach(x -> System.out.println(Arrays.toString(x)));
                    System.out.println("Время операции: " + (System.currentTimeMillis() - time));
                    break;
                case 2:
                    while (reader.readNext() != null) {
                        String[] stroka = reader.readNext();
                        String s = stroka[map.get("properties")];
                        filterAndSort(s);
                    }
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public static String choiceReturn() {
        System.out.println("Выберите, что хотите сделать:\n1.Просто вывести список аэропортов." +
                "\n2.Поиск значения в отфильтрованном и отсортированном списке.\n0.Выйти из программы.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int checking() {
        int i;
        while (true) {
            String choice = choiceReturn();
            try {
                i = Integer.parseInt(choice);
                if ((i != 1) && (i != 2) && (i != 0)) {
                    System.out.println("Такого варианта нету, попробуйте снова!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Такого варианта нету, попробуйте снова!");
            }
        }
        return i;

    }

    public static void filterAndSort(String s) {
        int i;//количество записей

    }
}
