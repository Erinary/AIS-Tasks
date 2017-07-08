package ru.academits.erinary.main;

import ru.academits.erinary.matrix.Matrix;
import ru.academits.erinary.vector.Vector;


public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 2};
        double[] array2 = {4, 5};
        double[] array3 = {7, 8};

        Vector[] arrayVectors = {
                new Vector(array1),
                new Vector(array2),
                new Vector(array3)
        };

        Matrix matrix = new Matrix(arrayVectors);
        System.out.println(matrix.getColumn(0));
        System.out.println();
        System.out.println(matrix);
        System.out.println();
        matrix.transpose();
        System.out.println(matrix);
    }
}
