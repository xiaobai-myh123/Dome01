package cn.whcm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import cn.whcm.bean.User;
import cn.whcm.dao.Merchandisedao;
import cn.whcm.utils.JdbcUtils;

//买书的顶的servlet

public class BuyBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//判断用户是否登入
		HttpSession session = request.getSession();
		User quan_user = (User) session.getAttribute("quan_user");//session.setAttribute("quan_user",quan_user);//用户全信息
		System.out.println("BuyBookServlet="+quan_user);
		if(quan_user!=null) {
			//用户已经登入
			//获取用户信息和图书bid和bname
			Map<String,Object> map = (Map<String,Object>) session.getAttribute("mapUserBidBname");
			//初始化bid和biname  查看数量和网页带来的比较  
			Integer bid = (Integer) map.get("bid");
			String bname = (String) map.get("bname");
			System.out.println(bid+bname);
			Connection conn = JdbcUtils.getCon();
			String sql="select number from merchandise_tb where id=? and bookname=?";
			PreparedStatement ps=null;
			Integer number_sql=null;
			ResultSet rs =null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, bid);
				ps.setString(2, bname);
				rs= ps.executeQuery();
				if(rs.next()) {
					number_sql=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs==null)
						rs.close();
					if(ps==null)
						ps.close();
					if(conn==null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(number_sql==null) {
				System.out.println("bname+\"商品可能已被删除");
				session.setAttribute("msg_book_number", bname+"商品可能已被删除");
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
			} 
			Integer number = Integer.parseInt(request.getParameter("number"));//网页带的书籍数量
			System.out.println("number="+number+"number_sql="+number_sql);
			if(number_sql==0) {  //图书没有图书
				System.out.println(bname+"图书库存为0");
				session.setAttribute("msg_book_number", bname+"图书库存为0");
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
			}else if(number_sql>=number) {   //mysql 的图书比要买的图书多
				//购买成功  数据库书籍减少
				//---------------------------------------
				Merchandisedao mdao = new Merchandisedao();
				try {
					boolean delBookByIdName = mdao.delBookByIdName(bid, bname, number);
					if(delBookByIdName) {
						System.out.println("你买了"+bname+"图书为数量"+number);
						session.setAttribute("msg_book_number", "购买成功"+number+"本");
					}else {
						System.out.println("购买失败");
						session.setAttribute("msg_book_number", "购买失败"+number+"本");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//---------------------------------------
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
			}else {// 库存小于购买   不能买到书
				System.out.println("你买"+bname+"数量太多，库存不够number="+number);
				session.setAttribute("msg_book_number", "购买"+bname+"的书籍过多，"+"存储不够"+number+"本");
				response.sendRedirect("jsp/OrderInfo.jsp");
			}
		}else {
			//如果没有登入 进入购物车  则跳入到login.jsp
			request.setAttribute("error", "请先登入");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			return;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
