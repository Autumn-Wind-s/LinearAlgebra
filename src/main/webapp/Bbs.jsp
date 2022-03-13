<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/11
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <style>
        .p1 {
            display: inline-block;
            white-space: nowrap;
            width: 100%;
            overflow: hidden;
            text-overflow: ellipsis
        }
    </style>
    <script>
        function getNewQu() {
            $.ajax({
                type: "post",
                url: "question/new",
                date: {},
                success: function (data) {
                    var list = "";
                    for (var i = 0; i < data.length; i++) {
                        var li = '<li style="height: 8%;font-size: 25px;margin-top: 10px;"><a href="Bbs/ShowQuestion.jsp?qid=' + data[i].qid + '">' + data[i].questionName + '</a></li>'
                        list += li;
                    }
                    $("#newQ").html(list)
                }

            })
        }

        function getNewAn() {
            $.ajax({
                type: "post",
                url: "answer/new",
                date: {},
                success: function (data) {
                    var list = "";
                    for (var i = 0; i < data.length; i++) {
                        var li = '<li><a href="Bbs/ShowQuestion.jsp?qid=' + data[i].question.qid + '">' + data[i].question.questionName + '</a>' +
                            ' <p class="p1">' + data[i].user.username + ':' + data[i].text + '</p></li>'
                        list += li
                    }
                    $("#newA").html(list)
                }

            })
        }

        function getAllQu() {
            $.ajax({
                type: "post",
                url: "question/all",
                date: {},
                success: function (data) {
                    $("#s2").html("总发贴数：" + data.length)
                    var list = ""
                    for (var i = 0; i < data.length; i++) {
                        var li = '<tr>' +
                            '<td><a href="Bbs/ShowQuestion.jsp?qid=' + data[i].qid + '">' + data[i].questionName + '</a></td>' +
                            '<td>' + data[i].user.username + '</td>' +
                            '<td>' + data[i].answers.length + '</td>' +
                            '<td>' + data[i].uploadTime + '</td></tr>';
                        list += li
                    }
                    $("#tb").html(list)
                }
            })
        }

        function getTodayQ() {
            $.ajax({
                type: "post",
                url: "question/count",
                date: {},
                success: function (data) {
                    $("#s1").html("今日贴数：" + data.data)
                }
            })
        }

        function getTodayA() {
            $.ajax({
                type: "post",
                url: "answer/countToday",
                date: {},
                success: function (data) {
                    $("#s3").html("今日评论：" + data.data)
                }
            })
        }

        function getAllA() {
            $.ajax({
                type: "post",
                url: "answer/countAll",
                date: {},
                success: function (data) {
                    $("#s4").html("总评论数：" + data.data)
                }
            })
        }

        $(function () {
            getNewQu();
            getNewAn();
            getAllQu();
            getTodayQ();
            getTodayA();
            getAllA();
            $("#fb").click(function (){
                if ("${sessionScope.user}"==""){
                    alert("您还未登录呢，请登录后再发布帖子！")
                }
               else {
                    location.href="Bbs/PublishQuestion.jsp"
                }

            })
        })
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12" style="height: 50%">
            <h3 class="text-left text-info">
                智极线代交流论坛
            </h3>
            <div class="row-fluid" style="height: 100%">
                <div class="span4" style="height: 100%;font-size: 20px">
                    <span class="label  badge-info" style="height: 9%; font-size: 14px; padding-top: 10px;">最新帖子</span>
                    <ol id="newQ">

                    </ol>
                </div>
                <div class="span4" style="height: 100%;font-size: 20px">
                    <span class="label  badge-info" style="height: 9%; font-size: 14px; padding-top: 10px;">最新评论</span>
                    <ol id="newA">
                    </ol>
                </div>
                <div class="span4" style="height: 100%;font-size: 20px">
                   <span class="badge badge-success" style="font-size:30px;height: 13%; padding-top: 25px;" id="s1"></span>
                  <span class="badge badge-info"style="font-size:30px;height: 13%; padding-top: 25px;"  id="s3"></span> <br>
                    <span class="badge badge-inverse"style="font-size:30px;height: 13%; padding-top: 25px; margin-top: 30px "  id="s2"></span>
                    <span class="badge badge-inverse"style="font-size:30px;height: 13%; padding-top: 25px; margin-top: 30px"  id="s4"></span><br>
                </div>
            </div>
        </div>
        <div>
        <table class="table" style="margin-top: 22%">
            <thead>
            <tr>
                <th>
                    帖子名
                </th>
                <th>
                    发布人
                </th>

                <th>
                    评论数
                </th>
                <th style="width: 30%">
                    发布时间
                    <button id="fb" style="margin-left: 200px">发布帖子</button>
                </th>


            </tr>
            </thead>
            <tbody id="tb" style="overflow-y: scroll;">

            </tbody>
        </table>
        </div>
    </div>
</div>
</div>
</body>
</html>
