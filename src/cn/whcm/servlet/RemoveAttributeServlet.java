package cn.whcm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	退出的时候清楚 seesion     seesion判断用户是否登入 
 * @author 莫耀华
 *
 */
public class RemoveAttributeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("addUserBoolean"));//移除登入标志
		System.out.println("---         ---");
		session.removeAttribute("mapUserBidBname");	
		session.removeAttribute("addUserBoolean");	
		session.removeAttribute("showAllBook_session");
		session.removeAttribute("merchandiseByID");
		session.removeAttribute("root_mysql");
		session.removeAttribute("succeed");
		session.removeAttribute("quan_user");
		session.removeAttribute("delbook");
		session.removeAttribute("user");							//移除用户
		System.out.println("-----        -");
		System.out.println(session.getAttribute("addUserBoolean"));
		Object getUserSession = session.getAttribute("addUserBoolean");
		System.out.println("退出成功");
		if(getUserSession==null) {
			response.sendRedirect("/Doem-2019-12-7/jsp/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
