package test;

import java.util.Arrays;
import java.util.List;

import cn.whcm.bean.Merchandise;
import cn.whcm.bean.User;
import cn.whcm.dao.Merchandisedao;
import cn.whcm.dao.Orderlistdao;
import cn.whcm.dao.Userdao;

/**
 *       	测试  Orderlist对数据库的操作是否成功
 * @author 莫耀华
 *
 */
public class TestOrderlist {
	public static void main(String[] args) throws Exception {
		

		// 删除订单
//		Orderlistdao o = new Orderlistdao();
//		Userdao user = new Userdao();
//		User findByuser = user.findById(1);
//		int id=1;
//		
//		boolean delBookById = o.delBookById(findByuser, id, "java程序设计");
//		
		
	

//		查询   谁买了什么东西
		
		Orderlistdao o = new Orderlistdao();
		Userdao user = new Userdao();
	
		
		User findByuser = user.findById(1);
		System.out.println(findByuser);
		
		int id=1;
		List list = o.FindBookById(findByuser);
		System.out.println(list);
//		System.out.println(list);
//		
//		User user = new User()
//		o.FindBookById(user, bid, bname);
//		
		
		//增加商品
		//public boolean addBookById(User user,int id,String bname) throws Exception {
//		Userdao user = new Userdao();
//		User findByuser = user.findById(1);
//		
//		int id=1;
//		String bname="java程序设计";
//		boolean addBookById = o.addBookById(findByuser, id, bname);
		
	}
}
