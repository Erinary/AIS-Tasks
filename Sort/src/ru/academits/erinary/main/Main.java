package ru.academits.erinary.main;

import ru.academits.erinary.sort.ContentType;
import ru.academits.erinary.sort.SortException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MAX_LIST_CAPACITY = 100;

    private static <T> void sortList(List<T> list, Comparator<T> cmp) {
        for (int i = 1; i < list.size(); ++i) {
            T temp = list.get(i);
            for (int j = i - 1; j >= 0; --j) {
                if (j == 0) {
                    if (cmp.compare(list.get(j), temp) <= 0) {
                        list.set(j + 1, temp);
                    } else {
                        list.set(j + 1, list.get(j));
                        list.set(j, temp);
                    }
                } else if (cmp.compare(temp, list.get(j)) >= 0) {
                    list.set(j + 1, temp);
                    break;
                } else {
                    list.set(j + 1, list.get(j));
                }
            }
        }
    }

    private static class AscIntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private static class DescIntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static class AscStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    private static class DescStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return -o1.compareTo(o2);
        }
    }

    private static void sortFile(String in, String out, ContentType contentType, boolean ascendingSort) throws SortException {
        if (contentType == ContentType.INT) {
            List<String> listString = Main.readStringInput(in);
            List<Integer> outList = Main.parseIntList(listString);
            Comparator<Integer> cmp = (ascendingSort) ? new AscIntComparator() : new DescIntComparator();
            Main.sortList(outList, cmp);
            Main.writeOutput(outList, out);
        } else {
            List<String> outList = Main.readStringInput(in);
            Comparator<String> cmp = (ascendingSort) ? new AscStringComparator() : new DescStringComparator();
            Main.sortList(outList, cmp);
            Main.writeOutput(outList, out);
        }
    }

    private static List<String> readStringInput(String inputFile) throws SortException {
        try (Scanner scanner = new Scanner(new FileInputStream(inputFile))) {
            List<String> listString = new ArrayList<>(MAX_LIST_CAPACITY);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                listString.add(line);
            }
            return listString;
        } catch (FileNotFoundException e) {
            throw new SortException("Файл не найден!");
        }
    }

    private static List<Integer> parseIntList(List<String> listString) throws SortException {
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

    private static <T> void writeOutput(List<T> sortedList, String outputFile) throws SortException {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (T e : sortedList) {
                writer.println(e);
            }
        } catch (FileNotFoundException e) {
            throw new SortException("Ошибка создания выходного файла!");
        }
    }

    private static void printHelp() {
        System.out.println("Выдает файл с отсортированным по возрастанию или убыванию содержимым входного файла.");
        System.out.println("java -jar sort.jar in.txt out.txt (-i|-s) (-a|-d)");
        System.out.println("in.txt входной файл");
        System.out.println("out.txt выходной файл");
        System.out.println("-i содержимое - целые числа");
        System.out.println("-s содержимое - строки");
        System.out.println("-a сортировка по возрастанию");
        System.out.println("-d сортировка по убыванию");
    }

    public static void main(String[] args) {
        if (args.length != 0 && args[0].equals("-help")) {
            Main.printHelp();
            return;
        }
        if (args.length != 4) {
            System.out.println("Передано неверное количество аргументов!");
            Main.printHelp();
            return;
        }

        String in = args[0];
        String out = args[1];
        String typeArg = args[2];
        String sortOrderArg = args[3];

        // Проверка и преобразование строковых значений аргументов программы к нужным типам
        ContentType contentType;
        //noinspection IfCanBeSwitch
        if (typeArg.equals("-i")) {
            contentType = ContentType.INT;
        } else if (typeArg.equals("-s")) {
            contentType = ContentType.STRING;
        } else {
            System.out.println("Неверное значение содержимого файла!");
            Main.printHelp();
            return;
        }

        boolean ascendingSort;
        //noinspection IfCanBeSwitch
        if (sortOrderArg.equals("-a")) {
            ascendingSort = true;
        } else if (sortOrderArg.equals("-d")) {
            ascendingSort = false;
        } else {
            System.out.println("Неверное значение порядка сортировки!");
            Main.printHelp();
            return;
        }

        try {
            Main.sortFile(in, out, contentType, ascendingSort);
            System.out.println("Done!");
        } catch (SortException e) {
            System.out.printf("Ошибка: %s", e.getMessage());
        }
    }
}
