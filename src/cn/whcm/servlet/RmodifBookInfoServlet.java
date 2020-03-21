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
 * 将modify.jsp的数据写入到数据库中
 * @author 莫耀华
 *
 */

public class RmodifBookInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//统一设置字符编码
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root用户全信息
		if(root!=null) {
			//登入
				//获取modify.jsp的数据
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			String bname = request.getParameter("bname");
			String presentation = request.getParameter("presentation");
			Double price = Double.parseDouble(request.getParameter("price"));
			Integer number = Integer.parseInt(request.getParameter("number"));
				//将获取的数据写入到数据库中
			Merchandise m = new Merchandise(bid, bname, null, price, presentation, number);
			Merchandisedao mdao= new Merchandisedao();
			boolean addBook;
			try {
				addBook = mdao.addMerchandise(m);
				if(addBook) {
					//修改成功
					System.out.println(bname+"修改成功,RmodifBookInfoServlet");
					session.setAttribute("succeed", bname+"信息修改成功");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}else {
					//修改失败  一般不会失败  特殊情况再说  先处理再说
					System.out.println(bname+"修改失败,RmodifBookInfoServlet");
					session.setAttribute("succeed", bname+"修改失败");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
