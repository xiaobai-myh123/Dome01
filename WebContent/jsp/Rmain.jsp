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
<title>商品管理员界面</title>
<style type="text/css">
div {
	margin-top: 100px;
	border: 1px solid red;
}

table {
	text-align: center;
	background-color: yellow;
	width: 900px;
}

a {
	text-decoration: none;
}

</style>
</head>
<body>
	<%
	//判断是否root用户是否登入
	HttpSession session1=request.getSession();
	Root rootuser=(Root)session1.getAttribute("root_mysql");
	if(rootuser==null){%>	
 		<a href="Rlogin.jsp">您还没有登入,是否先登入?</a>
<%   	return;
	}else{%>
		<a href="/Doem-2019-12-7/RemoveAttributeServlet">欢迎尊贵的：<%=rootuser.getName() %>用户&nbsp;&nbsp;请问退出吗？</a><br>
		<a href="Raddbook.jsp">添加书籍</a>
<%		
	}
%>

	<div align="center">
		<table border="1px" cellpadding="10px" cellspacing="0px" width="500px"
			class="table_class">
			<thead class="thead_class">欢迎以<%=rootuser.getName() %>用户进来,你可以随便修改书籍信息	</thead>
			<tr>
				<td>id</td>
				<td>bookname</td>
				<td>price</td>
				<td>presentation</td>
				<td>number</td>
				<td>modify</td>
				<td>delete</td>
			</tr>
			<%
				//从数据库拿书籍
				Merchandisedao m=new  Merchandisedao();
				List<Merchandise> list=(List<Merchandise>)m.showAllBook();
				for(Merchandise tmp:list){
			%>
					<tr>
						<td><%=tmp.getId() %></td>
						<td><%=tmp.getBookname() %></td>
						<td><%=tmp.getPrice()%></td>
						<td><%=tmp.getPresentation()%></td>
						<td><%=tmp.getNumber()%></td>
						<td><a href="/Doem-2019-12-7/RmodifyBookServlet?bid=<%=tmp.getId() %>&bname=<%=tmp.getBookname() %>">修改</a></td>
						<td><a href="/Doem-2019-12-7/RdelbookMerServlet?bid=<%=tmp.getId() %>&bname=<%=tmp.getBookname() %>">删除</a></td>
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
			<span id="img_span"><%=succeed %><span>
			<% 
				succeed=" ";
			%>
		</div>
		<script type="text/javascript">
		//清楚添加root车的提示
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