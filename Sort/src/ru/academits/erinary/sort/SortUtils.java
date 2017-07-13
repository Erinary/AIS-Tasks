package ru.academits.erinary.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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

    /**
     * Компаратор для сортировки списка целых чисел по возрастанию.
     */
    public static class AscIntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Компаратор для сортировки списка строк по возрастанию.
     */
    public static class AscStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Метод построчного чтения файла.
     *
     * @param inputFileName имя входного файла
     * @param expectedLineNumber изначальный размер списка, в который будут записываться строки
     * @return список строк с содержимым файла
     * @throws SortException ошибка отсутствия входного файла
     */
    public static List<String> readStringInput(String inputFileName, int expectedLineNumber) throws SortException {
        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            List<String> listString = new ArrayList<>(expectedLineNumber);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                listString.add(line);
            }
            return listString;
        } catch (FileNotFoundException e) {
            throw new SortException("Файл не найден!");
        }
    }

    /**
     * Метод преобразования списка строк в список целых чисел, если строки содержат только цифры.
     *
     * @param listString преобразуемый список строк
     * @return новый список целых чисел
     * @throws SortException в списке присутствуют элементы, не являющиеся числом
     */
    public static List<Integer> parseIntList(List<String> listString) throws SortException {
        List<Integer> listInt = new ArrayList<>(listString.size());
        for (String s : listString) {
            try {
                Integer number = Integer.valueOf(s);
                listInt.add(number);
            } catch (NumberFormatException e) {
                throw new SortException(String.format("Строка в файле не является числом: '%s'", s));
            }
        }
        return listInt;
    }

    /**
     * Метод построчной записи в файл списока объектов используя {@link Object#toString()}.
     *
     * @param list список для записи в файл
     * @param outputFile имя выходного файла
     * @param <T>        тип данных списка
     * @throws SortException ошибка при создании нового файла
     */
    public static <T> void writeOutput(List<T> list, String outputFile) throws SortException {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (T e : list) {
                writer.println(e);
            }
        } catch (FileNotFoundException e) {
            throw new SortException("Ошибка создания выходного файла!");
        }
    }

}
