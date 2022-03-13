package it.controller;

import it.domain.ResultInfo;
import it.utils.LineUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("line")
@Controller("lineController")
public class LineController {
    //行列式求值
    @RequestMapping("det")
    @ResponseBody
    public ResultInfo determinant(String det, ResultInfo rs) {

        try {
            double[][] doubles = LineUtils.invertDouble(det);
            rs.setFlag(true);
            rs.setData(LineUtils.determinant(doubles));
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，请输入规范的行列式1");
        }
        return rs;

    }

    //矩阵相加
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(String a, String b, ResultInfo rs) {
        try {
            double[][] A = LineUtils.invertDouble(a);
            double[][] B = LineUtils.invertDouble(b);
            double[][] add = LineUtils.add(A, B);
            String s = LineUtils.invertString(add);
            rs.setFlag(true);
            rs.setData(s);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，请重新输入并确保两个相加矩阵维度相同！");
        }
        return rs;
    }

    //矩阵相乘
    @RequestMapping("mul")
    @ResponseBody
    public ResultInfo multiply(String a, String b, ResultInfo rs) {
        try {
            double[][] A = LineUtils.invertDouble(a);
            double[][] B = LineUtils.invertDouble(b);
            double[][] multiplication = LineUtils.multiplication(A, B);
            String s = LineUtils.invertString(multiplication);
            rs.setFlag(true);
            rs.setData(s);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，请重新输入并确保矩阵a的列数与矩阵b的行数相等！");
        }
        return rs;
    }

    //矩阵转置
    @RequestMapping("tran")
    @ResponseBody
    public ResultInfo transpose(String a, ResultInfo rs) {
        try {
            double[][] doubles = LineUtils.invertDouble(a);
            double[][] transpose = LineUtils.transpose(doubles);
            String s = LineUtils.invertString(transpose);
            rs.setFlag(true);
            rs.setData(s);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，请输入正确格式的矩阵！");
        }
        return rs;
    }

    //逆矩阵
    @RequestMapping("inverse")
    @ResponseBody
    public ResultInfo inverse(String a, ResultInfo rs) {
        try {
            double[][] doubles = LineUtils.invertDouble(a);
            double[][] inverse = LineUtils.inverse(doubles);
            String s = LineUtils.invertString(inverse);
            rs.setFlag(true);
            rs.setData(s);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，该矩阵为奇异矩阵，不存在逆矩阵！");
        }
        return rs;
    }

    //求矩阵秩
    @RequestMapping("rank")
    @ResponseBody
    public ResultInfo rank(String a, ResultInfo rs) {
        try {
            double[][] doubles = LineUtils.invertDouble(a);
            int rank = LineUtils.rank(doubles);
            rs.setFlag(true);
            rs.setData(rank);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，请输入正确格式的矩阵！");
        }
        return rs;
    }

    //求矩阵特征值
    @RequestMapping("eigenvalue")
    @ResponseBody
    public ResultInfo eigenvalue(String a, ResultInfo rs) {
        try {
            double[][] doubles = LineUtils.invertDouble(a);
            double[] eigenvalue = LineUtils.eigenvalue(doubles);
            rs.setFlag(true);
            rs.setData(eigenvalue);
        } catch (Exception e) {
            rs.setErrorMessage("计算失败，该矩阵不为方阵或不存在实特征值！");
        }
        return rs;
    }

    //判断两矩阵是否相似
    @RequestMapping("similar")
    @ResponseBody
    public ResultInfo similar(String a, String b, ResultInfo rs) {
        try {
            double[][] A = LineUtils.invertDouble(a);
            double[][] B = LineUtils.invertDouble(b);
            boolean similar = LineUtils.similar(A, B);
            rs.setFlag(true);
            rs.setData(similar);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，请输入正确格式的矩阵！");
        }
        return rs;
    }

    //解线性方程组
    @RequestMapping("solve")
    @ResponseBody
    public ResultInfo solve(String a, String b, ResultInfo rs) {
        try {
            double[][] A = LineUtils.invertDouble(a);
            double[] B = LineUtils.toDouble(b);
            double[] solve = LineUtils.solve(A, B);
            rs.setFlag(true);
            rs.setData(solve);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("计算失败，请输入正确格式的矩阵a和矩阵b！");
        }
        return rs;
    }
    //判断线性相关
    @RequestMapping("linear")
    @ResponseBody
    public ResultInfo linear(String a, ResultInfo rs) {
        try {
            double[][] A = LineUtils.invertDouble(a);
            boolean linear = LineUtils.linear(A);
            rs.setFlag(true);
            rs.setData(linear);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("判断失败，请按规定格式正确输入向量组！");
        }
        return rs;
    }

}
