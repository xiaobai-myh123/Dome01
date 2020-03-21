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
import cn.whcm.dao.Merchandisedao;


public class RInsertBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//统一设置字符编码
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root用户全信息
		if(root!=null) {
			//登入
			//获取网页数据
			String bookname = request.getParameter("bookname");
			Double price = Double.parseDouble(request.getParameter("price"));
			String presentation = request.getParameter("presentation");
			Integer number = Integer.parseInt(request.getParameter("number"));
			//插入数据库
			Merchandise m = new Merchandise(null, bookname, null, price, presentation, number);
			Merchandisedao mdao = new Merchandisedao();
			try {
				boolean addBook = mdao.addBook(m);
				if(addBook) {
					//插入成功
					System.out.println("添加商品成功");
					session.setAttribute("succeed", "添加商品成功");
					response.sendRedirect("jsp/Raddbook.jsp");
					return;
					
				}else {
					//插入失败 一般不会失败  特殊处理
					System.out.println("添加商品全部信息为空     一般不可能为空");
					session.setAttribute("succeed", "添加商品全部信息为空     一般不可能为空");
//					request.getRequestDispatcher("/jsp/Rmain.jsp").forward(request, response);
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//回到Raddbook.jsp
			
		}else {
			//没有登入
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
