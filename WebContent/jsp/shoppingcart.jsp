<%@page import="cn.whcm.utils.JdbcUtils"%>
<%@page import="cn.whcm.dao.Orderlistdao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="cn.whcm.bean.*"%>
<%@ page import="cn.whcm.dao.Merchandisedao"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="cn.whcm.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>购物车页面</title>
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
	<% 	return; }else{%>
		   <span>用户：<%= user.getUser()%>&nbsp;&nbsp;的购物车 </span>&nbsp;&nbsp;<br>
		   <a href="/Doem-2019-12-7/ShowBookServlet">返回商品</a>
		 <a href="/Doem-2019-12-7/RemoveAttributeServlet">退出</a><br>

	<%   System.out.println("user.getName()="+user.getUser());
	
		}
		%>
		<div align="center">
			<table border="1px" cellpadding="10px" cellspacing="0px" width="500px" class="table_class">
				<thead class="thead_class">小白的购物车</thead>
				<tr>
					<td>图书id</td>
					<td>图书名字</td>
					<td>图书价格</td>
					<td>购买</td>
					<td>删除</td>
				</tr>
				
				<%
				
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/Dome?useSSL=false";
					String user1 = "root";
					String password = "123456";
					//查出订单表的信息
				    Orderlistdao orderlistdao =new Orderlistdao();
				    List<Orderlist> list = orderlistdao.FindBookById(user);
					//SELECT price from merchandise_tb where id=1
					Connection conn = DriverManager.getConnection(url, user1, password);
					String sql ="SELECT price from merchandise_tb where id=?";
					Double price=null;
					PreparedStatement ps = conn.prepareStatement(sql);
					for(Orderlist tmp :list){
					//查出图书价格
						if(conn==null){
							System.out.println("conn==null");
						}
						ps.setInt(1,tmp.getBid());
						System.out.println(tmp.getBid());
						ResultSet rs =ps.executeQuery();
						if(rs.next()){
							price=rs.getDouble(1);
						}
				%>	<tr>
						<td><%=tmp.getBid() %></td>	
						<td><%=tmp.getBname() %></td>	
						<td><%=price %></td>	
						<td><a href="/Doem-2019-12-7/ButBookNameServlet?bid=<%=tmp.getBid()%>&bname=<%=tmp.getBname() %>">购买</a></td>	
						<td><a href="/Doem-2019-12-7/DelBookOrdellsitServlet?bid=<%=tmp.getBid()%>&bname=<%=tmp.getBname() %> ">删除<a></a></td>	

					</tr>
				<%		rs.close();
					}
					ps.close();
					conn.close();
				%>	
			</table>
			<%
				String msg=(String)session.getAttribute("delbook");
				if(msg==null){
					msg="";
				}
			%>
			<span id="img_span"><%=msg %></span>
		</div>
		<script type="text/javascript">
			var img_span = document.getElementById ("img_span");
			function chang_img_span(){
				if(img_span.value!=""){
					img_span.innerHTML="";
				}
			}        
			setInterval(chang_img_span,3000);
		<%	session.removeAttribute("delbook");	%>
		</script>
		
	
</body>
</html>