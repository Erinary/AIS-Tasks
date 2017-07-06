package ru.academits.erinary.main;

import ru.academits.erinary.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static class PerimeterComparator implements Comparator<Shape> {
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
    }

    private static Shape getShapeByRating(Shape[] shapes, int rating, Comparator<Shape> cmp) {
        Shape[] array = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(array, cmp);
        return array[array.length - rating];
    }

    private static Shape getShapeWithMaxArea(Shape[] shapes) {
        Comparator<Shape> cmp = new Comparator<Shape>() {
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
        };
        return getShapeByRating(shapes, 1, cmp);
    }

    private static Shape getShapeWithSecondMaxPerimeter(Shape[] shapes) {
        PerimeterComparator cmp = new PerimeterComparator();
        return getShapeByRating(shapes, 2, cmp);
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
