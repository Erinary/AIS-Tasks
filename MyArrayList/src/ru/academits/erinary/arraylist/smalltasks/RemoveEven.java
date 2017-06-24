package ru.academits.erinary.arraylist.smalltasks;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveEven {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 5));
        for (int i = 0; i < numbers.size(); ++i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                --i;
            }
        }
        System.out.println(numbers);
    }
}
