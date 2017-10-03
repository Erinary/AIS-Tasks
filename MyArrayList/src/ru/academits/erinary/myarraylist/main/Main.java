package ru.academits.erinary.myarraylist.main;


import ru.academits.erinary.myarraylist.myarraylist.MyArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        for (String e: testString) {
            System.out.println(e);
        }

        System.out.println(testString);
        System.out.println(testString.indexOf("B"));
        System.out.println(testString.lastIndexOf("C"));

        MyArrayList<String> testList = new MyArrayList<>();
        testList.addAll(Arrays.asList("a", "b", "a", "c", "d", "e", null));
        System.out.println(testList);
        MyArrayList<String> testColl = new MyArrayList<>();
        testColl.addAll(Arrays.asList("a", "b", "e", "z"));
        testList.retainAll(testColl);
        System.out.println(testList);
        System.out.println(testList.size());




    }

}
