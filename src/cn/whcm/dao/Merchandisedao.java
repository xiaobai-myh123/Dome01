package cn.whcm.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.whcm.bean.Merchandise;
import cn.whcm.bean.User;
import cn.whcm.utils.JdbcUtils;

/**
 * 
 * @author 莫耀华	
 * 			    Merchandise
 *				商品表的	展示
 *						增加
 *						删除
 *						修改
 *	time：2019-12-9
 */

public class Merchandisedao {
	
	//返回所有商品
	public List<Merchandise> showAllBook() throws Exception {
		//获取用户和id   谁买书   买那一本书
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="select * from merchandise_tb";
		ArrayList<Merchandise> list = (ArrayList<Merchandise>)runner.query(sql, new BeanListHandler<Merchandise>(Merchandise.class));
		System.out.println("商品的个数为"+list.size());
		return list;
//		= runner.query(sql,new ScalarHandler("name"));
	} 
	
	//删除删品
	public boolean delBookById(Integer id) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="delete from merchandise_tb where id=?";
		int update = runner.update(sql, new Object[] {id});
		if(update>0) {
			System.out.println("删除成功");
			return true;
		}
		System.out.println("删除失败");
		return false;
	}
	
	//减少商品
	public boolean delBookByIdName(Integer id,String bookname,Integer number) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql ="update merchandise_tb set number=number-AND where id=? and bookname=?";
		sql=sql.replace("AND", number+"");
		int update = runner.update(sql,new Object[] {id,bookname});
		if(update>0) {
			System.out.println("删除成功");
			return true;
		}
		System.out.println("删除失败");
		return false;
	}
	
	//增加商品//insert into merchandise_tb(bookname,price,presentation,number) value("a",12.2,"介绍",12)
	public boolean addBook(Merchandise m) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "insert into merchandise_tb(bookname,price,presentation,number) values(?,?,?,?)";
		int update = runner.update(sql,new Object[] {m.getBookname(),m.getPrice(),m.getPresentation(),m.getNumber()});
		if(update>0) {
			System.out.println("添加成功");
			return true;
		}
		System.out.println("添加失败");
		return false;
	}
	//根据bid和bname拿一个全部商品信息    
	public Merchandise getMerchandiseByID(Integer bid,String bname) throws SQLException{
		Merchandise m = new Merchandise();
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "select * from merchandise_tb where id=? and bookname=?";
		m = (Merchandise)runner.query(sql, new BeanHandler<Merchandise>(Merchandise.class) , new Object[] {bid,bname});
		//Integer query = runner.query(sql, new ScalarHandler<Integer>());
		if(m==null) {
			System.out.println("Merchandise方法拿到的数据为空    给数据从数据库拿来的参数有问题");
		}
		return m;
	}
	
	//修改商品
	public boolean addMerchandise(Merchandise m) throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="update merchandise_tb set bookname=?,price=?,presentation=?,number=? where id=?";
		int update = runner.update(sql, new Object[] {m.getBookname(),m.getPrice(),m.getPresentation(),m.getNumber(),m.getId()});
		if(update>0) {
			System.out.println("addMerchandise添加成功");
			return true; 
		}
		System.out.println("addMerchandise添加失败");
		return false;
	}
	
	
}
