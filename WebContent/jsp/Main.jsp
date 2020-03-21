<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="cn.whcm.bean.*"%>
<%@ page import="cn.whcm.dao.Merchandisedao"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.whcm.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品展示页面</title>
	<style type="text/css">
		div {
			margin-top: 100px;
			border: 1px solid red;
		}
		
		table {
			text-align: center;
			background-color: yellow;
		}
		a{
			text-decoration: none;
		}
	</style>
</head>
<body>
	<%
		HttpSession session1=request.getSession();
		User user=(User)session1.getAttribute("quan_user");
		if(user==null){	%>
		<a href="login.jsp">您还没有登入,是否先登入?</a>
	<%  
		}else{%>
		 <a href="/Doem-2019-12-7/RemoveAttributeServlet">欢迎用户：<%= user.getUser()%>&nbsp;&nbsp;请问退出吗？</a><br>
		 <a href="/Doem-2019-12-7/jsp/shoppingcart.jsp">用户：<%=user.getUser() %> 购物车</a>
	<%   System.out.println("user.getName()="+user.getUser());
	
		}
		%>
		<div align="center">
			<table border="1px" cellpadding="10px" cellspacing="0px" width="500px" class="table_class">
				<thead class="thead_class">欢迎进入小白购物</thead>
				<tr>
					<td>书名</td>
					<td>价格</td>
					<td>数量</td>
					<td>购物车</td>
				</tr>
				
				<% 	 Merchandisedao m=new Merchandisedao();
					 List<Merchandise> showAllBook = m.showAllBook();
					 for (Merchandise merchandise : showAllBook) {
						System.out.println(merchandise.getId()+"==");
						Integer id = merchandise.getId();
				%>	
				<tr>
					<td><%=merchandise.getBookname() %></td>
					<td><%=merchandise.getPrice() %></td>
					<td><%=merchandise.getNumber() %></td>
					<td><a href="/Doem-2019-12-7/ShoppingCartServlet?bid=<%=merchandise.getId()%>&bname=<%=merchandise.getBookname()%>">
					加入购物车</a></td>
					
				</tr>
				<%  
					}
				%>
				
			</table>
			<% 
			String succeed =(String)session1.getAttribute("succeed");
			if(succeed==null){
				succeed=" ";
			}
			%>
			<span id="img_span"><%=succeed %></span>
				<% 
				succeed=" ";
			%>
		</div>
		<script type="text/javascript">
		//清楚添加购物车的提示
			var img_span = document.getElementById ("img_span");
			function chang_img_span(){
				if(img_span.value!=""){
					img_span.innerHTML="";
				}
			}        
			setInterval(chang_img_span,3000);
		<%	session.removeAttribute("succeed");	%>
		</script>
</body>
</html>