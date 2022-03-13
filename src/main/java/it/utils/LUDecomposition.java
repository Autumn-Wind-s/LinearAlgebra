package it.utils;

import java.util.Arrays;
import java.util.List;

public class LUDecomposition {
    /**
     * Get solutions of the equations
     * @param a - Coefficient matrix of the equations
     * @param b - right-hand side of the equations
     * @return solution of the equations
     */
    public static double[] solve(double[][] a, double[] b) {
        List<double[][]> LAndU = decomposition(a);  //LU decomposition
        double[][] L = LAndU.get(0);
        double[][] U = LAndU.get(1);
        double[] UMultiX = getUMultiX(a, b, L);
        return getSolution(a, U, UMultiX);
    }

    /**
     * Get solution of the equations
     * @param a - Coefficient matrix of the equations
     * @param U - U of LU Decomposition
     * @param UMultiX - U multiply X
     * @return Equations solution
     */
    private static double[] getSolution(double[][] a, double[][] U,
                                        double[] UMultiX) {
        double[] solutions = new double[a[0].length];
        for(int i=U.length-1; i>=0; i--) {
            double right_hand = UMultiX[i];
            for(int j=U.length-1; j>i; j--) {
                right_hand -= U[i][j] * solutions[j];
            }
            solutions[i] = right_hand / U[i][i];
        }
        return solutions;
    }

    /**
     * Get U multiply X
     * @param a - Coefficient matrix of the equations
     * @param b - right-hand side of the equations
     * @param L - L of LU Decomposition
     * @return U multiply X
     */
    private static double[] getUMultiX(double[][] a, double[] b, double[][] L) {
        double[] UMultiX = new double[a.length];
        for(int i=0; i<a.length; i++) {
            double right_hand = b[i];
            for(int j=0; j<i; j++) {
                right_hand -= L[i][j] * UMultiX[j];
            }
            UMultiX[i] = right_hand / L[i][i];
        }
        return UMultiX;
    }

    /**
     * Get matrix L and U. list.get(0) for L, list.get(1) for U
     * @param a - Coefficient matrix of the equations
     * @return matrix L and U, list.get(0) for L, list.get(1) for U
     */
    private static List<double[][]> decomposition(double[][] a) {
        double[][] U = a;
        double[][] L = createIdentityMatrix(a.length);

        for(int j=0; j<a[0].length - 1; j++) {
            if(a[j][j] == 0) { a[j][j] = 1e-20; }

            for(int i=j+1; i<a.length; i++) {
                double mult = a[i][j] / a[j][j];
                for(int k=j; k<a[i].length; k++) {
                    U[i][k] = a[i][k] - a[j][k] * mult;
                }
                L[i][j] = mult;
            }
        }
        return Arrays.asList(L, U);
    }

    private static double[][] createIdentityMatrix(int row) {
        double[][] identityMatrix = new double[row][row];
        for(int i=0; i<identityMatrix.length; i++) {
            for(int j=i; j<identityMatrix[i].length; j++) {
                if(j == i) {
                    identityMatrix[i][j] = 1;
                } else {
                    identityMatrix[i][j] = 0;
                }
            }
        }
        return identityMatrix;
    }

}
