package cn.whcm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.whcm.bean.Merchandise;
import cn.whcm.dao.Merchandisedao;

/**
 * 	展示(购物车)的servlet
 *  @author 莫耀华
 *
 */


public class ShowBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取数据库所图书
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Merchandisedao merchandisedao = new Merchandisedao();
		try {
			List<Merchandise> showAllBook = merchandisedao.showAllBook();
			//所有图书
			HttpSession session = request.getSession();
			session.setAttribute("showAllBook_session", showAllBook);
			request.setAttribute("showAllBook", showAllBook);
			System.out.println("ShowBookServlet");
//			request.getRequestDispatcher("jsp/Main.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/jsp/Main.jsp");
			
			System.out.println(request.getContextPath()+"/jsp/Main.jsp");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
