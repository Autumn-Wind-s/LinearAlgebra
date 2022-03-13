import Jama.Matrix;
import cn.hutool.core.date.DateTime;
import it.mapper.AlgorithmMapper;
import it.utils.LineUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tes {
    @Test
    public void test1(){
        double[][] b = {{-2, 1, 1}, {0, 2, 0}, {-4, 1, 3}};

        String tes = LineUtils.invertString(b);
        System.out.println(tes);
        double[][] k = LineUtils.invertDouble(tes);

        for (int x = 0; x < k.length; x++) {
            for (int y = 0; y < k[x].length; y++) {
                System.out.print(k[x][y] + " ");
            }
            System.out.println();
        }
    }
    @Test
    public void  test2(){
        double[][] a = {{2, -1, -1}, {2, 2, 0}, {0, 1, 3}};
        double[][] b = {{-2, 1, 1}, {0, 2, 0}, {-4, 1, 3}};
        double[][] k = LineUtils.add(a, b);
        for (int x = 0; x < k.length; x++) {
            for (int y = 0; y < k[x].length; y++) {
                System.out.print(k[x][y] + " ");
            }
            System.out.println();
        }
    }
    @Test
    public void test3(){
//行列式求值
        double[][] A = {
                {7, 8},
                {4, 5},

        };
        double determinant = LineUtils.rank(A);
       System.out.println(determinant);
        double[] es = LineUtils.eigenvalue(A);
        for (int i=0;i<es.length;i++){
            System.out.println(es[i]);
        }
        double[][] arr = new double[0][];
        try {
            arr = LineUtils.inverse(A);
        } catch (Exception e) {
            System.out.println("qiyi");
        }
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                System.out.print(arr[x][y] + " ");
            }
            System.out.println();
        }
    }
    @Test
    public void test4(){
        double[][] A = {
                {4, 0},
                {0, 4},

        };
        double[][] B = {
                {4, 1},
                {0, 4},
                {0,0}


        };
        System.out.println(LineUtils.rank(B));
        System.out.println(B[0].length);
        boolean linear = LineUtils.linear(B);
        //        boolean similar = LineUtils.similar(A, B);
        System.out.println(linear);
    }
    @Test
    public void test5(){
        double[][] a = {{1, 2, 2, 1}, {2, 1, -2, -2}, {1, -1, -4, -3}};
        double[] b = {0, 0, 0};
        double[] solve = LineUtils.solve(a, b);
        for (int i=0;i<solve.length;i++)
        {
            System.out.println(solve[i]);
        }
        System.out.println(solve);
    }
    @Test
    public void test6() {


//        System.out.println(a);
//        double[] doubles = LineUtils.toDouble(a);
//        System.out.println(doubles.length);
//        for (int i=0;i<doubles.length;i++){
//            System.out.println(doubles[i]);
//        }
    }
    @Test
    public void test7() throws ParseException {

//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
//        long todayZero = dayFormat.parse(dayFormat.format(new Date())).getTime();
        Date date = new Date();
        System.out.println(date);
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        System.out.println(timestamp);
    }
}
