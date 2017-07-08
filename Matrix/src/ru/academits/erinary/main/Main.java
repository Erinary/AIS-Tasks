package ru.academits.erinary.main;

import ru.academits.erinary.matrix.Matrix;
import ru.academits.erinary.vector.Vector;


public class Main {
    public static void main(String[] args) {
        Vector[] arrayVectorsA = {
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{4, 5, 6}),
        };

        Vector[] arrayVectorsC = {
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{4, 5, 6}),
        };

        Vector[] arrayVectorsB = {
                new Vector(new double[]{1, 2}),
                new Vector(new double[]{3, 4}),
                new Vector(new double[]{5, 6}),
        };

        Matrix matrixA = new Matrix(arrayVectorsA);
        Matrix matrixB = new Matrix(arrayVectorsB);
        Matrix matrixC = new Matrix(arrayVectorsC);
        System.out.printf("Матрица А:%n%s%n", matrixA);
        System.out.printf("Матрица B:%n%s%n", matrixB);
        System.out.printf("Матрица C:%n%s%n", matrixC);
        System.out.println();
        System.out.printf("Вектор-столбец матрицы А:%n%s%n", matrixA.getColumn(0));
        System.out.println();
        System.out.printf("A*B:%n%s%n", Matrix.multiply(matrixA, matrixB));
        System.out.printf("A+C(static):%n%s%n", Matrix.addMatrix(matrixA, matrixC));
        System.out.printf("A-C(static):%n%s%n", Matrix.subtractMatrix(matrixA, matrixC));
        System.out.printf("A+C:%n%s%n", matrixA.addMatrix(matrixC));
        System.out.printf("A-C:%n%s%n", matrixA.subtractMatrix(matrixC));
        System.out.printf("A*number:%n%s%n", matrixA.multiply(5));
    }
}
