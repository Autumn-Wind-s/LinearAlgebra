<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/7
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>文章上传</title>
    <link href="../css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="../js/jquery.min.js"></script>
    <script>



    </script>
</head>
<body>
<div class="container-fluid" style="height: 100%">
    <div class="row-fluid" style="height: 100%">
        <div class="span12" style="height: 100%">
            <h1 class="text-center">
                算法上传
            </h1>
            <form class="form-inline" action="${pageContext.request.contextPath}/algorithm/upload" style=" margin-left: 450px; width: 500px; font-size: 20px" method="post" id="fileForm"  enctype="multipart/form-data" >
                <p style="width: 400px ;height: 50px;margin-left: 82px;margin-top: 100px"><label style="font-size: 20px">算法名：</label>
                    <input type="text" name="algorithmName" required/></p>
                <p style="width: 400px ;height: 50px;margin-left: 155px;margin-top: 29px"><label  style="font-size: 20px">文件：</label>
                 <input type="file" id="file" name="file" multiple required/></p>
                <input type="submit" class="btn" id="uo"  style=" margin-left: 211px;margin-top: 60px; width: 100px" value="上传">
            </form>
        </div>
    </div>
</div>
</body>
</html>
