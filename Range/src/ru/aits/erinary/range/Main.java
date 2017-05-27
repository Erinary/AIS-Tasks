package ru.aits.erinary.range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные:");
        System.out.print("Граница 1: ");
        double sideA = scanner.nextDouble();
        System.out.print("Граница 2: ");
        double sideB = scanner.nextDouble();
        System.out.print("Число: ");
        double number = scanner.nextDouble();

        Range range = new Range(sideA, sideB);
        System.out.println("Вывод интервала в порядке возрастания:");
        System.out.println("Начало: " + range.getFrom());
        System.out.println("Конец: " + range.getTo());
        System.out.println("Длина интервала: " + range.getLength());

        if (range.isInside(number)) {
            System.out.println("Число входит в заданный промежуток");
        } else {
            System.out.println("Число не входит в заданный промежуток");
        }
    }
}
