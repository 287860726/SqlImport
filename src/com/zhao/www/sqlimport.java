package com.zhao.www;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlimport {

	/**
	 * ��ȡ���ݿ��б����Ϣ
	 * 
	 * @user zhaocm
	 * @date 2017��7��10��
	 * @param conn
	 */
	public static void huoqushujukubiao(Connection conn) {
		// ��ȡ����Ϣ
		DatabaseMetaData dbmata;
		try {
			dbmata = conn.getMetaData();
			// ����"%"���Ǳ�ʾ*����˼��Ҳ�����������е���˼������m_TableName����Ҫ��ȡ�����ݱ�����֣�������ȡ���еı�����֣��Ϳ���ʹ��"%"����Ϊ�����ˡ�
			// ResultSet tableRet = m_DBMetaData.getTables(null, "%",
			// m_TableName, new String[] { "TABLE" });
			ResultSet tableRet = dbmata.getTables(null, "%", "%", new String[] { "TABLE" });

			// ��ȡ�������
			while (tableRet.next()) {
				System.out.println("���ݿ�����----" + tableRet.getString("TABLE_NAME"));
				System.out.println("�ֶ�����" + "\t" + "�ֶ�����" + "\t" + "�ֶδ�С" + "\t" + "ʮ����" + "\t" + "�Ƿ��Ϊ��");

				// ��ȡ���ڵ��ֶε����ֺ�����
				// JDBC����ͨ��getColumns�Ľӿڣ�ʵ�ֶ��ֶεĲ�ѯ����getTablesһ����"%"��ʾ��������ģ��ֶΣ�����m_TableName�������ݱ�����֡�
				// getColumns�ķ���Ҳ�ǽ����е��ֶηŵ�һ�����Ƶ��ڴ��еı���COLUMN_NAME�����ֶε����֣�TYPE_NAME�����������ͣ�
				// ����"int","int
				// unsigned"�ȵȣ�COLUMN_SIZE���������������ֶεĳ��ȣ����綨���int(8)���ֶΣ����ؾ���8��
				// ���NULLABLE������1�ͱ�ʾ������Null,��0�ͱ�ʾNot Null��
				String columnName;
				String columnType;
				ResultSet colRet = dbmata.getColumns(null, "%", tableRet.getString("TABLE_NAME"), "%");
				while (colRet.next()) {
					columnName = colRet.getString("COLUMN_NAME");
					columnType = colRet.getString("TYPE_NAME");
					int datasize = colRet.getInt("COLUMN_SIZE");
					int digits = colRet.getInt("DECIMAL_DIGITS");
					int nullable = colRet.getInt("NULLABLE");

					System.out.println(
							columnName + "\t" + columnType + "\t" + datasize + "\t" + digits + "\t" + nullable);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver"; // ����������
		// ԭ���ݿ����
		Connection conn = null; // ����connection����
		String url = "jdbc:mysql://localhost:3306/jzsoft"; // urlָ��Ҫ�������ݿ���jzsoft
		String user = "root"; // ���ݿ��û���
		String pwd = "123456"; // ���ݿ�����
		Statement stmt = null;

		// �����ݿ����
		Connection conn1 = null; // ����connection����
		String url1 = "jdbc:mysql://localhost:3306/test1"; // urlָ��Ҫ�������ݿ���jzsoft
		String user1 = "root"; // ���ݿ��û���
		String pwd1 = "123456"; // ���ݿ�����
		Statement stmt1 = null;

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

			// huoqushujukubiao(conn);

			// String sql = "CREATE TABLE REGISTRATION (id INTEGER not NULL, " +
			// " first VARCHAR(255), " +
			// " last VARCHAR(255), " +
			// " age INTEGER, " +
			// " PRIMARY KEY ( id ))";
			//
			// stmt.executeUpdate(sql);

			String sql = "";
			DatabaseMetaData dbmata = null;
			try {
				dbmata = conn.getMetaData();
				// ����"%"���Ǳ�ʾ*����˼��Ҳ�����������е���˼������m_TableName����Ҫ��ȡ�����ݱ�����֣�������ȡ���еı�����֣��Ϳ���ʹ��"%"����Ϊ�����ˡ�
				// ResultSet tableRet = m_DBMetaData.getTables(null, "%",
				// m_TableName, new String[] { "TABLE" });
				ResultSet tableRet = dbmata.getTables(null, "%", "%", new String[] { "TABLE" });

				// ��ȡ�������
				while (tableRet.next()) {
					sql = "create table if not exists " + tableRet.getString("TABLE_NAME") + "(";
					// ��ȡ���ڵ��ֶε����ֺ�����
					// JDBC����ͨ��getColumns�Ľӿڣ�ʵ�ֶ��ֶεĲ�ѯ����getTablesһ����"%"��ʾ��������ģ��ֶΣ�����m_TableName�������ݱ�����֡�
					// getColumns�ķ���Ҳ�ǽ����е��ֶηŵ�һ�����Ƶ��ڴ��еı���COLUMN_NAME�����ֶε����֣�TYPE_NAME�����������ͣ�
					// ����"int","int
					// unsigned"�ȵȣ�COLUMN_SIZE���������������ֶεĳ��ȣ����綨���int(8)���ֶΣ����ؾ���8��
					// ���NULLABLE������1�ͱ�ʾ������Null,��0�ͱ�ʾNot Null��
					String columnName;
					String columnType;
					ResultSet colRet = dbmata.getColumns(null, "%", tableRet.getString("TABLE_NAME"), "%");
					while (colRet.next()) {
						int column_size = colRet.getInt("COLUMN_SIZE");
						String type_name = colRet.getString("TYPE_NAME");
						String nullable = "null";
						// ��tinyintת����int
//						if (colRet.getString("TYPE_NAME").equalsIgnoreCase("tinyint")) {
//							type_name = "int";
//							System.out.println(tableRet.getString("TABLE_NAME") + "-------" + colRet.getString("COLUMN_NAME"));
//						}
						if (colRet.getString("TYPE_NAME").equalsIgnoreCase("int")) {
							column_size = colRet.getInt("COLUMN_SIZE") + 1;
						} else if (colRet.getString("TYPE_NAME").equalsIgnoreCase("longtext")) {
							column_size = 0;
						} else if (colRet.getString("TYPE_NAME").equalsIgnoreCase("mediumtext")) {
							column_size = 0;
						}else if (colRet.getString("TYPE_NAME").equalsIgnoreCase("enum")) {
							column_size = 10000;
						}

						
						
						if (colRet.getInt("NULLABLE") == 0) {
							nullable = "not null";
						}
						sql = sql + colRet.getString("COLUMN_NAME") + " " + type_name;
						if(column_size == 10000){
							sql = sql + "('Y','N') "	+ nullable + ",";
						}else if(column_size == 0){
							sql = sql + " " + nullable + ",";
						}else{
							sql = sql + "(" +  column_size + ") "	+ nullable + ",";
						}
						
						// columnName = colRet.getString("COLUMN_NAME");
						// columnType = colRet.getString("TYPE_NAME");
						// int datasize = colRet.getInt("COLUMN_SIZE");
						// int digits = colRet.getInt("DECIMAL_DIGITS");
						// int nullable = colRet.getInt("NULLABLE");
					}
					int b = sql.lastIndexOf(",");
					sql = sql.substring(0, b);
					sql = sql + ");";
					System.out.println(sql);
					stmt1.executeUpdate(sql);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}
	}
}
