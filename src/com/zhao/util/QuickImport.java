/**
 * @ahthor zhaocm
 * @date 2017��7��12��
 * @time ����10:21:42
 */
package com.zhao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhaocm
 * @data 2017��7��12��
 * @time ����10:21:42
 * ��ṹ��ȫ��ͬ
 */
public class QuickImport extends Base {

	public void qimport(String ydataurl, String ydataname, String yuser, String ypwd, String newdataurl,
			String newdataname, String newuser, String newpwd) {
		this.url = ydataurl;
		this.user = yuser;
		this.pwd = ypwd;
		this.url1 = newdataurl;
		this.user1 = newuser;
		this.pwd1 = newpwd;

		getconnection();
		String sql = "";
		String sql1 = "";
		try {
			ResultSet tableRet = dbmata.getTables(null, "%", "%", new String[] { "TABLE" });

			while (tableRet.next()) {
				// ���Ʊ�ṹ
				sql = "create table if not exists " + newdataname + "." + tableRet.getString("TABLE_NAME") + " like "
						+ ydataname + "." + tableRet.getString("TABLE_NAME");
				// ���Ʊ�����
				sql1 = "insert into " + newdataname + "." + tableRet.getString("TABLE_NAME") + " select * from "
						+ ydataname + "." + tableRet.getString("TABLE_NAME");
				// System.out.println(sql);
				stmt1.executeUpdate(sql);
				System.out.println(sql1);
				try {
//					stmt1.executeUpdate(sql1);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("�����ظ���");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
