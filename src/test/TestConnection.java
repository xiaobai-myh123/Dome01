package test;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import cn.whcm.utils.JdbcUtils;

public class TestConnection {

	public static void main(String[] args) throws Exception {
		Connection con =null;
		for(int i=0;i<11;i++) {			
			con= JdbcUtils.getCon();	
			System.out.println(con);
			if(i==3) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
//		Connection con =null;
//		con= JdbcUtils.getCon();	
//		String sql="insert into test values(?)";
//		PreparedStatement ps = con.prepareStatement(sql);
//		ps.setString(1, "ÄªÒ«»ª");
//		ResultSet rs=null;
//		int i = ps.executeUpdate();
//		if(i>0) {
//			sql="select * from test";
//			ps = con.prepareStatement(sql);
//			rs = ps.executeQuery();
//			if(rs.next()) {
//				System.out.println(rs.getObject(1));
//			}
//		}
//		rs.close();
//		ps.close();
//		con.close();
//		String str="ÄªÒ«»ª";
//		try {
//			byte[] bytes = str.getBytes("utf-8");
//			String str1 = new String(bytes);
//			
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
}
