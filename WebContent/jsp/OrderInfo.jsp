<%@page import="cn.whcm.utils.JdbcUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.whcm.bean.User" %>
<!DOCTYPE html>
<html>
<!-- 这个是用户购买那一本书籍的jsp -->
<head>
<meta charset="utf-8">
<title>订单页面</title>
</head>
	<style>
		input[type="text"]{
			line-height: 30px;
			text-align: center;
			width:"150px";
			border: none;
			outline: none;/*设置元素周围的轮廓：*/
		}
		input[type="number"]{
			line-height: 30px;
			text-align: center;
			width:"150px";
			outline: none;/*设置元素周围的轮廓：*/
		}
		
		.textclass{
			margin-left:77px;
			border: none;
			outline: none;/*设置元素周围的轮廓：*/
		}
		.fromclass{
			text-align: center;
			text-overflow: inherit;
			border: 1px solid red;
			width:500px;
			margin:100px auto; 
		}
		input[type="passwrod"]{
			display: display;
			outline: none;/*设置元素周围的轮廓：*/
			border: 0px;
		}
	</style>
	<% Map<String,Object> map=(Map<String,Object>)session.getAttribute("mapUserBidBname");
		%>
<body>
	<div>
		<%
		HttpSession session1=request.getSession();
		User user=(User)session1.getAttribute("quan_user");
		if(user==null){	%>
		<a href="login.jsp">您还没有登入,是否先登入?</a>
		<% 	return; }else{%>
		<font color="red">欢迎：<%=((User)map.get("user")).getUser()  %>用户&nbsp;&nbsp;</font><a href="shoppingcart.jsp">返回购物车</a>
	<%} %>
	</div>
	<div>
		
		<form action="/Doem-2019-12-7/BuyBookServlet" method="post" class="fromclass" onsubmit="return checknumber();">
		            用  &nbsp;户 名：<input name="username" type="text"  readonly="readonly" value=<%=((User)map.get("user")).getUser()  %>><br>
			书 籍  &nbsp; id：<input name="upassword" type="text"  readonly="readonly" value=<%=map.get("bid")%>><br>
			书籍名称：<input class="msg_mc" name="bname" type="text" readonly="readonly"  value=<%=map.get("bname")%>><br>		
			<%
				String presentation=null;
				if(map.get("presentation")!=null){
					presentation=(String)map.get("presentation");
				}else{
					presentation="";
				}
				Double price=null;
				if(map.get("price")!=null){
					price=(Double)map.get("price");
				}else{
					price=0.0;
				}
			%>
			书籍信息：<input type="passwrod"/ readonly="readonly"><br><textarea name="presentation" class="textclass" rows="5" cols="" readonly="readonly" ><%=presentation %></textarea><br>
			书籍价格：<input name="price" type="text"  readonly="readonly" value=<%=price%>><br>
			<%
				String msg=(String)session.getAttribute("msg_book_number");
				if(msg==null){
					msg="";
				}
			%>
			书籍数量：<input name="number" id ="number" type="number"><br>
			<span id="id_number"><%=msg %></span><br>
			<input type="submit" value="购买">
			<input type="reset" value="重置"><br>
		
		</form>
	</div>
	<script>
		function checknumber() {
			var number = document.getElementById("number").value;
			var id_number = document.getElementById("id_number");
			if(number==""){
				id_number.innerHTML="购买数量不能为空";
				return false;
			}
			if(number<=0){
				id_number.innerHTML="购买数量不能小于0";
				return false;
			}else{
				return true;
			}
		}
		//清除提示
		function isChange() {
			document.getElementById("id_number").innerHTML = "";
		}
		setInterval(isChange, 3000);
		<%	session.removeAttribute("msg_book_number");	%>
	</script>
</body>
</html>