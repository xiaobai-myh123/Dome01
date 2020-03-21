package cn.whcm.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/GetphoneServlet")
public class GetphoneServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.创建一个对象 在内存中存取照片（验证码照片对象）
		// 照片大小
		int width = 100;
		int height = 50;
		char[] chyzm = new char[4];
		// 字体样式颜色
		String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑" };
		// 定义照片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 2.美化照片
		Graphics2D g = image.createGraphics();
		// 2.1填充背景颜色
		g.setColor(Color.pink);
		g.fillRect(0, 0, width, height);
		// 2.2画边框
		g.setColor(Color.blue);
		g.drawRect(0, 0, width - 1, height - 1);
		// 2.3写验证码
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		Random d = new Random();
		for (int i = 1; i <= 4; i++) {

			// 设置随机颜色
			int x1 = d.nextInt(150);
			int x2 = d.nextInt(150);
			int x3 = d.nextInt(150);
			// 设置画笔颜色
			g.setColor(new Color(x1, x2, x3));

			// 字体样式
			String strFont1 = fontNames[d.nextInt(fontNames.length)];

			// 字体大小
			int size = d.nextInt(5) + 16;
			// 设置字体样式
			g.setFont(new Font(strFont1, d.nextInt(4), size));

			int index = d.nextInt(str.length());
			char ch = str.charAt(index);
			g.drawString(ch + "", i * 20 - 5, height / 3 + d.nextInt(5) + 13);
			chyzm[i-1]=ch;
			
		}
		// 2.4 划线
		for (int i = 0; i < 5; i++) {
			// 设置随机颜色
			int x1 = d.nextInt(256);
			int x2 = d.nextInt(256);
			int x3 = d.nextInt(256);
			g.setColor(new Color(x1, x2, x3));
			int x11 = d.nextInt(width);
			int y11 = d.nextInt(height);
			int x22 = d.nextInt(width);
			int y22 = d.nextInt(height);
			g.drawLine(x11, y11, x22, y22);
		}
		// 3.将照片输出到屏幕展示
		System.out.println(chyzm);
		System.out.println("得到照片的servlet被访问了");
		ImageIO.write(image, "jpg", response.getOutputStream());

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
