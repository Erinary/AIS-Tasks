package ru.academits.erinary.sort;

import java.util.Comparator;

/**
 * Компаратор для сортировки списка целых чисел по возрастанию.
 */
public class AscIntComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}