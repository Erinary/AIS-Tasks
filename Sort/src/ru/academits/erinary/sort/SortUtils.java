package ru.academits.erinary.sort;

import java.util.Comparator;
import java.util.List;

public final class SortUtils {
    private SortUtils() {
    }

    /**
     * Обобщенная сортировка списка методом вставок.
     *
     * @param list сортируемый список
     * @param cmp  компаратор, используемый при сравнении элементов списка
     * @param <T>  тип данных списка
     */
    public static <T> void sortList(List<T> list, Comparator<T> cmp) {
        for (int i = 1; i < list.size(); ++i) {
            for (int j = i; j > 0 && cmp.compare(list.get(j - 1), list.get(j)) > 0; --j) {
                T temp = list.get(j - 1);
                list.set(j - 1, list.get(j));
                list.set(j, temp);
            }
        }
    }
}
