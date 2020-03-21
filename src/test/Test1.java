
package test;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.whcm.utils.JdbcUtils;

public class Test1 {
 
	
	@Test
	public void test() throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="select count(*) from merchandise_tb";
		long query = runner.query(sql, new ScalarHandler<Long>());
		System.out.println(query);
		
		//List<Map<String, Object>> query = runner.query(sql, new MapListHandler());
	}
}
