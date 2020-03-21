package cn.whcm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.Merchandise;
import cn.whcm.bean.Root;
import cn.whcm.bean.User;
import cn.whcm.dao.Merchandisedao;

/**
 * 	类作用
 * 	删除图书 在merchandise_tb表中
 * 
 * @author 莫耀华
 *
 */

public class RdelbookMerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置字符
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//判断用户是否登入
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root用户全信息
		if(root!=null) {
			//登入成功
			//删除图书   根据图书id来的
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			String bname = request.getParameter("bname");
			System.out.println("RdelbookMerServlet-->bid="+bid+" "+bname);
			//从数据库取书籍
			Merchandisedao mdao = new Merchandisedao();
			try {
				boolean delBookById = mdao.delBookById(bid);
				if(delBookById) {
					//删除成功
					System.out.println(bid+bname+"删除成功");
					session.setAttribute("succeed", bname+"删除成功");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}else {
					//删除不成功
					System.out.println(bid+bname+"删除失败");
					session.setAttribute("succeed", bname+"删除失败");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}else {
			//没有登入
			request.setAttribute("error", "请先登入");
			request.getRequestDispatcher("jsp/Rlogin.jsp").forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
