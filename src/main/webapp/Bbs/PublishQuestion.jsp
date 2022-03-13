<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/12
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="../js/jquery.min.js"></script>
    <script src="../ckeditor/ckeditor.js"></script>

</head>
<body>
<button class="btn btn-link" type="button" style="margin-left: 22px;font-size: 18px" id="bb"  >返回</button>
<form  >
    <p style=" margin-left: 259px;">
        <span style="font-size: 20px">标题：</span>
        <input type="text" name="question" id="questionName" style="height: 28px;" ></p>
    <p style="width: 1000px">
        <span style="font-size: 20px">内容：</span>

        <textarea style="width: 870px;height: 375px;resize:none" name="text" id="text"  >
      </textarea>
        <button type="submit" class="btn" id="sub" style="margin-left: 422px;margin-top: 30px">发布</button>
    </p>
</form>


<script >
    window.onload = function()
    {
        CKEDITOR.replace( 'text');
    };
    $(function () {

        $(function () {
                $("#bb").click(function () {
                    var n=document.referrer;
                    location.href=n
                })
            }
        )
        $("#sub").click(function () {
            var f=document.referrer;
            var  uid;
            if (f=="http://localhost:8080/LinearAlgebra/BbsManage/BbsManage.jsp") {
               uid ="${sessionScope.admin.uid}"
            }else {
                uid="${sessionScope.user.uid}"
            }
            $.ajax({
                type: "post", // 以post方式发起请求
                url: "../question/add", // 你的请求链接
                data: { // 提交数据
                    "uid":uid,
                    "questionName":$("#questionName").val(),
                    "text": CKEDITOR.instances.text.getData(), // 前者为字段名，后者为数据
                },
                success:function (data) {
                    // data为返回值

                    if (data.flag){
                        alert("帖子发布成功！")
                    }
                    else {
                        alert(data.errorMessage)

                    }

                }
            })


            return false
        })
    })

</script>

</body>

</body>
</html>
