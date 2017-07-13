package ru.academits.erinary.main;

import ru.academits.erinary.sort.ContentType;
import ru.academits.erinary.sort.SortException;
import ru.academits.erinary.sort.SortUtils;

import java.util.Comparator;
import java.util.List;

public class Main {
    private static final int EXPECTED_LINE_NUMBER = 100;

    /**
     * Основной метод чтения файла, сортировки содержимого и записи нового файла.
     *
     * @param inputFileName  имя входного файла
     * @param outputFileName имя выходного файла
     * @param contentType    тип данных файла
     * @param ascendingSort  вид сортировки
     * @throws SortException пробрасывает исключения из используемых методов
     */
    private static void sortFile(String inputFileName, String outputFileName, ContentType contentType,
                                 boolean ascendingSort) throws SortException {
        if (contentType == ContentType.INT) {
            List<String> listString = SortUtils.readStringInput(inputFileName, EXPECTED_LINE_NUMBER);
            List<Integer> outList = SortUtils.parseIntList(listString);
            Comparator<Integer> cmp = (ascendingSort) ? new SortUtils.AscIntComparator() : new SortUtils.AscIntComparator().reversed();
            SortUtils.sortList(outList, cmp);
            SortUtils.writeOutput(outList, outputFileName);
        } else {
            List<String> outList = SortUtils.readStringInput(inputFileName, EXPECTED_LINE_NUMBER);
            Comparator<String> cmp = (ascendingSort) ? new SortUtils.AscStringComparator() : new SortUtils.AscStringComparator().reversed();
            SortUtils.sortList(outList, cmp);
            SortUtils.writeOutput(outList, outputFileName);
        }
    }

    /**
     * Метод печатает справку в консоль.
     */
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

    /**
     * Точка входа, обрабатывает передаваемые в программу аргументы командной строки.
     *
     * @param args аргументы командной строки
     */
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

        String inputFileName = args[0];
        String outputFileName = args[1];
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
            Main.sortFile(inputFileName, outputFileName, contentType, ascendingSort);
            System.out.println("Сделано!");
        } catch (SortException e) {
            System.out.printf("Ошибка: %s", e.getMessage());
        } catch (Exception e) {
            System.out.printf("Непредвиденная ошибка (%s): %s", e.getClass(), e.getMessage());
        }
    }
}
