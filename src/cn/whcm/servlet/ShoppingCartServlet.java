package cn.whcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.User;
import cn.whcm.dao.Orderlistdao;


/**
 * 
 * @author 莫耀华
 *	购物车的servelt   设计到订单表        Orderlistdao_td
 */
public class ShoppingCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决字符集问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//判断用户是否登入  
		HttpSession session = request.getSession();
		//获取用户的id和名字 书名字和书id
		User user = (User)session.getAttribute("quan_user");
		
		//和判断的用户true和false
		Boolean addUserBoolean=(Boolean)session.getAttribute("addUserBoolean");
		if(addUserBoolean!=null&&addUserBoolean!=false&&user!=null) {
			//1.登入成功
					/**
					 * 获取书的id 和书的名字
					 */
			System.out.println("ShoppingCartServlet--->"+user);
			System.out.println("ShoppingCartServlet--->"+addUserBoolean);
			String bname=request.getParameter("bname");
			System.out.println("bname="+bname);
			String bid=request.getParameter("bid");
			System.out.println("bid="+bid);
			//插入数据   Orderlistdao_td
			Orderlistdao odao = new Orderlistdao();
			try {
				boolean addBookById = odao.addBookById(user, Integer.parseInt(bid), bname);
				if(addBookById) {
					session.setAttribute("succeed", bname+"已添加购物车");
					//request.getRequestDispatcher("jsp/Main.jsp").forward(request, response);
					response.sendRedirect("jsp/Main.jsp");
					return;
				}else {
					session.setAttribute("succeed", bname+"添加购物车失败");
					//request.getRequestDispatcher("jsp/Main.jsp").forward(request, response);	
					response.sendRedirect("jsp/Main.jsp");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//
		}else {
			//2.登入失败  
			request.setAttribute("error", "请先登入");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			return;
			//跳转到登入界面
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
