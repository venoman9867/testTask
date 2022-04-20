package ru.renue.chekingValue;

import java.util.Scanner;

public class Checking {
    public String choiceReturn() {
        System.out.println("Выберите, что хотите сделать:\n1.Просто вывести список аэропортов." +
                "\n2.Поиск значения в отфильтрованном и отсортированном списке.\n0.Выйти из программы.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int checking() {
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
}
