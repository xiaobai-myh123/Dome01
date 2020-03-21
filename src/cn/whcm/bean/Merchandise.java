package cn.whcm.bean;
/**
 * @author 莫耀华
 *	时间：2019-12-8
 *	类功能描述：关于商品的实体类
 *
 *	Merchandise_tb
	书名 数量 编号                  价格    介绍
		 
		Id              主键         int               不为空             自增       
		bookname        书名        varchar		          不为空
		serial          编号        varchar			可以为空	               随机六位字符或者数字生成    
		price            doubl价格       e 			不为null
		presentation    介绍        varchar            可以为空          
		number          数量      int                  不为空           
		不使用基本数据类型是因为数据库 有些数据库为空 不能
 */
public class Merchandise {
	private Integer id;
	private String bookname;
	private String serial;
	private Double price;
	private String presentation;
	private Integer number;
	public Merchandise() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}



	@Override
	public String toString() {
		return "Merchandise [id=" + id + ", bookname=" + bookname + ", serial=" + serial + ", price=" + price
				+ ", presentation=" + presentation + ", number=" + number + "]";
	}

	public Merchandise(Integer id, String bookname, String serial, Double price, String presentation, Integer number) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.serial = serial;
		this.price = price;
		this.presentation = presentation;
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
