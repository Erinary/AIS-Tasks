package ru.aits.erinary.range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range rangeA = new Range(0, 5);
        Range rangeB = new Range(-1, 10);

        System.out.printf("Интервал 1: %s", rangeA);
        System.out.println();
        System.out.printf("Интервал 2: %s", rangeB);
        System.out.println();
        System.out.printf("Пересечение: %s", rangeA.getIntersection(rangeB));
        System.out.println();
        System.out.printf("Объединение: %s", Arrays.toString(rangeA.uniteRanges(rangeB)));
        System.out.println();
        System.out.printf("Разность: %s", Arrays.toString(rangeA.subtractRanges(rangeB)));

    }
}
