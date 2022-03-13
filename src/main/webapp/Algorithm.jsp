<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/15
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <style>
    </style>


</head>
<body>


<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">
                <div class="span10" style="width: 100%">
                    <div class="navbar navbar-inverse">
                        <div class="navbar-inner">
                            <div class="container-fluid " style="padding-top: 5px;font-size: 20px">
                                <a class="btn btn-navbar" data-target=".navbar-responsive-collapse"
                                   data-toggle="collapse"></a> <a class="brand" href="#"
                                                                  style="pointer-events: none;font-size: 30px"
                            >算法库</a>
                                <div class="nav-collapse collapse navbar-responsive-collapse">
                                    <ul class="nav">
                                        <li>
                                            <a href="javascript:up(0,1,5)" id="a1">开源算法</a>
                                        </li>
                                        <li>
                                            <a href="javascript:PersonalUp()" id="a2">个人算法</a>
                                        </li>
                                        <li>
                                            <a href="javascript:load()" id="a3">上传算法</a>
                                        </li>

                                    </ul>
                                    <ul class="nav pull-right">
                                        <li>
                                            <div class="span2" style="width: 300px">
                                                <form class="form-search"
                                                      style="margin: 0 0 20px; margin-left: 0;margin-top: 8px;width: 300px;margin-left: 24px;">
                                                    <input class="input-medium search-query" type="text"
                                                           style="width: 180px" id="in" required/>
                                                </form>
                                                <button type="submit" class="btn"
                                                        style="margin-left: 220px ;margin-top: -50px" id="se">查找
                                                </button>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>

            </div>
            <div id="if" style="height: 84%">
                <table class="table table-hover table-striped" style="margin-left: 200px;width: 80%;">
                    <thead>
                    <tr>
                        <th>
                            id
                        </th>
                        <th>
                            算法名
                        </th>
                        <th>
                            作者
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                    </thead>
                    <tbody id="tb">


                    </tbody>
                </table>
                <div class="row-fluid" style="position: absolute;top: 430px;left: -5px;">
                    <div class="span12">
                        <div class="pagination pagination-centered">
                            <span><h3>共<span id="totalPage"></span>页<span id="totalCount"></span>篇</h3> </span>
                            <ul id="list">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <%--    <form action="PaginationServlet" method="post">--%>
    <%--        <input type="text" value="字符" name="te">--%>
    <%--        <input type="submit" value="提交">--%>
    <%--    </form>--%>
