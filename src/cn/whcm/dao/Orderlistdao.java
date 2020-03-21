package cn.whcm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.whcm.bean.Merchandise;
import cn.whcm.bean.Orderlist;
import cn.whcm.bean.User;
import cn.whcm.utils.JdbcUtils;

/***
 * 	
 * @author 莫耀华
 *	时间：2019-12-8
 *	类作用 ：顶单表的增删改查
 *	购物车
 *	
 *	orderlist_tb              对订单表增删         
 *	
 *	当用户支付钱购买购物车的东西时   商品删除 
 *	下单时增加 
 *	类中方法
 *	add 和del 
 */
public class Orderlistdao {
	
	//下顶单
	public boolean addBookById(User user,int id,String bname) throws Exception {
		//获取用户和id   谁买书   买那一本书
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "insert into orderlist_tb(uid,uname,bid,bname) values(?,?,?,?)";
		int update = runner.update(sql, new Object[] {user.getId(),user.getUser(),id,bname});
		if(update>0) {
			System.out.println(user.getUser()+"下单"+update+"条数据");
			return true;
		}else {
			System.out.println("下单失败");
		}
		return false;
	} 

	// 删除订单
	public boolean delBookById(User user, int id, String bname) throws Exception {
		// 获取用户和id 谁买书 买那一本书
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "delete from orderlist_tb where uid=? and uname=? and bid=? and bname=?";
		int update = runner.update(sql, new Object[] { user.getId(), user.getUser(), id, bname});
		if (update > 0) {
			System.out.println(user.getUser() + "删除" + update + "条数据");
			return true;
		} else {
			System.out.println("删除失败");
		}
		return false;
	} 
	
	//查询   谁买了什么东西
	public List FindBookById(User user) throws Exception {
		// 获取用户和id 谁买书 买那一本书
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="select * from orderlist_tb where uid=? and uname=? GROUP BY bid";
		System.err.println("FindBookById"+user);
		List<Orderlist> list = runner.query(sql,new BeanListHandler<Orderlist>(Orderlist.class),new Object[] {user.getId(),user.getUser()});
		if(list!=null) {
			System.out.println(list.size());
		}else {
			System.out.println("FindBookById查询"+"失败");
		}
		return list;
	} 
	
	
//	String sql = "select * from tb_book where name like ";
//	sql = sql + "\'%" + textFieldFindName.getText() + "%\'";
	
	
	
}
