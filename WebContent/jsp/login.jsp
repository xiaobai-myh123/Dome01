<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登入页面</title>
		<style>
			body{
				padding: 0px;
				margin: 0px;
				font-family: sans-serif;
				background: #34495e;
			}
			.box{
				width: 400px;
				padding: 40px;
				position: absolute;
				top: 50%;
				left: 50%;
				border: 1px solid red;
				transform: translate(-50%,-50%);/*旋转 div 元素：*/
				background: #191919;
				text-align: center;
			}
			.box h1{
				color: white;
				text-transform:uppercase;
				font-weight: 500;
			}
			
			.box input[type="text"],
			.box input[type="password"]{
				border: 0;
				background: none;
				display: block;
				margin: 20px auto;
				text-align: center;
				border: 2px solid #3498db; 	
				padding: 14px 10px;
				width: 200px;
				outline: none;/*设置元素周围的轮廓：*/
				color: white;
				border-radius: 24px;
				transition: 0.25;
			}
			
			.box input[type="text"]:focus,.box input[type="password"]:focus{
				width: 280px;
				border-color:white ;
			}
			
			.box input[type="submit"]{
				border: 0;
				background: none;
				display: inline-block;
				margin: auto;
				text-align: center;
				border: 2px solid #3498db; 	
				padding: 14px 40px;
				outline: none;
				color: white;
				border-radius: 24px;
				transition: 0.25;
				cursor: pointer;
			}
			
			.registered{
				position: relative;
				display: inline-block;
				border: 2px solid #3498db;
				width: 104px;
				height: 23px;				
				text-align: center;
				vertical-align: auto;
				border-radius: 24px;
				font-size: 14px;
				padding-top: 10px;		
				padding-bottom: 10px;				
				color: white;
				text-decoration: none;
			} 
			
			.box input[type="submit"]:hover,.registered:hover{
				background: blue;
			}
			img{
				display: block;
			}
			
			.box .imgcheck{
				position: relative;
				
				margin-bottom: -92px;
				left: 378px;
				top: -19px;
				
			}
			/*.lun_img{
				position: absolute;
				top: 50%;
				transform: translate(-50%,-50%);/*旋转 div 元素：
				width: 200px;
			}*/
		</style>
	</head>
	<body>
		<!--<div class="lun_img">
				<img id="lun_img_id" src="img/1.jpg" />
		</div>-->
	
		<form class="box" action="${pageContext.request.contextPath}/LoginServlet" method="post">
			<img id="chang_img" class="imgcheck" src="/Doem-2019-12-7/sourse/deng_off.png"" />
			<h1>Login</h1>
			<input type="text" name="user" placeholder="user" value="${login_jsp_user}" />
			<input type="password" name="password" id="" placeholder="password" value="${login_jsp_password}" />
			<input type="submit" value="Login" />
			<a class="registered" href="${pageContext.request.contextPath}/jsp/registered.jsp">registered</a><br>
			<font color="red" id="msg_span">${error}</font><br>	
			<font color="red">${cg}</font>	
		</form>
		<script>
			//开关灯
			var flag = true;
			document.getElementById("chang_img").onclick=function(){
				if(flag){
					/*var chang_img=document.getElementById("chang_img");
					chang_img.src="/Doem-2019-12-7/sourse/deng_on.png";
					document.body.style.backgroundColor="yellow";
					flag=false;*/
					window.location.href="${pageContext.request.contextPath}/jsp/Rlogin.jsp";
					
				}else{
					var chang_img=document.getElementById("chang_img");
					chang_img.src="/Doem-2019-12-7/sourse/deng_off.png";
					document.body.style.backgroundColor=" #34495e";
					flag=true;
				
				}
			}
			//轮播图
			/*
				var num=2;
				function lun_chang_img(){
				var lun_img_id=document.getElementById("lun_img_id");
				lun_img_id.src="sourse/"+(num%6+1)+".jpg"
				num++;
				}
				setInterval(lun_chang_img,1000);
			*/
		</script>
		
	</body>
</html>
