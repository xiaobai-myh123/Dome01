package cn.whcm.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.whcm.bean.Root;
import cn.whcm.utils.JdbcUtils;

/**
 *	root 用户的登入
 * @author 莫耀华
 *
 */

public class Rootdao {

	//返回root  这个方法判断root是否登入
	public Root getRoot(Root r) throws Exception {
		Root root=null;
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="select * from root where name=? and password=?";
		root = (Root)runner.query(sql, new BeanHandler<Root>(Root.class), new Object[] {r.getName(),r.getPassword()});
		System.out.println("Rootdao="+root);
		return root;
	}
}
