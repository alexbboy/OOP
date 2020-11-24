package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размер матрицы:");
        int m = in.nextInt(), n = in.nextInt();
        System.out.println("Введите номер операции:" + "\n"
                + "1) вывод элемента" + "\n"
                + "2) изменение элемента" + "\n"
                + "3) вывод резмерностей" + "\n"
                + "4) сложение матриц" + "\n"
                + "5) вычитание матриц" + "\n"
                + "6) умножение матриц" + "\n"
                + "7) умножение на скаляр" + "\n"
                + "8) вычисление определителя" + "\n"
                + "9) сравнение матриц" + "\n"
                + "10) вывод матрицы" + "\n");
        int var = in.nextInt();

        Matrix matrixA = new Matrix(m, n);
        Matrix matrixB = new Matrix(m, n);
        Matrix matrixC = new Matrix(m, n);

        System.out.println("Введите первую матрицу:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int inp = in.nextInt();
                matrixA.put(i, j, inp);
            }
        }

        System.out.println("Введите вторую матрицу:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int inp = in.nextInt();
                matrixB.put(i, j, inp);
            }
        }

        int i, j, value;

        switch (var) {
            case (1) -> {
                System.out.println("Введите номер элемента:");
                i = in.nextInt();
                j = in.nextInt();
                System.out.println("Элеменет номер [" + i + "][" + j + "] равен " + matrixA.get(i, j));
            }
            case (2) -> {
                System.out.println("Введите номер элемента:");
                i = in.nextInt();
                j = in.nextInt();
                System.out.println("Введите новое значение:");
                value = in.nextInt();
                matrixA.put(i, j, value);
            }
            case (3) -> System.out.println("размерность матрицы:" + matrixA.getM() + " на " + matrixA.getN());
            case (4) -> matrixA.sum(matrixB);
            case (5) -> matrixA.dif(matrixB);
            case (6) -> matrixC.mul(matrixB);
            case (7) -> {
                value = in.nextInt();
                for (i = 1; i < m; i++) {
                    for (j = 1; i < n; i++) {
                        matrixA.mulsk(i, j, value);
                    }
                }
            }
            case (8) -> matrixA.det(0);
            case (9) -> matrixA.equals(matrixB);
            case (10) -> {
                System.out.println("Матрица A:" + "\n" + matrixA.toString());
                System.out.println("Матрица B:" + "\n" + matrixB.toString());
                System.out.println("Матрица C:" + "\n" + matrixC.toString());
            }
        }
    }
}

class Matrix {
    private int[][] matrixTable;
    private int m, n;

    public Matrix(int m, int n) {
        if (m < 0 || n < 0) {
            throw new IllegalArgumentException("Недопустимый размер матрицы");
        }
        this.matrixTable = new int[m][n];
        this.m = m;
        this.n = n;
    }

    public int getM() { return m; }
    public int getN() { return n; }

    public int get(int i, int j) {
        if (i < 0 || j < 0 || i > m || j > n) {
            throw new IllegalArgumentException("Недопустимый индекс элемента");
        }
        return this.matrixTable[i][j];
    }

    public void put(int i, int j, int value) {
        if (i < 0 || j < 0 || i > m || j > n) {
            throw new IllegalArgumentException("Недопустимый индекс элемента");
        }
        this.matrixTable[i][j] = value;
    }

    public Matrix sum(Matrix b) {
        Matrix c = new Matrix(m, n);
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                c.put(i, j, this.get(i, j) + b.get(i, j));
            }
        }
        return c;
    }

    public Matrix dif(Matrix b) {
        Matrix c = new Matrix(m, n);
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                c.put(i, j, this.get(i, j) - b.get(i, j));
            }
        }
        return c;
    }

    public Matrix mul(Matrix b) {
        int value = 0;
        Matrix c = new Matrix(m, n);
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                for (int k = 0; k < n; k++)
                {
                    value += this.get(i, j) * b.get(i, j);
                }
                c.put(i, j, value);
            }
        }
        return c;
    }

    public void mulsk(int i, int j, int value) {
        if (i < 0 || j < 0 || i > m || j > n) {
            throw new IllegalArgumentException("Недопустимый индекс элемента");
        }
        this.matrixTable[i][j] *= value;
    }

    public int det(int j) {
        if (m != n) {
            throw new IllegalArgumentException("Недопустимая матрица");
        }
        int det = 0;
        if (n == 1)
            det = this.matrixTable[0][0];
        if (n == 2)
            det = this.matrixTable[0][0] * this.matrixTable[1][1] - this.matrixTable[1][0] * this.matrixTable[0][1];
        if (n > 2)
        {
            for (int i = j; i < n; i++)
            {
                det += this.matrixTable[1][i] * det(j + 1);;
            }
        }
        return det;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                result += this.matrixTable[i][j];
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object b) {
        if (b == null || !(b instanceof Matrix)) {
            return false;
        }
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (this != b)
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String strMatrix = "";
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                strMatrix += this.matrixTable[i][j] + " ";
            }
            strMatrix += "\n";
        }
        return strMatrix;
    }
}