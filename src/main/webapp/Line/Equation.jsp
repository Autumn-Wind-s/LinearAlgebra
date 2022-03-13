<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/5
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="../js/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid" style="height: 100%;">
    <div class="row-fluid" style="height: 100%;">
        <div class="span12" style="height: 100%;">
            <%--            <div class="btn-group" style="height: 15%;">--%>
            <%--                <button class="btn" type="button"><em class="icon-align-left"></em></button> <button class="btn" type="button"><em class="icon-align-center"></em></button> <button class="btn" type="button"><em class="icon-align-right"></em></button> <button class="btn" type="button"><em class="icon-align-justify"></em></button>--%>
            <%--            </div>--%>
            <div class="row-fluid" style="height: 82%;margin-top: 10px">
                <div class="span5">
                    <span class="label"
                          style="width: 100%;height: 10%; padding-top: 11px;font-size: 15px;">系数矩阵a:</span>
                    <textarea id="a" style="width: 482px;height: 109px;resize: none;"></textarea>
                    <span class="label" style="width: 100%;height: 10%; padding-top: 11px;font-size: 15px;">右矩阵b:</span>
                    <textarea id="b" style="width: 481px; height: 128px;resize: none;"></textarea>

                </div>
                <div class="span2" style="height: 100%">
                    <button class="btn" type="button"
                            id="sub1"
                            style="margin-left: 61px;margin-top: 88%;width: 78px;height: 47px;">求解
                    </button>

                </div>
                <div class="span5" style="height: 100%;">
                    <span class="label"
                          style=" width: 100%; margin-top: 11%; height: 37px;padding-top: 13px;font-size: 15px;">结果：</span>
                    <textarea readonly id="c" style="width: 481px;height: 224px;resize: none;"> </textarea>
                </div>
            </div>
            <div class="alert " style="margin-top: 11px">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    提示!
                </h4> 请注意将方程组的增广矩阵拆分为系数矩阵a和右矩阵b分别输入,输入时一行元素以空格隔开，每行之间要换行，否则无法计算！
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function check() {
        var v1 = $("#a").val();
        var v2 = $("#a").val();
        if (v1 == "") {
            return false
        } else if (v2 == "") {
            return false
        } else return true;
    }

    $("#sub1").click(function () {
        if (check()) {
            $.ajax({
                type: "get",
                url: "../line/solve",
                data: {
                    a: $("#a").val(),
                    b: $("#b").val(),
                },
                success: function (data) {
                    if (data.flag) {
                        alert("成功")
                        $("#c").val("方程组解为：" + data.data)
                    } else {
                        alert(data.errorMessage)
                    }
                }
            })
        } else alert("请输入矩阵！")
    })


</script>
</html>
