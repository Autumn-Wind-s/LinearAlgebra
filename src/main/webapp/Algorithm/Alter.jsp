
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
<form method = "get" >
    <p style=" margin-left: 259px;">
        <span style="font-size: 20px">算法名：</span>
        <input type="text" name="algorithmName" id="algorithmName" style="height: 28px;" value=${param.get("aName")}></p>

    <p style="width: 1000px">
      <textarea name="description" id="description"  >
          ${param.get("aText")}
      </textarea>
        <button type="submit" class="btn" id="sub" style="margin-left: 422px;margin-top: 30px">修改</button>
    </p>
</form>


<script >
    window.onload = function()
    {
        CKEDITOR.replace( 'description');
    };
    $(function () {

        $(function () {
                $("#bb").click(function () {
                    var f=document.referrer;
                    location.href=f
                })
            }
        )
        $("#sub").click(function () {
            $.ajax({
                type: "get", // 以post方式发起请求
                url: "../algorithm/update", // 你的请求链接
                data: { // 提交数据
                    "aid":"${param.aid}",
                    "algorithmName":$("#algorithmName").val(),
                    "text": CKEDITOR.instances.description.getData(), // 前者为字段名，后者为数据
                },
                success:function (data) {
                    // data为返回值

                    if (data.flag){
                        alert("恭喜你修改成功！")
                    }
                    else {
                        alert(data.errorMessage)

                    }
                    // 成功后的回调方法
                }
            })


            return false
        })
    })

</script>

</body>
</html>
