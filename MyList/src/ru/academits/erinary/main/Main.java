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
        testStringList.inverseList();
        System.out.println(testStringList);
        testIntegerList.inverseList();
        System.out.println(testIntegerList);

        SimpleLinkedList<String> copyString = testStringList.copyList();
        System.out.println(copyString);
        SimpleLinkedList<Integer> copyInteger = testIntegerList.copyList();
        System.out.println(copyInteger);

        System.out.println(copyInteger.getNodeData(0));
        System.out.println(copyInteger.getSize());
        System.out.println(copyInteger.setNodeData(4, 9));
        System.out.println(copyInteger.deleteNode(4));
        copyInteger.deleteHead();
        System.out.println(copyInteger);
        copyInteger.insertNode(11, 3);
        System.out.println(copyInteger);
        copyInteger.deleteNodeByData(2);
        System.out.println(copyInteger);
    }
}
