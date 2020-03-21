package test;

import java.util.List;

import cn.whcm.bean.Merchandise;
import cn.whcm.dao.Merchandisedao;

/*
 * 	测试TestMerchandisedao方法
 * 
 */
public class TestMerchandisedao {
	public static void main(String[] args) throws Exception {
		//测试全部返回商品
		Merchandisedao m=new Merchandisedao();
		List<Merchandise> showAllBook = m.showAllBook();
		for (Merchandise merchandise : showAllBook) {
			System.out.println(merchandise);
		}
		
		//增加商品
//		Merchandise m1=new Merchandise();
//		m1.setBookname("a");
//		//String sql = "insert into merchandise_tb(bookname,price,presentation,number) value(?,?,?,?)";
//		m1.setPrice(12.2);
//		m1.setPresentation("aaa");
//		m1.setNumber(1);
//		boolean addBook = m.addBook(m1);
//		System.out.println(addBook);
		
		//删除单个删品
		
//		boolean delBookById = m.delBookById(9);
		
	} 
}
