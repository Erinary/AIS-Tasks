package ru.academits.erinary.main;

import ru.academits.erinary.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Shape sortShapeByAreaRating(Shape[] shapes, int rating) {
        Shape[] array = new Shape[shapes.length];
        System.arraycopy(shapes, 0, array, 0, shapes.length);
        Arrays.sort(array, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                if (o1.getArea() > o2.getArea()) {
                    return 1;
                } else if (o1.getArea() < o2.getArea()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return array[array.length - rating];
    }

    private static Shape sortShapeByPerimeterRating(Shape[] shapes, int rating) {
        Shape[] array = new Shape[shapes.length];
        System.arraycopy(shapes, 0, array, 0, shapes.length);
        Arrays.sort(array, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                if (o1.getPerimeter() > o2.getPerimeter()) {
                    return 1;
                } else if (o1.getPerimeter() < o2.getPerimeter()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return array[array.length - rating];
    }

    private static Shape getShapeWithMaxArea(Shape[] shapes) {
        return sortShapeByAreaRating(shapes, 1);
    }

    private static Shape getShapeWithSecondMaxPerimeter(Shape[] shapes) {
        return sortShapeByPerimeterRating(shapes, 2);
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new Square(2),
                new Square(3),
                new Triangle(0, 0, 0, 1, 1, 0),
                new Circle(2),
                new Rectangle(2, 3)};

        System.out.printf("Самая большая фигура по площади: %s", getShapeWithMaxArea(shapes));
        System.out.println();
        System.out.printf("Вторая большая фигура по периметру: %s", getShapeWithSecondMaxPerimeter(shapes));
    }
}
