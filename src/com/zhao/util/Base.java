/**
 * @ahthor zhaocm
 * @date 2017��7��12��
 * @time ����9:05:38
 */
package com.zhao.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author zhaocm
 * @data 2017��7��12��
 * @time ����9:05:38
 */
public class Base {

	protected String driver = "com.mysql.jdbc.Driver"; // ����������
	// ԭ���ݿ����
	protected Connection conn = null; // ����connection����
	protected String url = ""; // urlָ��Ҫ�������ݿ���jzsoft
	protected String user = ""; // ���ݿ��û���
	protected String pwd = ""; // ���ݿ�����
	protected Statement stmt = null;

	// �����ݿ����
	protected Connection conn1 = null; // ����connection����
	protected String url1 = ""; // urlָ��Ҫ�������ݿ���jzsoft
	protected String user1 = ""; // ���ݿ��û���
	protected String pwd1 = ""; // ���ݿ�����
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
		// ������ѯ�����
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, user, pwd);
			conn1 = (Connection) DriverManager.getConnection(url1, user1, pwd1);
			if (!conn.isClosed()) {
				System.out.println("ԭ���ݿ����ӳɹ���");
			} else {

				System.out.println("ԭ���ݿ�����ʧ�ܣ�");
			}

			if (!conn1.isClosed()) {
				System.out.println("�����ݿ����ӳɹ���");
			} else {

				System.out.println("�����ݿ�����ʧ�ܣ�");
			}
			stmt = conn.createStatement();
			stmt1 = conn1.createStatement();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣�������д�����Ƿ�����");
		}
	}

}
