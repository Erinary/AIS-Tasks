package ru.academits.erinary.main;

import ru.academits.erinary.list.SimpleLinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SimpleLinkedList<String> testStringList = new SimpleLinkedList<>();
        testStringList.insertNode("A", 0);
        testStringList.insertNode("B", 1);

        SimpleLinkedList<Integer> testIntegerList = new SimpleLinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        testIntegerList.addAll(arrayList);

        System.out.println(testStringList);
        System.out.println(testStringList.getNodeData(1));
        System.out.println(testIntegerList);
    }
}
