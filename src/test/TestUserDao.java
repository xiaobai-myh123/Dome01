package test;

import java.sql.SQLException;
import java.util.List;

import cn.whcm.bean.User;
import cn.whcm.dao.Userdao;

/**
 * 					测试类 测试数据库的增删该是否有错
 * @author 莫耀华
 *
 */
public class TestUserDao {
	public static void main(String[] args) throws Exception {
		Userdao userdao=new Userdao();
		//测试add
//		User user= new User("b","b");
//		boolean addUser = userdao.addUser(user);
//		if(addUser) {
//			System.out.println("add yes");
//		}else {
//			System.out.println("add no");
//		}
		//--------------------------------------
		//测试查询所有
//		List findAll = userdao.findAll();
//		for (Object object : findAll) {
//			System.out.println(object);
//		}
		//--------------------------------------
		//测试查询一个
//		User findById = userdao.findById(1);
//		System.out.println(findById);
		//--------------------------------------
		//修改
//		User user = new User("c","c");
//		boolean updateByUser = userdao.updateByUser(user,1);
//		System.out.println(updateByUser);
		
		boolean deleteById = userdao.deleteById(4);
		System.out.println(deleteById);
	}
}
