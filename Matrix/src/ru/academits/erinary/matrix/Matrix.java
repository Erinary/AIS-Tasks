package ru.academits.erinary.matrix;


import ru.academits.erinary.vector.Vector;

public class Matrix {
    private final int height;
    private final int width;
    private final Vector[] rows;

    /**
     * Матрица нулей размера height*width
     *
     * @param height высота матрица
     * @param width  ширина матрицы
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
     *
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
     *
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
     *
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

    //Методы

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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
        vector = (this.width > vector.getSize()) ? new Vector(this.width, vector) : vector;
        for (int i = 0; i < this.width; ++i) {
            this.rows[index].setComponent(i, vector.getComponent(i));
        }
    }

    /**
     * Получение вектора-столбца по индексу
     *
     * @param index индекс столбца матрицы
     * @return новый ветктор со значениями столбца матрицы
     */
    public Vector getColumn(int index) {
        Vector column = new Vector(this.height);
        for (int i = 0; i < this.height; ++i) {
            column.setComponent(i, this.rows[i].getComponent(index));
        }
        return column;
    }

    /**
     * Транспонирование матрицы с созданием новой
     * @return транспонированная копия текущей матрицы
     */
    public Matrix transpose() {
        @SuppressWarnings("SuspiciousNameCombination") Matrix newMatrix = new Matrix(this.width, this.height);
        for (int i = 0; i < this.width; ++i) {
            newMatrix.setRow(i, this.getColumn(i));
        }
        return newMatrix;
    }

    @Override
    public String toString() {
        String[] vectorStrings = new String[this.height];
        for (int i = 0; i < height; ++i) {
            vectorStrings[i] = this.rows[i].toString();
        }
        return String.join("\n", vectorStrings);

    }
}
