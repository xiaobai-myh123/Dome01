package cn.whcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.User;
import cn.whcm.dao.Userdao;
import cn.whcm.utils.StringIsiEmpty;
/**
 * 		这个是做登入判断的
 * 		如果登入   则进入成功页面
 * 		如果失败   则返回登入页面
 * 
 * @author 莫耀华
 *
 */
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//统一设置字符编码
		response.setHeader("text/html", "charset=uft-8");
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
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			System.out.println(request.getContextPath()+"/jsp/login.jsp");
			return;
		}
		
		System.out.println(login_jsp_user+"->>>"+login_jsp_password);
		//获取数据库账户和密码
		Userdao userdao=new Userdao();
		//根据页面的user和passwrod判
		
		User user=new User(login_jsp_user, login_jsp_password);
		String username=user.getUser();
		try {
			boolean addUserBoolean = userdao.checkUser(user);//判断是否登入
			//比较是否正确
			if(addUserBoolean) {
				//正确跳入正确页面
				
				HttpSession session = request.getSession();
				User quan_user=userdao.getQueryUser(user);
				//----------------------------------------------登入成功设置session
				//设置一个全信息用户
				session.setAttribute("quan_user",quan_user);				//用户全信息
				session.setAttribute("addUserBoolean", addUserBoolean);     //是否登入
				session.setAttribute("user", user);			 			    //登入用户
				//----------------------------------------------登入成功设置session
//				session.removeAttribute(name);
				System.out.println("登入成功");
				request.setAttribute("user_name_password", user);
				response.sendRedirect("jsp/Main.jsp");
				return;
			
			}else {
				//错误跳转到登入页面
				System.out.println("登入失败");
				request.setAttribute("error", "账户或密码输入错误");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
