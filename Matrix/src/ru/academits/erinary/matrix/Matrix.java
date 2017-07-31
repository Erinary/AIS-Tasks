package ru.academits.erinary.matrix;


import ru.academits.erinary.vector.Vector;

public class Matrix {
    private Vector[] rows;

    /**
     * Матрица нулей размера height*width
     *
     * @param height высота матрица
     * @param width  ширина матрицы
     * @throws MatrixException попытка создания матрицы с нулевыми размерами
     */
    public Matrix(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new MatrixException("Нельзя просто взять и создать пустой матрицу");
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
     * @throws MatrixException попытка создания матрицы из массива векторов длины 0
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
     * @throws MatrixException попытка создания матрицы из двумерного массива длины 0
     */
    public Matrix(double[][] array) {
        this(Matrix.transformArrayToVectorRows(array));
    }

    //Методы

    /**
     * Преобразует двумерный массив чисел в масив векторов; первый индекс массива - индекс вектора в массиве, второй -
     * индекс компонента вектора
     *
     * @param array передаваемый массив чисел
     * @return массив векторов
     */
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
     * Транспонирование матрицы, изменяет текущий экземпляр
     */
    public void transpose() {
        Vector[] vectorRows = new Vector[this.getWidth()];
        for (int i = 0; i < vectorRows.length; ++i) {
            vectorRows[i] = this.getColumn(i);
        }
        this.rows = vectorRows;
    }

    /**
     * Нестатическое сложение матриц, изменяется текущая матрица
     *
     * @param matrix прибавляемая матрица
     * @throws MatrixException несовпадение размеров складываемых матриц
     */
    public Matrix addMatrix(Matrix matrix) {
        if (this.getWidth() != matrix.getWidth() || this.getHeight() != matrix.getHeight()) {
            throw new MatrixException("Матрицы разного размера");
        }
        for (int i = 0; i < this.getHeight(); ++i) {
            this.rows[i].addVector(matrix.rows[i]);
        }
        return this;
    }

    /**
     * Нестатическое вычитание матриц, изменяется текущая матрица
     *
     * @param matrix вычитаемая матрица
     * @throws MatrixException несовпадение размеров уменьшаемой и вычитаемой матриц
     */
    public Matrix subtractMatrix(Matrix matrix) {
        if (this.getWidth() != matrix.getWidth() || this.getHeight() != matrix.getHeight()) {
            throw new MatrixException("Матрицы разного размера");
        }
        for (int i = 0; i < this.getHeight(); ++i) {
            this.rows[i].subtractVector(matrix.rows[i]);
        }
        return this;
    }

    /**
     * Умножение на скаляр, изменяется текущая матрица
     *
     * @param number множитель
     */
    public Matrix multiply(double number) {
        for (int i = 0; i < this.getHeight(); ++i) {
            this.rows[i].multiply(number);
        }
        return this;
    }

    /**
     * Умножение матрицы на вектор
     *
     * @param vector вектор-множитель
     * @return новый вектор-произведение
     * @throws MatrixException несовпадение ширины матрицы с размерностью вектора
     */
    public Vector multiply(Vector vector) {
        if (this.getWidth() != vector.getSize()) {
            throw new MatrixException("Число столбцов в матрице должно совпадать с числом строк в векторе");
        }
        Vector result = new Vector(this.getHeight());
        for (int i = 0; i < this.getHeight(); ++i) {
            result.setComponent(i, Vector.multiply(this.rows[i], vector));
        }
        return result;
    }

    /**
     * Получение элемента матрицы a(i, j)
     *
     * @param indexRow    индекс строки (i)
     * @param indexColumn индекс сотлбца (j)
     * @return элемент a(i, j)
     */
    public double getMatrixElement(int indexRow, int indexColumn) {
        return this.rows[indexRow].getComponent(indexColumn);
    }

    /**
     * Убирает из матрицы столбец по индексу
     *
     * @param index индекс удаляемого столбца
     */
    private void removeColumn(int index) {
        for (Vector e : this.rows) {
            e.removeComponent(index);
        }
    }


    /**
     * Убирает из матрицы строку по индексу
     *
     * @param index индекс удаляемой строки
     */
    private void removeRow(int index) {
        Vector[] vectorArray = new Vector[this.rows.length - 1];
        System.arraycopy(this.rows, 0, vectorArray, 0, index);
        System.arraycopy(this.rows, index + 1, vectorArray, index, vectorArray.length - index);
        this.rows = vectorArray;
    }

    /**
     * Получение дополнительного минора матрицы к произвольному элементу а(i, j)
     *
     * @param indexRow    индекс вычеркиваемой строки (i)
     * @param indexColumn индекс вычеркиваемого столбца (j)
     * @return дополнительный минор в виде отдельной матрицы
     */
    public Matrix getСomplementaryMinor(int indexRow, int indexColumn) {
        Matrix result = new Matrix(this);
        result.removeRow(indexRow);
        result.removeColumn(indexColumn);
        return result;
    }

    /**
     * Вычисление детерминанта матрицы разложением по первому столбцу
     *
     * @return детерминант матрицы
     * @throws MatrixException если текущая матрица не квадратная
     */
    public double getMatrixDeterminantWithDecomposition() {
        if (this.getHeight() != this.getWidth()) {
            throw new MatrixException("Матрица должна быть квадратной!");
        }
        if (this.getHeight() == 1) {
            return this.getMatrixElement(0,0);
        }
        if (this.getHeight() == 2) {
            return this.getMatrixElement(0, 0) * this.getMatrixElement(1, 1) -
                    this.getMatrixElement(1, 0) * this.getMatrixElement(0, 1);
        }
        double result = 0;
        for (int i = 0; i < this.getHeight(); ++i) {
            double intermediateDeterminant = this.getСomplementaryMinor(i, 1)
                    .getMatrixDeterminantWithDecomposition();
            result += Math.pow(-1, 1 + i) * this.getMatrixElement(i, 1) * intermediateDeterminant;
        }
        return result;
    }

//    Статические методы

    /**
     * Статическое сложение матриц
     *
     * @param matrixA первая матрица
     * @param matrixB вторая матрица
     * @return матрица-результат сложения
     * @throws MatrixException несовпадение размеров слогаемых матриц
     */
    public static Matrix addMatrix(Matrix matrixA, Matrix matrixB) {
        Matrix result = new Matrix(matrixA);
        return result.addMatrix(matrixB);
    }

    /**
     * Статическое вычитание матриц
     *
     * @param matrixA первая матрица
     * @param matrixB вторая матрица
     * @return матрица-результат вычитания
     * @throws MatrixException несовпадение размеров уменьшаемой и вычитаемой матриц
     */
    public static Matrix subtractMatrix(Matrix matrixA, Matrix matrixB) {
        Matrix result = new Matrix(matrixA);
        return result.subtractMatrix(matrixB);
    }

    /**
     * Умножение матриц, создается новая матрица
     *
     * @param matrixA умножаемая матрица
     * @param matrixB матрица-множитель
     * @return матрица-результат
     * @throws MatrixException несовпадение числа столбцов первой матрицы и числа строк второй
     */
    public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getWidth() != matrixB.getHeight()) {
            throw new MatrixException("Число столбцов одной матрицы должно быть равно числу строк другой");
        }
        Vector[] vectorColumns = new Vector[matrixB.getWidth()];
        for (int i = 0; i < matrixB.getWidth(); ++i) {
            vectorColumns[i] = matrixA.multiply(matrixB.getColumn(i));
        }
        Matrix result = new Matrix(vectorColumns);
        result.transpose();
        return result;
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
