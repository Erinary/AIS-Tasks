package ru.academits.erinary.myarraylist.main;


import ru.academits.erinary.myarraylist.myarraylist.MyArrayList;
import java.util.ArrayList;

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

    }

}
