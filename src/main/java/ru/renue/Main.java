package ru.renue;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        String CsvFilePath = "src/main/resources/airports.csv";
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
                    CSVReader reader = new CSVReader(new FileReader(CsvFilePath));
                    List<String[]> listOfAirports = reader.readAll();
                    listOfAirports.forEach(x -> System.out.println(Arrays.toString(x)));
                    System.out.println("Время операции: " + (System.currentTimeMillis() - time));
                    break;
                case 2:
                    String[] stroka;
                    List<String> filteredList = new ArrayList<>();//Наш отфильтрованный список
                    CSVReader reader2 = new CSVReader(new FileReader(CsvFilePath));
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Ваша строка для поиска: ");
                    String word = scanner.next();
                    while ((stroka = reader2.readNext()) != null) {//Фильтрация
                        String s = stroka[map.get("properties")];
                        if(filterAndSort(s, word)){
                            filteredList.add(s);//Заполнение отфильтрованного списка
                        }
                    }
                    Collections.sort(filteredList);//Сортировка
                    filteredList.forEach(System.out::println);
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

    public static boolean filterAndSort(String s, String word) {
        return s.contains(word);

    }
}
