package org.xiongmaotailang.util;

import java.security.MessageDigest;
/**
 * 
 * @author xiongmaotailang
 *
 */
public class TableRouter {

	public static void main(String[] args) {
		System.out.println(getTable("7130841", "test_uuid", 8));
		System.out.println(getTable1(11130841, "test_uuid"));
	}
	
	// 使用移位分表
	/*
	 * 如果我们预估我们系统的用户是100亿，单张表的最优数据量是100万，
	 * 那么我们就需要将UID移动20来确保每个表是100万的数据，保留用户表（user_xxxx）四位来扩展1万张表
	 */
	public static String getTable1(int uid, String prefix) {
		
		return prefix + "_" + String.format("%04d",(uid>>20));
	}
	
	// 使用md5做hash水平分表
	public static String getTable(String mark, String prefix, int num) {
		if (num == 0)
			return prefix;
		String temp = md5(mark).substring(0, 2);
		int hexdec = Integer.parseInt(temp, 16);// 16转成10进制
		int index = hexdec % num + 1;
		return prefix + "_" + index;
	}

	// 提供和php->md5一样的功能
	private static String md5(String txt) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(txt.getBytes("GBK")); // 问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
			StringBuffer buf = new StringBuffer();
			for (byte b : md.digest()) {
				buf.append(String.format("%02x", b & 0xff));
			}
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
