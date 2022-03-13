<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/13
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/bootstrap-combined.min.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
    <script src="../js/jquery.min.js"></script>
</head>
<body>
<div id="if" style="height: 84%">
    <table class="table table-hover table-striped" style="margin-left: 200px;width: 80%;">
        <thead>
        <tr>
            <th>
                id
            </th>
            <th>
                标题
            </th>
            <th>
                发布时间
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
</body>
<script>
    function deleteByQid(qid){
        var r = confirm("您确定要删除这篇贴子吗？")
        if (r == true) {
            $.ajax({
                type: "post",
                url: "../question/deleteByQid",
                data:{qid},
                success:function (data){
                    if (data){
                        alert("删除成功")
                        location.reload()
                    }else {
                        alert(data.errorMessage)
                    }
                }

            })
        }}
    function up(uid, currentPage, pageSize) {

        $.ajax({
            type: "post",
            url: "../question/personal",
            data: {
                "uid": uid,
                "currentPage": currentPage,
                "pageSize": pageSize,
            },
            success: function (data) {
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
                for (var j = 0; j < data.list.length; j++) {
                    var question = data.list[j]
                    var li2 = ' <tr>\n' +
                        ' <td> ' + question.qid + ' </td>\n ' +
                        '<td>' + question.questionName + '</td>\n' +
                        '<td>' + question.uploadTime + '</td>\n' +
                        ' <td>' +
                        ' <a href="ShowQuestion.jsp?qid=' + question.qid  + '" style="text-decoration: none">查看</a>' +
                        ' <a href="javascript:deleteByQid(' + question.qid+')" style="text-decoration: none">删除</a>' +
                        '</td>' +
                        '</tr>';
                    list2 += li2

                }


                $("#tb").html(list2)
            }

        })
    }

    $(function (){
        up("${sessionScope.user.uid}",1,5)
    })
</script>
</html>
