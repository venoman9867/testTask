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
                    int count=0;
                    String[] stroka;
                   SortedMap<String,String[]> filteredList = new TreeMap<>(){
                   };//Наш отфильтрованный список
                    CSVReader reader2 = new CSVReader(new FileReader(CsvFilePath));
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Ваша строка для поиска: ");
                    String word = scanner.next();
                    long time2 = System.currentTimeMillis();
                    while ((stroka = reader2.readNext()) != null) {//Фильтрация
                        String s = stroka[map.get("properties")];
                        if(filter(s, word)){
                            filteredList.put(s,stroka);//Заполнение отфильтрованного списка
                            count++;
                        }
                    }
                    System.out.println("Время операции: " + (System.currentTimeMillis() - time2)+" мс");
                    System.out.println("Количество записей: "+count);
                    filteredList.forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));
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

    public static boolean filter(String s, String word) {
        return s.contains(word);

    }
}
