/**
 * @ahthor zhaocm
 * @date 2017年7月12日
 * @time 下午9:05:38
 */
package com.zhao.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author zhaocm
 * @data 2017年7月12日
 * @time 下午9:05:38
 */
public class Base {

	protected String driver = "com.mysql.jdbc.Driver"; // 驱动包名称
	// 原数据库相关
	protected Connection conn = null; // 声明connection对象
	protected String url = ""; // url指向要访问数据库名jzsoft
	protected String user = ""; // 数据库用户名
	protected String pwd = ""; // 数据库密码
	protected Statement stmt = null;

	// 新数据库相关
	protected Connection conn1 = null; // 声明connection对象
	protected String url1 = ""; // url指向要访问数据库名jzsoft
	protected String user1 = ""; // 数据库用户名
	protected String pwd1 = ""; // 数据库密码
	protected Statement stmt1 = null;
	protected DatabaseMetaData dbmata = null;

	public DatabaseMetaData getDbmata() {
		return dbmata;
	}

	public void setDbmata(DatabaseMetaData dbmata) {
		this.dbmata = dbmata;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public Connection getConn1() {
		return conn1;
	}

	public void setConn1(Connection conn1) {
		this.conn1 = conn1;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public Statement getStmt1() {
		return stmt1;
	}

	public void setStmt1(Statement stmt1) {
		this.stmt1 = stmt1;
	}

	public void getconnection() {
		// 遍历查询结果集
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, user, pwd);
			conn1 = (Connection) DriverManager.getConnection(url1, user1, pwd1);
			if (!conn.isClosed()) {
				System.out.println("原数据库连接成功！");
			} else {

				System.out.println("原数据库连接失败！");
			}

			if (!conn1.isClosed()) {
				System.out.println("新数据库连接成功！");
			} else {

				System.out.println("新数据库连接失败！");
			}
			stmt = conn.createStatement();
			stmt1 = conn1.createStatement();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("连接异常，检查填写数据是否有误！");
		}
	}

}
