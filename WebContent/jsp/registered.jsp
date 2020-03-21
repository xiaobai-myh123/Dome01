<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%		response.setHeader("text/html", "charset=utf-8");
			request.setCharacterEncoding("utf-8"); 
	%>
<%@ page import="cn.whcm.bean.User"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册页面</title>
		<style type="text/css">
			body{
			background-color: 	#00FFFF;
			}
			*{
				
				padding: 0px;
				margin: 0px;
			}
			input[type]{
				border: none;
				outline: none;
				font-size: 18px;
				background-color:#00FFFF;
			}
			.box{
				position: absolute;
				left: 50%;
				top: 50%;
				transform: translate(-50%,-50%);	
				line-height: 45px;	
				/*background-color: 	#00FFFF;*/
			/*	color: white;*/
			}
			
		
			tr::after td{
				text-align: right;
				line-height: 60px;
			}
			
			.box .td_r{
				padding-left: 17px;
			}
			.td_img{
				border: none;
			}
			.box .context_Msg .img_yzm{
				margin-left: -40px;
	
			}	
			
			.box  .context_img{
				line-height: 64px;
				font-size: 20px;
				position: absolute;
				left: 10px;
			}
			.box h3{
				text-align: center;
				font-family: "微软雅黑";
			}
			
			input[type="submit"],
			input[type="reset"]{
				background-color: blue;	
				color: white;
				width: 100px;
				line-height: 2;
				cursor: pointer;
				border-radius:10px ;
			}
			
			
			#chang_span_yzm,#chang_img_yzm{
				cursor: pointer;
				width:80px;
				display: inline-block;
				height:34px;
				margin-top: 14px;
			}
			
			.box .re_class{
				margin-left: 107px;
			}
		</style>
	</head>
	<body>
		<%
		HttpSession session1=request.getSession();
		User user=(User)session1.getAttribute("quan_user");
		if(user==null){	%>
		<a href="login.jsp">返回登入页面?</a>
		<% 	 }%>
		<form class="box" action="/Doem-2019-12-7/RegisteredServlet" method="post" onsubmit="return checkAll()">
			<h3>注册页面</h3>
			<table border="1px" cellspacing="0px" cellpadding="5">
				<tr>
					<td><label for="uname">用户名：</label></td>
					<td><input class="td_r" name="uname" id="uname" type="text" placeholder="请输入用户名"/></td>
				</tr>
				<tr>
					<td><label for="upassword">密码：</label></td>
					<td><input class="td_r" name="upassword" id="upassword" type="password" placeholder="请输入密码"/></td>
				</tr>
				<tr>
					<td><label for="upassword">确认密码：</label></td>
					<td><input class="td_r" id="upassword2" type="password" placeholder="请输入确认密码"/></td>
				</tr>
			</table>
			<div class="context_Msg">
				<span class="context_img">验证码：</span>
				<input type="text" />
				<img id="chang_img_yzm" class="img_yzm" src="/Doem-2019-12-7/GetphoneServlet"/><span id="chang_span_yzm">换一张？</span>
			</div>
			<input class="bu_class" type="submit" value="注册"/>
			<input class=re_class type="reset" value="重置"/><br>
					<font color="red" id="msg_span"></font><br>
					<span color="black">${cg}</span>
		</form>
		<script>
			var onclik_chang_img_yzm=function(){
				var chang_img_yzm = document.getElementById("chang_img_yzm");
				chang_img_yzm.src="/Doem-2019-12-7/GetphoneServlet?time="+new Date().getTime();
			}
			document.getElementById("chang_img_yzm").onclick=onclik_chang_img_yzm;
			document.getElementById("chang_span_yzm").onclick=onclik_chang_img_yzm;		
			//验证账户和俩次密码是否一样
			var uname;
			var upassword;
			var upassword2;
			var msg_span;
		
			window.onload = function() {
				uname = document.getElementById("uname");
				upassword = document.getElementById("upassword");
				upassword2 = document.getElementById("upassword2");
				msg_span = document.getElementById("msg_span");
			}
			
			//用户名需要三位到六位
			function checkAll(){
				var all=checkUser()&&checkupassword()&&checkupassword2()&&checkpasswd12();
				return all;
			}
			function checkUser(){
				var value=uname.value;
				if(value==""){
					msg_span.innerHTML="用户名或者密码不能为空1";
					return false;
				}
				if(value.length>=3&&value.length<=6){
					msg_span.innerHTML="";
					return true;
				}else{
					msg_span.innerHTML="用户名或者密码应该输入3-6位1";
					return false;
				}
			}
			
			function checkupassword(){
				var value=upassword.value;
				if(value==""){
					msg_span.innerHTML="用户名或者密码不能为空2";
					return false;
				}
				if(value.length>=3&&value.length<=6){
					msg_span.innerHTML="";
					return true;
				}else{
					msg_span.innerHTML="用户名或者密码应该输入3-6位2";
					return false;
				}
			}
			
			function checkupassword2(){
				var value=upassword2.value;
				if(value==""){
					msg_span.innerHTML="用户名或者密码不能为空3";
					return false;
				}
				if(value.length>=3&&value.length<=6){
					msg_span.innerHTML="";
					return true;
				}else{
					msg_span.innerHTML="用户名或者密码应该输入3-6位3";
					return false;
				}
			}
			
			function checkpasswd12(){
				var value1=upassword.value;
				var value2=upassword2.value;
				if(value1!=value2){
					msg_span.innerHTML="俩次密码不一样";
					return false;
				}
			}
		
		</script>
	</body>
</html>