</div>
</body>
<script>

    function deleteA(aid) {
        var r = confirm("您确定要删除这篇算法吗？")
        if (r == true) {
            $.ajax({
                type: "post",
                url: "algorithm/delete",
                data: {
                    "aid": aid
                },
                success: function (data) {
                    if (data.flag) {
                        alert("删除成功！")
                        location.reload()
                    } else {
                        alert(data.errorMessage)
                    }

                }
            })
        }
    }
    function load()
    { if ("${sessionScope.user}" == "") {
        $("#a1").focus()
        alert("您还未登录，请登录后再上传算法！")

    }
     else {
        $("#if").load("Algorithm/Upload.jsp");
    }
    }
    function up(uid, currentPage, pageSize) {
        var d= '<table class="table table-hover table-striped" style="margin-left: 200px;width: 80%;"><thead><tr><th>id</th><th>'
                  +'算法名</th><th>作者</th><th>操作</th></tr></thead><tbody id="tb"></tbody></table><div class="row-fluid" style="position: absolute;top: 430px;left: -5px;"><div class="span12"><div class="pagination pagination-centered"><span><h3>共<span id="totalPage"></span>页<span id="totalCount"></span>篇</h3> </span><ul id="list"></ul></div></div></div>'
            $("#if").html(d)
        $.ajax({
            type: "post",
            url: "algorithm/query",
            data: {
                "uid": uid,
                "currentPage": currentPage,
                "pageSize": pageSize,
            },
            success: function (data) {
                if (uid == 0) {
                    $("#a1").focus()
                } else {
                    $("#a2").focus()
                }
                $("#totalPage").html(data.pages)
                $("#totalCount").html(data.total)
                var list = "";
                var firstPage = '<li> <a href="javascript:up(' + uid + ',1,5)">首页</a> </li>'
                if (data.pageNum == 1) {
                    var pre = 1
                } else pre = currentPage - 1
                var prePage = '<li> <a href="javascript:up(' + uid + ',' + pre + ',5)">上一页</a> </li>'
                list += firstPage
                list += prePage
                for (var i = 1; i <= data.pages; i++) {
                    // alert(Type)
                    if (data.pageNum == i) {
                        var li = '<li  ><a  style="background-color: #459d3e" href = "javascript:up(' + uid + ',' + i + ',5)">' + i + '</a></li>'

                    } else {
                        var li = '<li><a href = "javascript:up(' + uid + ',' + i + ',5)">' + i + '</a></li>'
                    }
                    list += li;
                }
                if (data.pageNum == data.pages) {
                    var last = data.pages


                } else last = data.pageNum + 1
                var lastPage = '<li> <a href="javascript:up(' + uid + ',' + last + ',5)">下一页</a> </li>'

                var nextPage = '<li> <a href="javascript:up(' + uid + ',' + data.pages + ',5)">尾页</a> </li>'
                list += lastPage
                list += nextPage
                $("#list").html(list)
                var list2 = "";
                if (uid == 0) {
                    for (var j = 0; j < data.list.length; j++) {
                        var algorithm = data.list[j]
                        var li2 = ' <tr>\n' +
                            ' <td> ' + algorithm.aid + ' </td>\n ' +
                            '<td>' + algorithm.algorithmName + '</td>\n' +
                            '<td>' + algorithm.user.username + '</td>\n' +
                            ' <td>' +
                            ' <a href="algorithm/visit?aid=' + algorithm.aid + '&algorithmName=' + algorithm.algorithmName + '&text=' + algorithm.text + '&username=' + algorithm.user.username + '" style="text-decoration: none">查看</a>' +
                            '  <a href="algorithm/downLoad?algorithmName=' + algorithm.algorithmName + '&text=' + algorithm.text + '&username=' + algorithm.user.username + '" style="text-decoration: none">下载</a>' +
                            '</td>' +
                            '</tr>';
                        list2 += li2

                    }

                } else {
                    for (var j = 0; j < data.list.length; j++) {
                        var algorithm = data.list[j]
                        var li2 = ' <tr>\n' +
                            ' <td> ' + algorithm.aid + ' </td>\n ' +
                            '<td>' + algorithm.algorithmName + '</td>\n' +
                            '<td>' + algorithm.user.username + '</td>\n' +
                            ' <td>' +
                            ' <a href="algorithm/visit?aid=' + algorithm.aid + '&algorithmName=' + algorithm.algorithmName + '&text=' + algorithm.text + '&username=' + algorithm.user.username + '" style="text-decoration: none">查看</a>' +
                            '  <a href="Algorithm/Alter.jsp?aName=' + algorithm.algorithmName + '&aText=' + algorithm.text + '&aid=' + algorithm.aid + '" style="text-decoration: none">修改</a>' +
                            '  <a href="javascript:deleteA(' + algorithm.aid + ')" style="text-decoration: none;color: red">删除</a>' +
                            '  <a href="algorithm/downLoad?algorithmName=' + algorithm.algorithmName + '&text=' + algorithm.text + '&username=' + algorithm.user.username + '" style="text-decoration: none">下载</a>' +
                            '</td>' +
                            '</tr>';
                        list2 += li2

                    }

                }
                $("#tb").html(list2)
            }

        })
    }
    function findByKey(key, currentPage, pageSize) {
        $.ajax({
            type: "post",
            url: "algorithm/findByKey",
            data: {
                "key": key,
                "currentPage": currentPage,
                "pageSize": pageSize,
            },
            success: function (data) {
                $("#totalPage").html(data.pages)
                $("#totalCount").html(data.total)
                var list = "";
                var firstPage = '<li> <a href="javascript:findByKey(' + key + ',1,5)">首页</a> </li>'
                if (data.pageNum == 1) {
                    var pre = 1
                } else pre = currentPage - 1
                var prePage = '<li> <a href="javascript:findByKey(' + key + ',' + pre + ',5)">上一页</a> </li>'
                list += firstPage
                list += prePage
                for (var i = 1; i <= data.pages; i++) {
                    // alert(Type)
                    if (data.pageNum == i) {
                        var li = '<li  ><a  style="background-color: #459d3e" href = "javascript:findByKey(' + key + ',' + i + ',5)">' + i + '</a></li>'

                    } else {
                        var li = '<li><a href = "javascript:findByKey(' + key + ',' + i + ',5)">' + i + '</a></li>'
                    }
                    list += li;
                }
                if (data.pageNum == data.pages) {
                    var last = data.pages


                } else last = data.pageNum + 1
                var lastPage = '<li> <a href="javascript:findByKey(' + key + ',' + last + ',5)">下一页</a> </li>'

                var nextPage = '<li> <a href="javascript:findByKey(' + key + ',' + data.pages + ',5)">尾页</a> </li>'
                list += lastPage
                list += nextPage
                $("#list").html(list)
                var list2 = "";
                    for (var j = 0; j < data.list.length; j++) {
                        var algorithm = data.list[j]
                        var li2 = ' <tr>\n' +
                            ' <td> ' + algorithm.aid + ' </td>\n ' +
                            '<td>' + algorithm.algorithmName + '</td>\n' +
                            '<td>' + algorithm.user.username + '</td>\n' +
                            ' <td>' +
                            ' <a href="algorithm/visit?aid=' + algorithm.aid + '&algorithmName=' + algorithm.algorithmName + '&text=' + algorithm.text + '&username=' + algorithm.user.username + '" style="text-decoration: none">查看</a>' +
                            '  <a href="algorithm/downLoad?algorithmName=' + algorithm.algorithmName + '&text=' + algorithm.text + '&username=' + algorithm.user.username + '" style="text-decoration: none">下载</a>' +
                            '</td>' +
                            '</tr>';
                        list2 += li2

                    }
                $("#tb").html(list2)
            }

        })
    }

    function PersonalUp() {
        if ("${sessionScope.user}" == "") {
            $("#a1").focus()
            alert("您还未登录，请登录后查看个人算法！")

        } else {
            up("${sessionScope.user.uid}", 1, 5)
        }
    }



    $(function () {


            if ("${sessionScope.uid}" == "") {
                up(0, 1, 5)
            } else {
                up("${sessionScope.uid}", "${sessionScope.currentPage}", "${sessionScope.pageSize}")
            }
            $("#se").click(
                function () {
                    if ( $("#in").val()==""){
                        return false;
                    }
                    else {
                        findByKey($("#in").val(),1,5)
                    }

                }
            )


        }
    )

</script>
</html>
