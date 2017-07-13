package com.zhao.www;

import com.zhao.util.ImportData;
import com.zhao.util.QuickImport;

public class sqlimport {

	public static void main(String[] args) {

		//表结构不同，但是原来表没有的字段必须有默认值
		ImportData id = new ImportData();
		id.importdate("jdbc:mysql://localhost:3306/cesh", "cesh", "root", "123456", "jdbc:mysql://localhost:3306/test1",
				"test1", "root", "123456");

		// 快速导入表结构以及表数据
//		long a = System.currentTimeMillis();
//		QuickImport qi = new QuickImport();
//		qi.qimport("jdbc:mysql://localhost:3306/jzsoft", "jzsoft", "root", "123456",
//				"jdbc:mysql://localhost:3306/test1", "test1", "root", "123456");
//		long b = System.currentTimeMillis();
//		System.out.println("数据库导入完成,用时：" + (b - a) + "毫秒。");

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
