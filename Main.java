package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размер матрицы: ");
        int m = in.nextInt(), n = in.nextInt();
        System.out.println("\n");
        System.out.println("Введите номер операции:" + "\n"
                + "1) вывод элемента" + "\n"
                + "2) изменение элемента" + "\n"
                + "3) вывод резмерностей" + "\n"
                + "4) сложение матриц" + "\n"
                + "5) вычитание матриц" + "\n"
                + "6) умножение на скаляр" + "\n"
                + "7) вычисление определителя (только для матриц 2x2)" + "\n"
                + "8) сравнение матриц" + "\n");
        int var = in.nextInt();
        Matrix matrixA = new Matrix(m, n);
        Matrix matrixB = new Matrix(m, n);
        Matrix matrixC = new Matrix(m, n);
        System.out.println("Введите первую матрицу:" + "\n");
        for (int i = 1; i < m; i++) {
            for (int j = 1; i < n; i++) {
                int inp = in.nextInt();
                matrixA.put(i, j, inp);
            }
        }
        System.out.println("\n");
        System.out.println("Введите вторую матрицу:" + "\n");
        for (int i = 1; i < m; i++) {
            for (int j = 1; i < n; i++) {
                int inp = in.nextInt();
                matrixB.put(i, j, inp);
            }
        }
        System.out.println("\n");

        int i, j, out, inp, num1, num2, sum, dif, sc, kl;

        switch (var) {
            case (1):
                System.out.println("Введите номер элемента:" + "\n");
                i = in.nextInt();
                j = in.nextInt();
                System.out.println("\n");
                out = matrixA.get(i, j);
                System.out.println("Элемент номер [" + i + "][" + j + "] равен: " + out + "\n");
                break;
            case (2):
                System.out.println("Введите номер элемента:" + "\n");
                i = in.nextInt();
                j = in.nextInt();
                System.out.println("\n");
                System.out.println("Введите новое значение:" + "\n");
                inp = in.nextInt();
                System.out.println("\n");
                matrixA.put(i, j, inp);
                System.out.println("Элемент изменён" + "\n");
                break;
            case (3):
                System.out.println("Размерность матрицы:" + m + " " + n);
                break;
            case (4):
                for (i = 1; i < m; i++) {
                    for (j = 1; i < n; i++) {
                        num1 = matrixA.get(i, j);
                        num2 = matrixB.get(i, j);
                        sum = num1 + num2;
                        matrixC.put(i, j, sum);
                    }
                }
                break;
            case (5):
                for (i = 1; i < m; i++) {
                    for (j = 1; i < n; i++) {
                        num1 = matrixA.get(i, j);
                        num2 = matrixB.get(i, j);
                        dif = num1 - num2;
                        matrixC.put(i, j, dif);
                    }
                }
                break;
            case (6):
                System.out.println("Введите скалярную величину:" + "\n");
                sc = in.nextInt();
                for (i = 1; i < m; i++) {
                    for (j = 1; i < n; i++) {
                        num1 = matrixA.get(i, j);
                        num1 *= sc;
                        matrixC.put(i, j, num1);
                    }
                }
                break;
            case (7):
                if(n != m && n!=2) {
                    System.out.println("Неправильная матрица:" + "\n");
                }
                else {
                    num1 = matrixA.get(1, 1);
                    num2 = matrixA.get(2, 2);
                    sc = matrixA.get(2, 1);
                    kl = matrixA.get(1, 2);
                    out = (num1 * num2) - (sc * kl);
                }
                break;
            case (8):
                kl = 0;
                for (i = 1; i < m; i++) {
                    for (j = 1; i < n; i++) {
                        num1 = matrixA.get(i, j);
                        num2 = matrixB.get(i, j);
                        if (num1 != num2)
                        {
                            kl++;
                        }
                    }
                }
                System.out.println("Количество раличных значений:" + kl + "\n");
                break;
        }
    }
}

class Matrix {
    private int[][] matrixTable;

    public Matrix(int m, int n) {
        this.matrixTable = new int[m][n];
    }

    public int get(int i, int j) {
        return this.matrixTable[i][j];
    }

    public void put(int i, int j, int value) {
        this.matrixTable[i][j] = value;
    }
}