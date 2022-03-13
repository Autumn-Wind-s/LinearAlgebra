<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/6
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/jquery.min.js"></script>
    <link href="../css/bootstrap-combined.min.css" rel="stylesheet">

    <script>
        $(function () {
                $("#bb").click(function () {
                    var f=document.referrer;
                    location.href=f
                })
            }
        )

    </script>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12" style="font-family: cursive">
            <button id="bb" class="btn btn-link" type="button"  style=" margin-left: 22px;font-size: 18px;"   >返回</button>
            <h3 class="text-center">
                ${requestScope.algorithm.algorithmName}
            </h3>
            <h4 class="text-center">
                作者:${requestScope.algorithm.user.username}

            </h4>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <p style="text-indent: 2em;font-family: cursive;font-size: 19px ">
                ${requestScope.algorithm.text}
            </p>
        </div>
    </div>
</div>
</body>
</html>
