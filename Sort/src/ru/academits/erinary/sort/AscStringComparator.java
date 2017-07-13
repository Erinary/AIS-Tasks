package ru.academits.erinary.sort;

import java.util.Comparator;

/**
 * Компаратор для сортировки списка строк по возрастанию.
 */
public class AscStringComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
