package com.zhao.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zhaocm
 * @data 2017��7��12��
 * @time ����9:02:32
 */
public class fuzhibiaojiegou extends Base {

	public void fuzhubiao(String ydataurl, String yuser, String ypwd, String newdataurl, String newuser,
			String newpwd) {
		this.url = ydataurl;
		this.user = yuser;
		this.pwd = ypwd;
		this.url1 = newdataurl;
		this.user1 = newuser;
		this.pwd1 = newpwd;

		getconnection();
		
		String sql = "";
		try {
			//
			// ����"%"���Ǳ�ʾ*����˼��Ҳ�����������е���˼������m_TableName����Ҫ��ȡ�����ݱ�����֣�������ȡ���еı�����֣��Ϳ���ʹ��"%"����Ϊ�����ˡ�
			// ResultSet tableRet = m_DBMetaData.getTables(null, "%",
			// m_TableName, new String[] { "TABLE" });
			ResultSet tableRet = dbmata.getTables(null, "%", "%", new String[] { "TABLE" });

			// ��ȡ�������
			while (tableRet.next()) {
				sql = "create table if not exists " + tableRet.getString("TABLE_NAME") + "(";
				// ��ȡ���ڵ��ֶε����ֺ�����
				//
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
					// if
					// (colRet.getString("TYPE_NAME").equalsIgnoreCase("tinyint"))
					// {
					// type_name = "int";
					// System.out.println(tableRet.getString("TABLE_NAME") +
					// "-------" + colRet.getString("COLUMN_NAME"));
					// }
					if (colRet.getString("TYPE_NAME").equalsIgnoreCase("int")) {
						column_size = colRet.getInt("COLUMN_SIZE") + 1;
					} else if (colRet.getString("TYPE_NAME").equalsIgnoreCase("longtext")) {
						column_size = 0;
					} else if (colRet.getString("TYPE_NAME").equalsIgnoreCase("mediumtext")) {
						column_size = 0;
					} else if (colRet.getString("TYPE_NAME").equalsIgnoreCase("enum")) {
						column_size = 10000;
					}

					if (colRet.getInt("NULLABLE") == 0) {
						nullable = "not null";
					}
					sql = sql + colRet.getString("COLUMN_NAME") + " " + type_name;
					if (column_size == 10000) {
						sql = sql + "('Y','N') " + nullable + ",";
					} else if (column_size == 0) {
						sql = sql + " " + nullable + ",";
					} else {
						sql = sql + "(" + column_size + ") " + nullable + ",";
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

	}
}
