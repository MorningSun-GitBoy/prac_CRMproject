<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>
        $(function(){
            //设置默认顶层窗口
            if(window.top!=window.self){
                window.top.location = window.self.location;
            }
            //页面加载完毕后，清空用户文本框中的内容
            $("#loginAct").val("");
            //得到用户名输入框对象
            $("#loginAct").focus();//获得焦点focus();丢失焦点blur();
            //为登录按钮绑定登录操作
            $("#submitBtn").click(function(){
                login();
            })
            //为窗口绑定键盘事件
            $(window).keydown(function(event){
                if(13==event.keyCode){
                    login();
                }
            })
        })
        function login(){
            var loginAct = $.trim($("#loginAct").val());
            var loginPwd = $.trim($("#loginPwd").val());
            if(loginAct == "" || loginPwd == ""){
                $("#msg").html("账号或密码不能为空");
                return false;//避免执行ajax方法
            }
            //通过Ajax进行后台验证
            $.ajax({
                url:"users/login.do",
                data:{
                    "loginAct":loginAct,
                    "loginPwd":loginPwd
                },
                type:"post",
                dataType:"json",
                success:function(data){
                /*
                 * data{
                 *    "success":true/false,
                 *    "msg":错误信息
                 * }
                 */
                    if(data.success){
                        //登陆成功则跳转
                        window.location.href = "workbench/index.jsp";
                    }else{
                        //登录失败，则显示提示信息
                        $("#msg").html(data.msg);
                    }
                }
            })
        }
    </script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2020&nbsp;NEAU.ZTJ.SH</span></div>
	</div>

	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.jsp" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">

							<span id="msg" style="color: red"></span>

					</div>
					<!--

						注意：按钮写在form表单中，默认的行为就是提交表单
							一定要将按钮的类型设置为button
							按钮所触发的行为应该是由我们自己手动写js代码来决定

					-->
					<button type="button" id="submitBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>