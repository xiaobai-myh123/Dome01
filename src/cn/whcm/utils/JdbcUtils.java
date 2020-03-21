package cn.whcm.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/***
 * 		数据库工具类
 * 		获取数据库    	 连接
 * 				           数据源连接
 * 			 
 * @author 莫耀华
 *
 */
public class JdbcUtils {
	private static DataSource ds=null;
	
	public static DataSource getDataSourse() {
		ComboPooledDataSource cds = new ComboPooledDataSource();
		if(ds==null) {
			ds=cds;
		}
		return ds;
	}
	public static Connection getCon() {
		Connection con = null;
		if(ds==null) {
			ds=getDataSourse();
		}
		try {
			con=ds.getConnection();
			if(con==null) {
				System.out.println("数据库连接失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
