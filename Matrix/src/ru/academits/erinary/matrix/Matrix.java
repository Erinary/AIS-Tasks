package ru.academits.erinary.matrix;


import ru.academits.erinary.vector.Vector;

public class Matrix {
    private Vector[] rows;

    /**
     * Матрица нулей размера height*width
     *
     * @param height высота матрица
     * @param width  ширина матрицы
     */
    public Matrix(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new MatrixException("Нельзя просто взять и создать нулевую матрицу");
        }
        this.rows = new Vector[height];
        for (int i = 0; i < height; ++i) {
            this.rows[i] = new Vector(width);
        }
    }

    /**
     * Конструктор копирования
     *
     * @param matrix копируемая матрица
     */
    public Matrix(Matrix matrix) {
        this(matrix.getHeight(), matrix.getWidth());
        for (int i = 0; i < this.getHeight(); ++i) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    /**
     * Создание матрицы из массива векторов-строк
     *
     * @param vectorRows массив векторов-строк
     */
    public Matrix(Vector[] vectorRows) {
        if (vectorRows.length == 0) {
            throw new MatrixException("Нельзя просто взять и создать нулевую матрицу");
        }
        this.rows = new Vector[vectorRows.length];
        int maxWidth = 0;
        for (Vector e : vectorRows) {
            maxWidth = (maxWidth < e.getSize()) ? e.getSize() : maxWidth;
        }
        if (maxWidth == 0) {
            throw new MatrixException("Нельзя просто взять и создать нулевую матрицу");
        }
        for (int i = 0; i < this.getHeight(); ++i) {
            this.rows[i] = new Vector(maxWidth, vectorRows[i]);
        }
    }

    /**
     * Создание матрицы из двумерного массива
     *
     * @param array массив строк
     */
    public Matrix(double[][] array) {
        this(Matrix.transformArrayToVectorRows(array));
    }

    //Методы

    private static Vector[] transformArrayToVectorRows(double[][] array) {
        Vector[] vectorRows = new Vector[array.length];
        for (int i = 0; i < array.length; ++i) {
            vectorRows[i] = new Vector(array[i]);
        }
        return vectorRows;
    }

    public int getHeight() {
        return this.rows.length;
    }

    public int getWidth() {
        return this.rows[0].getSize();
    }

    /**
     * Получение вектора-строки по индексу
     *
     * @param index индекс
     * @return копия вектора-строки
     */
    public Vector getRow(int index) {
        return new Vector(this.rows[index]);
    }

    /**
     * Задание вектора-строки по индексу; если передаваемый вектор больше размера матрицы, то вектор урезается,
     * если меньше - дополняется 0; в матрицу передается копия вектора
     *
     * @param index  индекс
     * @param vector передаваемый вектор
     */
    public void setRow(int index, Vector vector) {
        Vector normalizedVector = (this.getWidth() > vector.getSize()) ? new Vector(this.getWidth(), vector) : vector;
        for (int i = 0; i < this.getWidth(); ++i) {
            this.rows[index].setComponent(i, normalizedVector.getComponent(i));
        }
    }

    /**
     * Получение вектора-столбца по индексу
     *
     * @param index индекс столбца матрицы
     * @return новый ветктор со значениями столбца матрицы
     */
    public Vector getColumn(int index) {
        Vector column = new Vector(this.getHeight());
        for (int i = 0; i < this.getHeight(); ++i) {
            column.setComponent(i, this.rows[i].getComponent(index));
        }
        return column;
    }

    /**
     * Транспонирование матрицы с созданием новой
     *
     * @return транспонированная копия текущей матрицы
     */
    public Matrix transpose() {
        @SuppressWarnings("SuspiciousNameCombination") Matrix newMatrix = new Matrix(this.getWidth(), this.getHeight());
        for (int i = 0; i < this.getWidth(); ++i) {
            newMatrix.setRow(i, this.getColumn(i));
        }
        return newMatrix;
    }

    @Override
    public String toString() {
        String[] vectorStrings = new String[this.getHeight()];
        for (int i = 0; i < this.getHeight(); ++i) {
            vectorStrings[i] = this.rows[i].toString();
        }
        return String.join(System.lineSeparator(), vectorStrings);

    }
}
