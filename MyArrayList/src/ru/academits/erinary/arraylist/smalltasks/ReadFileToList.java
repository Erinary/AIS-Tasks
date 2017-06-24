package ru.academits.erinary.arraylist.smalltasks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileToList {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            ArrayList<String> letters = new ArrayList<>(10);
            while (scanner.hasNext()) {
                String fileString = scanner.next();
                letters.add(fileString);
                System.out.println(fileString);
            }
            System.out.println(letters);
        }
    }
}
