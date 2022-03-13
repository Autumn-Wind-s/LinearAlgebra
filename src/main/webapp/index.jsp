<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2022/3/3
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <link rel="stylesheet" type="text/css"  href="css/style.css"/>
    <style>
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            padding: 12px 16px;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>

    <script>
        function out() {
            var r=confirm("您确定要退出登录嘛？")
            if (r==true){
               $.ajax({
                   type:"post",
                   url:"user/out",
                   data:{},
                   success:function (data){
                       if (data.flag){
                           location.reload()
                       }else {
                           alert(data.errorMessage)
                       }
                   }
               })
            }}
        function al(){

            document.getElementById("iframe").src="AlterUser.jsp"
        }
        function te(){

            document.getElementById("iframe").src="Bbs/MyQuestion.jsp"
        }
        function hu(){

            document.getElementById("iframe").src="Bbs/MyAnswer.jsp"
        }
        $(function () {

            $("#a2").click(function (){
                document.getElementById("iframe").src="Line.jsp"
            })
            $("#a3").click(function (){
                document.getElementById("iframe").src="Bbs.jsp"
            })
            $("#a4").click(function (){
                document.getElementById("iframe").src="Algorithm.jsp"
            })

            var username="${sessionScope.user.username}";
            var a=' <a class="">'+username+'</a><div class="dropdown">' +
                '<div class="dropdown-content"> ' +
                '<p> <a href="javascript:al()">修改信息</a></p> '+
                '<p> <a href="javascript:te()">我的帖子</a></p> '+
                '<p> <a href="javascript:hu()">我的评论</a></p> '+
                '<p> <a  href="javascript:out()">退出登录</a></p></div></div>';
            if (${sessionScope.user!=null}){

                $("#ui").html(a)
            }


        })
    </script>
</head>
<body>
<div class="container-fluid" style=" padding-right: 4px; padding-left: 0.3px;">
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">
                <div class="span12">
                    <div class="navbar navbar-inverse" style="height: 60px">
                        <div class="navbar-inner" style="height: 80px">
                            <div class="container-fluid" style="height: 60px;font-size: 20px;margin-top: 20px;">
                                <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">网站名</a>
                                <div class="nav-collapse collapse navbar-responsive-collapse">

                                    <ul class="nav pull-right">
                                        <li>
                                            <a href="#">主页</a>
                                        </li><li>
                                        <li>
                                            <a id="a2" href="#">线代计算</a>
                                        </li>
                                        <li>
                                            <a id="a3" href="#">问答论坛</a>
                                        </li>
                                        <li>
                                            <a id="a4" href="#">算法库</a>
                                        </li>
                                        <li class="dropdown"id="ui" tabindex="0"  >
                                            <div class="login-header" style="margin-top: 4px"><a style="color: #ffffff91;font-size: 20px;text-decoration: none;"  href="javascript:void(0); ">登录</a></div>

                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div style="height: 550px" class="row-fluid">
                <div class="span12">
                    <iframe width="100%" height="100%" name="sss" id="iframe" src="">
                    </iframe>
                </div>
            </div>
            <div style="height: 50px" class="row-fluid">
                <div class="span12">
                    <address> <strong>Twitter, Inc.</strong><br /> 795 Folsom Ave, Suite 600<br /> San Francisco, CA 94107<br /> <abbr title="Phone">P:</abbr> (123) 456-7890</address>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="login">
    <div class="login-title">登录会员<span><a href="javascript:void(0);" class="close-login">关闭</a></span></div>
    <div class="login-input-content">
        <div class="login-input">
            <label>用&nbsp;户&nbsp;&nbsp;名：</label>
            <input type="text" placeholder="请输入用户名"  name="username" id="username" class="list-input"/>
        </div>
        <div class="login-input">
            <label>登录密码：</label>
            <input type="password" placeholder="请输入登录密码" name="password" id="password" class="list-input"/>
        </div>
    </div>
    <div class="login-button"><a href="javascript:;" id="login-button-submit">登录</a></div>
</div>
</body>

</html>
