package ru.renue;

import com.opencsv.exceptions.CsvException;
import ru.renue.chekingValue.Checking;
import ru.renue.realisation.Realisation;

import java.io.IOException;
/*Я не понял как сделать поиск значений меньше чем O(n), не храня файл в памяти, были идеи воспользоваться Map ведь там
поиск всегда O(1), еще идея была как на курсах показывали разделить массив на две части Thread по нему пройтись и
склеить, буду рад если вы мне обьясните!)*/

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        Realisation realisationClass = new Realisation();
        Checking checkingClass = new Checking();
        System.out.println("Добро пожаловать в аэропорт!");
        while (true) {
            int i = checkingClass.checking();
            switch (i) {
                case 1:
                    realisationClass.allWrites();
                    break;
                case 2:
                    realisationClass.filteredAndSorted();
                    break;
                case 0:
                    System.exit(0);
            }
        }


    }

}
