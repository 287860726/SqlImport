package com.zhao.www;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhao.util.fuzhibiaojiegou;

public class sqlimport {

	public static void main(String[] args) {
		
		//复制表结构
		fuzhibiaojiegou fz = new fuzhibiaojiegou();
		fz.fuzhubiao("jdbc:mysql://localhost:3306/jzsoft", "root", "123456", "jdbc:mysql://localhost:3306/test1", "root", "123456");
	}
}
