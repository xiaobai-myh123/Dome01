<%@ page import="cn.whcm.utils.JdbcUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.whcm.bean.*" %>
<!DOCTYPE html>
<html>
<!-- 这个是用户购买那一本书籍的jsp -->
<head>
<meta charset="utf-8">
<title>修改商品页面</title>
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
<body>
	<div>
		<%
		HttpSession session1=request.getSession();
		Root root=(Root)session1.getAttribute("root_mysql");
		if(root==null){	%>
		<a href="login.jsp">您还没有登入,是否先登入?</a>
		<% 	return; }else{%>
		<font color="red">欢迎：<%=root.getName()%>用户   &nbsp;&nbsp;&nbsp;</font><a href="Rmain.jsp">是否返回商品？</a>
	<%} 
		Merchandise m=(Merchandise)session1.getAttribute("merchandiseByID");//表示一个商品的信息
	%>
	</div>
	<div>
		<form action="/Doem-2019-12-7/RmodifBookInfoServlet" method="post" class="fromclass" onsubmit="return checkAll();">
		            用  &nbsp;户 名：<input name="username" type="text"  readonly="readonly" value=<%=root.getName()%>><br>
			书 籍  &nbsp; id：<input name="bid" type="text"  readonly="readonly" value=<%=m.getId()%>><br>
			书籍名称：<input class="msg_mc" id="bname" name="bname" type="text" value=<%=m.getBookname()%>><br>		
			<%
				String presentation=null;
				if(m.getPresentation()!=null){
					presentation=(String)m.getPresentation();
				}else{
					presentation="";
				}
				Double price=null;
				if(m.getPrice()!=null){
					price=(Double)m.getPrice();
				}else{
					price=0.0;
				}
			%>
			书籍信息：<input type="passwrod" readonly="readonly" /><br><textarea name="presentation" class="textclass" rows="5" cols="" ><%=presentation%></textarea><br>
			书籍价格：<input name="price" id="price" type="text" value=<%=price%>><br>
			<%
				String msg=(String)session.getAttribute("succeed");
				if(msg==null){
					msg="";
				}
			%>
			书籍数量：<input name="number" id ="number" type="number" value="<%=m.getNumber() %>"><br>
			<span id="id_number"><%=msg %></span><br> <%--<%=(String)session.getAttribute("succeed")==null?"":(String)session.getAttribute("succeed")%> --%>
			<%msg="";session.removeAttribute("succeed");%>
			<input type="submit" value="修改">
		</form>
	</div>
	<script>
		<!--  判断bname是否为空  !-->
		var id_number=document.getElementById("id_number");
		function checkBname(){
			var value=document.getElementById("bname").value;
			if(value==""){
				id_number.innerHTML = "书籍名称不能为空";
				return false;
			}else{
				return true;
			}
			return false;
		}
		
		<!--  判断price是否为空  !-->
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
		
		<!--  判断number是否为空  !-->
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
		
		<!--  判断修改的数据是否为空  !-->
		function  checkAll() {
			return checkBname()&&checkPrice()&&checkNumber();;
		}
	
		<!--  判断修改的数据是否为空  !-->
		//清除提示
		function isChange() {
			document.getElementById("id_number").innerHTML = "";
		}
		setInterval(isChange, 3000);
		
	</script>
</body>
</html>