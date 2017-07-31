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

        Vector[] arrayVectorsE = {
                new Vector(new double[]{1, -2, 3}),
                new Vector(new double[]{4, 0, 6}),
                new Vector(new double[]{-7, 8, 9}),
        };

        Vector[] arrayVectorsF = {
                new Vector(new double[]{11, -3}),
                new Vector(new double[]{-15, -2}),
        };

        Vector[] arrayVectorsG = {
                new Vector(new double[]{1, 2, 3, 4, 6}),
                new Vector(new double[]{4, 5, 6, 43, 75}),
                new Vector(new double[]{12, -54, -3, 0, 6}),
                new Vector(new double[]{7, -2, 3, 41, 34}),
                new Vector(new double[]{0, 2, -3, 10, 9}),
        };


        Matrix matrixA = new Matrix(arrayVectorsA);
        Matrix matrixB = new Matrix(arrayVectorsB);
        Matrix matrixC = new Matrix(arrayVectorsC);
        Matrix matrixD = new Matrix(arrayVectorsA);
        Matrix matrixE = new Matrix(arrayVectorsE);
        Matrix matrixF = new Matrix(arrayVectorsF);
        Matrix matrixG = new Matrix(arrayVectorsG);

        System.out.printf("Матрица А:%n%s%n", matrixA);
        System.out.printf("Матрица B:%n%s%n", matrixB);
        System.out.printf("Матрица C:%n%s%n", matrixC);
        System.out.printf("Матрица D:%n%s%n", matrixD);
        System.out.println();
        System.out.printf("Вектор-столбец матрицы А:%n%s%n", matrixA.getColumn(0));
        System.out.println();
        System.out.printf("A*B:%n%s%n", Matrix.multiply(matrixA, matrixB));
        System.out.printf("A+C(static):%n%s%n", Matrix.addMatrix(matrixA, matrixC));
        System.out.printf("A-C(static):%n%s%n", Matrix.subtractMatrix(matrixA, matrixC));
        System.out.printf("A+C:%n%s%n", matrixA.addMatrix(matrixC));
        System.out.printf("A-C:%n%s%n", matrixA.subtractMatrix(matrixC));
        System.out.printf("A*number:%n%s%n", matrixA.multiply(5));
        System.out.printf("Дополнительный минор матрицы D М22: %n%s%n", matrixD.getСomplementaryMinor(2, 2));
        System.out.printf("Элемент а(1 1) матрицы F = %.2f%n", matrixF.getMatrixElement(1, 1));
        System.out.printf("Определитель матрицы F = %.2f%n", matrixF.getMatrixDeterminantWithDecomposition());
        System.out.printf("Определитель матрицы Е = %.2f%n", matrixE.getMatrixDeterminantWithDecomposition());
        System.out.printf("Определитель матрицы G = %.2f%n", matrixG.getMatrixDeterminantWithDecomposition());
    }
}
