package it.utils;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import java.util.ArrayList;
import java.util.List;

public class LineUtils {
    //二维数组转字符串
    public static String invertString(double[][] a) {
        String b = new String();
        int c = a.length * a[0].length;
        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[x].length; y++) {
                b += a[x][y];
                b += " ";
            }
            b += "\n";
        }
        return b;

    }

    //字符串转一维数组
    public static double[] toDouble(String tes) {
        String[] split = tes.split("\n");
        System.out.println(split.length);
        double[] d = new double[split.length];
        for (int i = 0; i < split.length; i++) {
            d[i] = Double.parseDouble(split[i]);
        }
        return d;
    }

    //字符串转二维数组
    public static double[][] invertDouble(String tes) {
        String[] split = tes.split("\n");

        List<double[]> a = new ArrayList<double[]>();
        int j;
        for (j = 0; j < split.length; j++) {
            String[] s = split[j].split("\\s+");
            double[] e = new double[s.length];
            for (int i = 0; i < s.length; i++) {
                e[i] = Double.parseDouble(s[i]);
            }
            a.add(e);
        }
        double[][] k = new double[a.size()][a.get(0).length];
        for (int i = 0; i < a.size(); i++) {
            double[] doubles = a.get(i);
            for (j = 0; j < a.get(0).length; j++) {
                k[i][j] = doubles[j];
            }
        }
        return k;
    }

    //矩阵相加
    public static double[][] add(double[][] a, double[][] b) {
        Matrix A = new Matrix(a);
        Matrix B = new Matrix(b);
        Matrix plus = A.plus(B);
        return plus.getArray();
    }

    //矩阵相乘
    public static double[][] multiplication(double[][] a, double[][] b) {
        Matrix A = new Matrix(a);
        Matrix B = new Matrix(b);
        Matrix plus = A.times(B);
        return plus.getArray();
    }

    //行列式求值
    public static double determinant(double[][] a) {
        Matrix A = new Matrix(a);
        return A.det();
    }

    //矩阵转置
    public static double[][] transpose(double[][] a) {
        Matrix A = new Matrix(a);
        Matrix transpose = A.transpose();
        return transpose.getArray();
    }

    //求逆矩阵
    public static double[][] inverse(double[][] a) {
        Matrix A = new Matrix(a);
        Matrix inverse = A.inverse();
        return inverse.getArray();
    }

    //求矩阵的秩
    public static int rank(double[][] a) {
        Matrix A = new Matrix(a);
        return A.rank();
    }

    //求矩阵的特征值
    public static double[] eigenvalue(double[][] a) {
        Matrix A = new Matrix(a);
        EigenvalueDecomposition eig = A.eig();
        Matrix d = eig.getD();
        double[][] arr = d.getArray();
        int length = arr.length;
        double[] b = new double[length];
        for (int i = 0; i < length; i++) {
            b[i] = arr[i][i];
        }
        return b;
    }

    //判断两矩阵是否相似
    public static boolean similar(double[][] N, double[][] M) {
        Matrix A = new Matrix(N);
        Matrix B = new Matrix(M);

        if (A.getColumnDimension() == A.getRowDimension() && B.getColumnDimension() == B.getRowDimension() && A.getColumnDimension() == B.getColumnDimension()) {
            int n = A.getColumnDimension();
            if (A.det() == B.det()) {
                if (A.rank() == B.rank()) {
                    int sumA = 0;
                    int sumB = 0;
                    for (int i = 0; i < n; i++) {
                        sumA += A.get(i, i);
                        sumB += B.get(i, i);
                    }
                    if (sumA == sumB) {
                        EigenvalueDecomposition a = A.eig();
                        EigenvalueDecomposition b = B.eig();
                        Matrix d = a.getD();
                        Matrix e = b.getD();
                        for (int j = 0; j < n; j++) {
                            if (d.get(j, j) == e.get(j, j)) {
                            } else {
                                return false;
                            }
                        }
                        return true;
                    } else return false;
                } else return false;
            }
            return false;
        } else return false;

    }

    //解线性方程组
    public static double[] solve(double[][] a, double[] b) {
        double[] solve = LUDecomposition.solve(a, b);
        return solve;
    }

    //向量组的线性判断
    public static boolean linear(double[][] a) {
        Matrix A = new Matrix(a);
        int rank = A.rank();
        if (rank < a[0].length) {
            return false;
        } else return true;

    }
}

