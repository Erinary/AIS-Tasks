package ru.academits.erinary.main;

import ru.academits.erinary.sort.ContentType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static void sortFile(String in, String out, ContentType contentType, boolean ascendingSort) {

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
        if (sortOrderArg.equals("-a")) {
            ascendingSort = true;
        } else if (sortOrderArg.equals("-d")) {
            ascendingSort = false;
        } else {
            System.out.println("Неверное значение порядка сортировки!");
            Main.printHelp();
            return;
        }

        Main.sortFile(in, out, contentType, ascendingSort);

    }
}
