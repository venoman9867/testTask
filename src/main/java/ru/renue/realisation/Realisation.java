package ru.renue.realisation;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Realisation {

    private final String CsvFilePath = "src/main/resources/airports.csv";


    public void allWrites() throws IOException, CsvException {
        long time = System.currentTimeMillis();
        CSVReader reader = new CSVReader(new FileReader(CsvFilePath));
        List<String[]> listOfAirports = reader.readAll();
        listOfAirports.forEach(x -> System.out.println(Arrays.toString(x)));
        System.out.println("Время операции: " + (System.currentTimeMillis() - time));
    }

    public void filteredAndSorted() throws IOException, CsvValidationException {
        Yaml yaml = new Yaml();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("application.yaml");
        Map<String, Integer> map = yaml.load(inputStream);
        int count = 0;
        String[] stroka;
        SortedMap<String, String[]> filteredList = new TreeMap<>() {};//Наш отфильтрованный и отсортированный список
        CSVReader reader2 = new CSVReader(new FileReader(CsvFilePath));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ваша строка для поиска: ");
        String word = scanner.next();
        long time2 = System.currentTimeMillis();
        while ((stroka = reader2.readNext()) != null) {//Фильтрация
            String s = stroka[map.get("properties")];
            if (filter(s, word)) {
                filteredList.put(s, stroka);//Заполнение отфильтрованного списка
                count++;
            }
        }
        System.out.println("Время операции: " + (System.currentTimeMillis() - time2) + " мс");
        System.out.println("Количество записей: " + count);
        filteredList.forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));

    }
    private boolean filter(String s, String word) {
        return s.contains(word);
    }
}
