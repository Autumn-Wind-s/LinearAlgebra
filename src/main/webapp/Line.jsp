<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/4
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <link href="css/layoutit.css" rel="stylesheet">

    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script><script>

</script>
</head>
<body style="width: 80%;margin-left: 100px;height: 83%">
<div class="container-fluid" style="margin-top: -40px">
    <div class="row-fluid">
        <div class="span12">
            <ul class="nav nav-tabs">
                <li tabindex="0"  >
                    <a id="a1"  href="#">行列式计算</a>
                </li>
                <li tabindex="0">
                    <a id="a2" href="#">矩阵初等计算</a>
                </li>
                <li tabindex="0">
                    <a id="a3" href="#">矩阵高等计算</a>
                </li>
                <li  tabindex="0">
                    <a id="a4" href="#">解线性方程组</a>
                </li>
                <li  tabindex="0">
                    <a id="a5" href="#">线性判断</a>
                </li>

            </ul>
        </div>
    </div>
</div>
<iframe id="frame" style="height: 104%;width: 100%" ></iframe>
<%--<textarea id="a"></textarea>--%>
<%--<button id="c">提交</button>--%>
<%--<textarea id="b"></textarea>--%>
</body>
<script>  $(function (){
    $("#a1").click(function (){
        document.getElementById("frame").src="Line/Determinant.jsp"
    })
    $("#a2").click(function (){
        document.getElementById("frame").src="Line/ElementaryMatrix.jsp"
    })
    $("#a3").click(function (){
        document.getElementById("frame").src="Line/HigherMatrix.jsp"
    })
    $("#a4").click(function (){
        document.getElementById("frame").src="Line/Equation.jsp"
    })
    $("#a5").click(function (){
        document.getElementById("frame").src="Line/VectorGroup.jsp"
    })
$("#a1").click()


})</script>
</html>
