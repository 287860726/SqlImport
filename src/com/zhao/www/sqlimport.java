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
		System.out.println("���ݿ⵼�����,��ʱ��" + (b - a) + "���롣");

		// ���Ʊ�ṹ
		// fuzhibiaojiegou fz = new fuzhibiaojiegou();
		// fz.fuzhubiao("jdbc:mysql://localhost:3306/jzsoft", "root", "123456",
		// "jdbc:mysql://localhost:3306/test1", "root", "123456");

		// ��ȡ����Ϣ
		// huoqubiaoxinxi xx = new huoqubiaoxinxi();
		// xx.huoqushujukubiao("jdbc:mysql://localhost:3306/jzsoft", "root",
		// "123456");
	}
}
