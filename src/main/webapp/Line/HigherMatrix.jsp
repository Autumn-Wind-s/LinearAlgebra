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
                          style=" width: 100px; height: 40px;font-size: 30px;padding-top: 20px">矩阵a：</span>
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
                    <button id="sub1" style=" height: 53px; width: 73px; margin-left: 44px;margin-top: -76px;">矩阵转置</button>
                    <button id="sub2" style=" height: 53px; width: 73px;margin-left: 44px;">矩阵求逆</button>
                    <button id="sub3" style=" height: 53px; width: 73px;margin-left: 44px;margin-top: 20px">矩阵求秩</button>
                    <button id="sub4" style=" height: 53px; width:103px;margin-left: 29px;margin-top: 24px">矩阵求特征值</button>
                </div>
                <div class="span5" style="">
                    <textarea readonly id="b" style="resize: none; width: 460px; height: 308px;"></textarea>
                </div>
            </div>
            <div class="alert" style="margin-top: 0px">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    提示!
                </h4> 请注意输入矩阵时一行元素以空格隔开，每行之间要换行，否则无法计算！
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
                url: "../line/tran",
                data: {
                    a: $("#a").val(),
                },
                success: function (data) {
                    if (data.flag) {
                        $("#b").val(data.data)
                    } else {
                        alert(data.errorMessage)
                    }
                }
            })
        }


    })
    $("#sub2").click(function () {

        var line = $("#a").val();
        if (line == "") {

            alert("请输入矩阵！")
        } else {
            $.ajax({
                type: "get",
                url: "../line/inverse",
                data: {
                    a: $("#a").val(),
                },
                success: function (data) {
                    if (data.flag) {
                        $("#b").val(data.data)
                    } else {
                        alert(data.errorMessage)
                    }
                }
            })
        }


    })
    $("#sub3").click(function () {

        var line = $("#a").val();
        if (line == "") {

            alert("请输入矩阵！")
        } else {
            $.ajax({
                type: "get",
                url: "../line/rank",
                data: {
                    a: $("#a").val(),
                },
                success: function (data) {
                    if (data.flag) {

                        $("#b").val("该矩阵的秩为："+data.data)
                    } else {
                        alert(data.errorMessage)
                    }
                }
            })
        }


    })
    $("#sub4").click(function () {

        var line = $("#a").val();
        if (line == "") {

            alert("请输入矩阵！")
        } else {
            $.ajax({
                type: "get",
                url: "../line/eigenvalue",
                data: {
                    a: $("#a").val(),
                },
                success: function (data) {
                    if (data.flag) {
                    var a="";
                    for (var i=0;i<data.data.length;i++)
                    {
                        a +=data.data[i]
                        if ((i+1)!=data.data.length){
                            a+="，"
                        }
                    }
                        $("#b").val("该矩阵共有"+data.data.length+"个特征值，分别为："+a)
                    } else {
                        alert(data.errorMessage)
                    }
                }
            })
        }


    })

</script>
</html>
