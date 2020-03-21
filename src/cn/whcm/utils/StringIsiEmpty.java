package cn.whcm.utils;

public class StringIsiEmpty {

	public static boolean isEmpty(String str) {
		if(str==null||str.trim()=="") {
			return true;
		}
		return false;
	}
	
}
