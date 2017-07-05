package ru.academits.erinary.shapes;

import java.util.Arrays;

public class Main {
    private static void bubbleSort(Shape[] array) {
        for (int j = array.length; j > 0; --j) {
            int swapCounter = 0;
            for (int i = 1; i < j; ++i) {
                if (array[i - 1].getArea() > array[i].getArea()) {
                    Shape temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    ++swapCounter;
                }
            }
            if (swapCounter == 0) {
                return;
            }
        }
    }

    private static Shape getShapeByAreaRating(Shape[] shapes, int rating) {
        Shape[] array = new Shape[shapes.length];
        System.arraycopy(shapes, 0, array, 0, shapes.length);
        bubbleSort(array);
        return array[array.length - rating];
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        return getShapeByAreaRating(shapes, 1);
    }

    public static Shape getShapeWithSecondMaxArea(Shape[] shapes) {
        return getShapeByAreaRating(shapes, 2);
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new Square(2),
                new Square(3),
                new Triangle(0, 0, 0, 1, 1, 0),
                new Circle(2),
                new Rectangle(2, 3)};

    }
}
