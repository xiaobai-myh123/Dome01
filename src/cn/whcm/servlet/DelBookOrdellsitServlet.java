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
 * 类作用：删除想要的订单
 * 
 * @author 莫耀华 当用户删除时 删除数据库 orderlist_tb中所有
 */
public class DelBookOrdellsitServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//判断用户是否登入
		HttpSession session = request.getSession();
		User quan_user = (User) session.getAttribute("quan_user");//session.setAttribute("quan_user",quan_user);//用户全信息
		System.out.println("DelBookOrdellsitServlet="+quan_user);
		if(quan_user!=null) {
			//已经登入  获取传进来的bid值  然后删除orderlist_tb中的数据
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			System.out.println("DelBookOrdellsitServlet-->bid="+bid);
			String bname = request.getParameter("bname");
			Orderlistdao orderlistdao = new Orderlistdao();
			try {
				boolean delBookById = orderlistdao.delBookById(quan_user, bid, bname);
				if(delBookById) {
					System.out.println(bname+"删除成功");
					session.setAttribute("delbook", bname+"删除成功");
					response.sendRedirect("jsp/shoppingcart.jsp");
					return;
				}else {
					System.out.println(bname+"删除失败");
					session.setAttribute("delbook", bname+"删除失败");
					response.sendRedirect("jsp/shoppingcart.jsp");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
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
