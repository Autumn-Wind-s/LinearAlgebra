<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/5
  Time: 15:36
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
<div class="container-fluid" style="height: 100%">
    <div class="row-fluid" style="height: 100%">
        <div class="span12" style="height: 100%">
            <div class="row-fluid" style="height: 15%;">
                <div class="span6">
                    <span class="label"
                          style=" width: 132px; height: 40px;font-size: 30px;padding-top: 20px">向量矩阵：</span>
                </div>

                <div class="span6">
                    <span class="label"
                          style=" margin-left: 100px;    height: 40px;   font-size: 30px; padding-top: 20px;padding-left: 16px;padding-right: 23px;">结果:</span>
                </div>
            </div>
            <div class="row-fluid" style="height: 70%">
                <div class="span5">
                    <textarea id="a" style="resize: none;width: 460px; height: 308px;"></textarea>
                </div>
                <div class="span2" style=" margin-top: 100px;height: 70%;">
                    <button id="sub1" style=" height: 53px; width:119px;margin-left: 29px;margin-top: 24px">判断线性相关性
                    </button>
                </div>
                <div class="span5" style="">
                    <textarea readonly id="b" style="resize: none; width: 460px; height: 308px;"></textarea>
                </div>
            </div>
            <div class="alert" style="margin-top: 0px">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    提示!
                </h4> 请将向量组以矩阵的形式输入，注意输入矩阵时一行元素以空格隔开，每行之间要换行，否则无法计算！
            </div>
        </div>
    </div>
</div>
</body>
<script>

    $("#sub1").click(function () {

        var line = $("#a").val();
        if (line == "") {

            alert("请输入矩阵！")
        } else {
            $.ajax({
                type: "get",
                url: "../line/linear",
                data: {
                    a: $("#a").val(),
                },
                success: function (data) {
                    if (data.flag) {
                        if (data.data) {
                            $("#b").val("该向量组线性相关。")
                        } else {
                            $("#b").val("该向量组线性不相关。")
                        }
                    } else {
                        alert(data.errorMessage)
                    }
                }
            })
        }


    })


</script>
</html>
