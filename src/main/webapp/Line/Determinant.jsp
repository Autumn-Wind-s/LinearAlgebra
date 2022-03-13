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
                          style=" width: 100px; height: 40px;font-size: 30px;padding-top: 20px">行列式：</span>
                </div>

                <div class="span6">
                    <span class="label" style=" margin-left: 100px;    height: 40px;   font-size: 30px; padding-top: 20px;padding-left: 16px;padding-right: 23px;">结果:</span>
                </div>
            </div>
            <div class="row-fluid" style="height: 70%">
                <div class="span5">
                    <textarea id="a" style="resize: none;width: 460px; height: 308px;"></textarea>
                </div>
                <div class="span2" style=" margin-top: 100px;height: 102px;">
                    <button id="sub" style=" height: 53px; width: 68px;margin-left: 46px;">计算</button>
                </div>
                <div class="span5" style="">
                    <textarea readonly id="b" style="resize: none; width: 460px; height: 308px;"></textarea>
                </div>
            </div>
            <div class="alert"style="margin-top: 0px">
                <button  type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    提示!
                </h4>  请将行列式以矩阵的形式输入，行列式一行元素以空格隔开，每行之间要换行，否则无法计算！
            </div>
        </div>
    </div>
</div>
</body>
<script>

    $("#sub").click(function (){

        var line=$("#a").val();
        if (line==""){

            alert("请输入行列式！")
        }else {
            $.ajax({
                type:"get",
                url:"../line/det",
                data:{
                    det:$("#a").val(),
                },
                success:function (data) {
                    if (data.flag){
                        $("#b").val(data.data)
                    }else {
                        alert(data.errorMessage)
                    }}
            })
        }



    })
</script>
</html>
