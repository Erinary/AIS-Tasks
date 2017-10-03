package ru.academits.erinary.myarraylist.main;


import ru.academits.erinary.myarraylist.myarraylist.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> testString = new MyArrayList<>();
        testString.add("A");
        testString.add("A");
        testString.add(0, "B");
        System.out.println(testString.size());
        testString.add(3, "B");
        System.out.println(testString.size());
        System.out.println(testString);

        for (String e : testString) {
            System.out.println(e);
        }

        System.out.println(testString);
        System.out.println(testString.indexOf("B"));
        System.out.println(testString.lastIndexOf("C"));

        MyArrayList<String> testList = new MyArrayList<>();
        testList.addAll(Arrays.asList("a", "b", "a", "c", "d", "e", null));
        System.out.println(testList);
        MyArrayList<String> testColl = new MyArrayList<>();
        testColl.addAll(Arrays.asList("a", "b", "e"));
        testList.retainAll(testColl);
        System.out.println(testList);
        System.out.println(testList.size());
        System.out.println(testList.containsAll(testColl));

        MyArrayList<String> listA = new MyArrayList<>(10);
        listA.addAll(Arrays.asList("a", "a", "b", "c", null, "e"));
        listA.remove(5);
        listA.remove(3);
        listA.trimToSize();

        String[] array = new String[10];
        array = testList.toArray(array);
        StringBuilder sb = new StringBuilder();
        for (String e : array) {
            sb.append(e).append(" ");
        }
        System.out.println(sb.toString());

    }
}
