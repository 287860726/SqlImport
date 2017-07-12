/**
 * @ahthor zhaocm
 * @date 2017��7��12��
 * @time ����9:00:50
 */
package com.zhao.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhaocm
 * @data 2017��7��12��
 * @time ����9:00:50
 */
public class huoqubiaoxinxi extends Base {
	/**
	 * ��ȡ���ݿ��б����Ϣ
	 * 
	 * @user zhaocm
	 * @date 2017��7��10��
	 * @param conn
	 */
	public void huoqushujukubiao(String dataurl, String user, String pwd) {
		this.url = dataurl;
		this.user = user;
		this.pwd = pwd;
		// ������ѯ�����
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, user, pwd);
			if (!conn.isClosed()) {
				System.out.println("���ݿ����ӳɹ���");
			} else {

				System.out.println("���ݿ�����ʧ�ܣ�");
			}
			stmt = conn.createStatement();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣�������д�����Ƿ�����");
		}
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
}
