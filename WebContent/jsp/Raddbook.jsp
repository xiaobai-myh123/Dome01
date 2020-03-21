<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.whcm.bean.*"%>
    
    <%
    	//判断root用户是否登入
    		Root root = (Root)session.getAttribute("root_mysql");
			if(root==null){%>	
				<a href="/Doem-2019-12-7/jsp/Rlogin.jsp">您还没有登入,是否先登入?</a>
	<%   		return;
			}
    %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品添加页面</title>
		<style>
			.im_hy{
				color: red;
			}
			.box{
				text-align: center;
				background-color: yellow;
			}
			.box .form{
				text-align: center;
			}
			input[type="text"]{
				background-color: yellow;
				font-family: "微软雅黑";
				font-size: 20px;
				/*outline: none; 驱动边框影子*/
				padding-left: 2em;
			}
			.form table .thead_class{
				font-size: 25px;
			}
			span {
				display: block;
			    font-size: 25px;
			    margin-top:37px ;
			    margin-bottom:15px ;
			}
		</style>
	</head>
	
	<body>
		<div>
			<div>
				<p class="im_hy">欢迎：<%=root.getName() %>&nbsp;&nbsp;用户</p>
				<a href="/Doem-2019-12-7/jsp/Rmain.jsp">返回首页</a>
			</div>
			<div class="box">
				<form action="/Doem-2019-12-7/RInsertBookServlet" method="post" class="form" onsubmit="return checkAll();">
					<table border="1px" cellpadding="10px" cellspacing="0" align="center">
						<span>小白的购物车</span>
						<tr>
							<td>bookname</td>
							<td><input id="bookname" name="bookname" type="text" placeholder="请输入书籍名称"/></td>
						</tr>
						<tr>
							<td>price</td>
							<td><input id="price" name="price" type="text" placeholder="请输入书籍价格"/></td>
						</tr>
						<tr>
							<td>presentation</td>
							<td><input id="presentation" name="presentation" type="text" placeholder="请输入书籍信息"/></td>
						</tr>
						<tr>
							<td>number</td>
							<td><input id="number" name="number" type="text" placeholder="请输入书籍数量"/></td>
						</tr>
					</table>
				<% 
					String succeed =(String)session.getAttribute("succeed");
						if(succeed==null){
							succeed=" ";
						}
				%>	
				
					<span id="id_number"><%=succeed %></span>
				<% 
					succeed=" ";
				%>
					<br />
					<input type="submit" value="添加"/>
					<input type="reset" nam="重置"/>
				</form>
				
				
			</div>
		</div>
	</body>
	<script>
		var id_number=document.getElementById("id_number");
		function checkBname(){
			var value=document.getElementById("bookname").value.trim();
			if(value==""){
				id_number.innerHTML = "书名不能为空";
				return false
			}else{
				return true;
			}
			return false;
		}
		function checkPrice(){
			var regex = /^[\d]{0,}[.]{0,1}[\d]{0,}$/;
			var value = document.getElementById("price").value.trim();
			if(value==""){
				id_number.innerHTML = "价格不能为空";
				return false;
			}else if(!regex.test(value)){
				id_number.innerHTML = "价格应该为合法数字";
				return false;
			}else{
				if(value < 0) {
					id_number.innerHTML = "价格不能为负数";
					return false;
				}else{
					return true;
				}
			}
			return false;
		}
		function checkNumber(){
			var regex = /^[\d]{0,}$/;
			var value = document.getElementById("number").value.trim();
			if(value==""){
				id_number.innerHTML = "书籍数量不能为空";
				return false;
			}else if(!regex.test(value)){
				id_number.innerHTML = "数量应该为合法数字";
				return false;
			}else{
				if(value < 0) {
					id_number.innerHTML = "书籍数量不合法";
					return false;
				}else{
					return true;
				}
			} 
			return false;
		}
		
		function  checkAll() {
			return checkBname()&&checkPrice()&&checkNumber();;
		}
		//清楚添加的提示
		var img_span = document.getElementById ("id_number");
		function chang_img_span(){
			if(img_span.value!=""){
				img_span.innerHTML="";
			}
		}        
		setInterval(chang_img_span,3000);
		
		<%	session.removeAttribute("succeed");	%>
	</script>
</html>
