package com.zhao.www;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhao.util.fuzhibiaojiegou;
import com.zhao.util.huoqubiaoxinxi;
import com.zhao.util.quickimport;

public class sqlimport {

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		quickimport qi = new quickimport();
		qi.qimport("jdbc:mysql://localhost:3306/jzsoft", "jzsoft", "root", "123456",
				"jdbc:mysql://localhost:3306/test1", "test1", "root", "123456");
		long b = System.currentTimeMillis();
		System.out.println("数据库导入完成,用时：" + (b - a) + "毫秒。");

		// 复制表结构
		// fuzhibiaojiegou fz = new fuzhibiaojiegou();
		// fz.fuzhubiao("jdbc:mysql://localhost:3306/jzsoft", "root", "123456",
		// "jdbc:mysql://localhost:3306/test1", "root", "123456");

		// 获取表信息
		// huoqubiaoxinxi xx = new huoqubiaoxinxi();
		// xx.huoqushujukubiao("jdbc:mysql://localhost:3306/jzsoft", "root",
		// "123456");
	}
}
