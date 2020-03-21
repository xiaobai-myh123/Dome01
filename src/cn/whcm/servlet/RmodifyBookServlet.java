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


/**
 *   从数据库拿    书籍的servlet（即将修改的）
 * @author 莫耀华
 *
 */

public class RmodifyBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//统一设置字符编码
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root用户全信息
		if(root!=null) {
			//已经登入
			//获取书籍id和书籍名字
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			String bname = request.getParameter("bname");
			//从数据库拿全部信息
			Merchandisedao m = new Merchandisedao();
			try {
				Merchandise merchandiseByID = m.getMerchandiseByID(bid, bname);//拿来的商品全部信息
				if(merchandiseByID==null) {
					System.out.println("拿来的商品全部信息为空     一般不可能为空");
					session.setAttribute("succeed", "拿来的商品全部信息为空     一般不可能为空");
//					request.getRequestDispatcher("/jsp/Rmain.jsp").forward(request, response);
					response.sendRedirect("/jsp/Rmain.jsp");
					return;
				}else {
					//把商品信息设置成session
					session.setAttribute("merchandiseByID", merchandiseByID);//一个商品的全信息
//					Root root = (Root) session.getAttribute("root_mysql");
					//然后在修改的modif.jsp中修改
					response.sendRedirect("jsp/modify.jsp");
					return;
					//修改成功   则显示修改成功  否则显示修改失败   修改失败原因   数据没有填写js判断
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
