package ru.academits.erinary.arraylist.smalltasks;

import java.util.ArrayList;
import java.util.Arrays;

public class ListWithoutIterativeElements {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> newList = new ArrayList<>();
        for (int e : numbers) {
            if (!newList.contains(e)) {
                newList.add(e);
            }
        }
        System.out.println(numbers);
        System.out.println(newList);
    }
}
