package ru.academits.erinary.matrix;


import ru.academits.erinary.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private int height;
    private int width;
    private Vector[] rows;

    /**
     * Матрица нулей размера height*width
     * @param height высота матрица
     * @param width ширина матрицы
     */
    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.rows = new Vector[height];
        for (int i = 0; i < height; ++i) {
            this.rows[i] = new Vector(width);
        }
    }

    /**
     * Конструктор копирования
     * @param matrix копируемая матрица
     */
    public Matrix(Matrix matrix) {
        this(matrix.height, matrix.width);
        for (int i = 0; i < this.height; ++i) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    /**
     * Создание матрицы из массива векторов-строк
     * @param vectorRows массив векторов-строк
     */
    public Matrix(Vector[] vectorRows) {
        this.height = vectorRows.length;
        this.rows = new Vector[height];
        int maxWidth = 0;
        for (Vector e : vectorRows) {
            maxWidth = (maxWidth < e.getSize()) ? e.getSize() : maxWidth;
        }
        this.width = maxWidth;

        for (int i = 0; i < this.height; ++i) {
            this.rows[i] = new Vector(maxWidth, vectorRows[i]);
        }
    }

    /**
     * Создание матрицы из двумерного массива
     * @param array массив строк
     */
    public Matrix(double[][] array) {
        this.height = array.length;
        this.rows = new Vector[height];
        int maxWidth = 0;
        for (double[] e : array) {
            maxWidth = (maxWidth < e.length) ? e.length : maxWidth;
        }
        this.width = maxWidth;
        for (int i = 0; i < this.height; ++i) {
            this.rows[i] = new Vector(maxWidth, array[i]);
        }
    }

}
