package cn.whcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.Root;
import cn.whcm.dao.Rootdao;
import cn.whcm.utils.StringIsiEmpty;

/**
 * 这个类是来判断root用户的
 *  关于商品的增删改查
 * @author 莫耀华
 *
 */
public class RloginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//统一设置字符编码
//		response.setHeader("text/html", "charset=uft-8");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取页面账户和密码
		String login_jsp_user=null;
		login_jsp_user=request.getParameter("user");
		String login_jsp_password=null;
		login_jsp_password=request.getParameter("password");
		request.setAttribute("login_jsp_user", login_jsp_user);
		request.setAttribute("login_jsp_password", login_jsp_password);
		//判断账户和密码是否为空
		if(StringIsiEmpty.isEmpty(login_jsp_password)||StringIsiEmpty.isEmpty(login_jsp_user)) {
			System.out.println("账户或密码为空");
			request.setAttribute("error", "账户或密码为空");
			System.err.println(request.getAttribute("error"));
			request.getRequestDispatcher("/jsp/Rlogin.jsp").forward(request, response);
			return;
		}
		//从root数据库拿来的数据
		//-----------------------------------
		Root root = new Root(login_jsp_user,login_jsp_password);
		Rootdao rootdao= new Rootdao();
		try {
			Root root_mysql = rootdao.getRoot(root);
			if(root_mysql!=null) {
				//登入成功
				//登入成功 记住账号
				HttpSession session = request.getSession();
				session.setAttribute("root_mysql", root_mysql);
				response.sendRedirect("jsp/Rmain.jsp");
				return;
			}else {
				//登入失败
				System.out.println("登入失败");
				request.setAttribute("error", "账户或密码输入错误");
				request.getRequestDispatcher("/jsp/Rlogin.jsp").forward(request, response);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//-----------------------------------
		//从root数据库拿来的数据
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
