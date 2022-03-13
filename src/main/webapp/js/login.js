$(function () {
    H_login = {};
    H_login.openLogin = function(){
        $('.login-header a').click(function(){
            $('.login').show();
            $('.login-bg').show();
        });
    };
    H_login.closeLogin = function(){
        $('.close-login').click(function(){
            $('.login').hide();
            $('.login-bg').hide();
        });
    };
    H_login.loginForm = function () {
        $("#login-button-submit").on('click',function(){
              var username = $("#username");
              var usernameValue = $("#username").val();
              var password = $("#password");
              var passwordValue = $("#password").val();
            if(usernameValue == ""){
                alert("用户名不能为空");
                username.focus();
                return false;
            }else if(usernameValue.length > 15){
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            }else if(passwordValue == ""){
                alert("密码不能为空");
                password.focus();
                return false;
            }else if(passwordValue.length < 6 || passwordValue.length > 30){
                alert("密码长度不能小于6或大于30位字符");
                password.focus();
                return false;
            }else{
                        $.ajax({
                            type:"get",
                            url:"user/userLogin",
                            data:{
                                username:$("#username").val(),
                                password:$("#password").val()
                            },
                            success:function (data) {
                                if (data.flag){
                                    alert("登录成功！")
                                    location.href="index.jsp"
                                }else {
                                    alert(data.errorMessage)
                                }}
                        })




            }
        });
    };
    H_login.run = function () {
        this.closeLogin();
        this.openLogin();
        this.loginForm();
    };
    H_login.run();
});