<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/12
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link href="../css/bootstrap-combined.min.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
    <script src="../js/jquery.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="page-header">
                <button class="btn btn-link" type="button" style="margin-left: -5px; font-size: 18px;margin-top: 0px;"
                        id="bb">返回
                </button>
                <h1 id="h1">
                </h1>

            </div>
            <p class="text-success" id="p1">
            </p>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">

            <p style="text-indent: 2em;font-family: cursive;font-size: 28px;line-height: 40px" id="p2">

            </p>
            <div class="span12" style="width: 95%">
                <form style="margin-left: 0px">
                    <textarea id="te" style="height: 91px;width: 1207px; resize: none " required></textarea>

                    <input type="button" id="sub4" style="margin-top: 12px;margin-left: 0px;" VALUE="评论"> </input>
                </form>
            </div>
            <div class="span12" style="width: 95%">
                <ul class="unstyled" id="u1" style="font-size: 23px">

                </ul>
            </div>
        </div>
    </div>

</div>
</body>
<script>
    function getQu(qid) {
        $.ajax({
            type: "post",
            url: "../question/getByQid",
            data: {
                qid: qid
            },
            success: function (data) {
                var a = '  <strong >' + data.user.username + '</strong>于' + data.uploadTime + '发布。'
                $("#p1").html(a)
                $("#h1").html("" + data.questionName)
                $("#p2").html("" + data.text)

            }

        })


    }

    function getAn(qid) {
        $.ajax({
            type: "post",
            url: "../question/getByQid",
            data: {
                qid: qid
            },
            success: function (data) {
                var  name=data.user.username
                $.ajax({
                    type: "post",
                    url: "../answer/getByQid",
                    data: {
                        qid: qid
                    },
                    success: function (data) {
                        if (data[0] == null) {
                            $("#u1").html("暂无评论，欢迎评论")
                        } else {
                            var list = ""
                            for (var i = 0; i < data.length; i++) {
                                var li;
                                if (data[i].user.username ==name) {
                                     li = ' <li> <address style="line-height: 32px"> <strong style="color: deepskyblue">' + data[i].user.username + '(作者)</strong>&nbsp;&nbsp;&nbsp;&nbsp;' + data[i].publishtime + '<br>' +
                                        data[i].text + '</address> </li>';
                                }else if(data[i].user.permissions=="管理员"){
                                     li = ' <li> <address style="line-height: 32px"> <strong style="color: red">' + data[i].user.username + '(管理员)</strong>&nbsp;&nbsp;&nbsp;&nbsp;' + data[i].publishtime + '<br>' +
                                        data[i].text + '</address> </li>';
                                }else {
                                    li = ' <li> <address style="line-height: 32px"> <strong>' + data[i].user.username + '</strong>&nbsp;&nbsp;&nbsp;&nbsp;' + data[i].publishtime + '<br>' +
                                        data[i].text + '</address> </li>';

                                }
                                list += li
                            }
                            $("#u1").html(list)
                        }
                    }

                })
            }

        })
    }

    function addAn(qid, uid, text) {
        $.ajax({
            type: "post",
            url: "../answer/add",
            data: {
                qid: qid,
                uid: uid,
                text: text
            },
            success: function (data) {
                if (data.flag) {
                    alert("评论成功")
                    $("#te").val("")
                    getAn(${param.get("qid")})
                } else {
                    alert(data.errorMessage)
                }
            }

        })
    }

    $(function () {

        getQu(${param.get("qid")})
        getAn(${param.get("qid")})
        $("#bb").click(function () {
            var f = document.referrer;
            location.href = f
        })
        $("#sub4").click(function () {
            var  a=document.referrer
            if (a=="http://localhost:8080/LinearAlgebra/BbsManage/BbsManage.jsp"){
                if ($("#te").val() == "") {
                    alert("请输入评论的内容！")
                }else
                {
                    addAn("${param.get("qid")}", "${sessionScope.admin.uid}", $("#te").val())
                }

            }else {
                if ("${sessionScope.user}" == "") {
                    alert("您还未登录呢，请登录后再评论")

                } else {
                    if ($("#te").val() == "") {
                        alert("请输入评论的内容！")
                    }else
                    {
                        addAn("${param.get("qid")}", "${sessionScope.user.uid}", $("#te").val())
                    }
                }}
        })

    })
</script>
</html>
