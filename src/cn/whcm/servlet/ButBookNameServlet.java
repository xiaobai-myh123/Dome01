package cn.whcm.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.User;
import cn.whcm.utils.JdbcUtils;
/**
 * 	 类作用：BuyBookNameServlet  
 * 		判断商品图书是否有存货 
 * 			如果有则购买
 * 			反之则不买
 * @author 莫耀华
 *
 */
//@WebServlet("/ButBookNameServlet")
public class ButBookNameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//设置字符
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//判断用户是否登入
			HttpSession session = request.getSession();
			User quan_user = (User) session.getAttribute("quan_user");//session.setAttribute("quan_user",quan_user);//用户全信息
			System.out.println("ButBookNameServlet="+quan_user);
			if(quan_user!=null) {
				//用户已经登入
				//获取用户信息和图书bid和bname
				Integer bid = Integer.parseInt(request.getParameter("bid"));
				System.out.println("ButBookNameServlet-->bid="+bid);
				String bname = request.getParameter("bname");
				//创建Map来存用户信息和用户购买书籍
				if(quan_user==null||bid==null||bname==null) {
					System.out.println("获取用户或者bid或者bname为空");
					return;
				}
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				String url = "jdbc:mysql://127.0.0.1:3306/Dome?useSSL=false";
				String user1 = "root";
				String password = "123456";
				Double price = null;
				String presentation= null;
				
				String sql ="SELECT * from merchandise_tb where id=?";
				ResultSet rs = null;
				PreparedStatement ps = null;
				Connection conn = null;
				//查出订单表的信息
				try {
					conn = DriverManager.getConnection(url, user1, password);
					if(conn!=null) {
						ps = conn.prepareStatement(sql);
						ps.setInt(1, bid);
						rs = ps.executeQuery();
						if(rs.next()) {
							price = rs.getDouble("price");
							presentation = rs.getString("presentation");
							System.out.println(price+presentation);
						}
						
					}else {
						System.out.println("conn="+conn);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						if(rs!=null) {rs.close();}
						if(ps!=null) {ps.close();}
						if(conn!=null) {conn.close();}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//___________________________________________________
				Map<String,Object> map = new HashMap();
				map.put("price",price);
				map.put("presentation",presentation);
				map.put("user",quan_user);
				map.put("bid",bid);
				map.put("bname", bname);
				System.out.println(map+"的长度为"+map.size());
				session.setAttribute("mapUserBidBname", map);
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
				//___________________________________________________
			}else {
				//如果没有登入 进入购物车  则跳入到login.jsp
				request.setAttribute("error", "请先登入");
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
				return;
			}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
