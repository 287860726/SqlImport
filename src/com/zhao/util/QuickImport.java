/**
 * @ahthor zhaocm
 * @date 2017年7月12日
 * @time 下午10:21:42
 */
package com.zhao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhaocm
 * @data 2017年7月12日
 * @time 下午10:21:42
 * 表结构完全相同
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
				// 复制表结构
				sql = "create table if not exists " + newdataname + "." + tableRet.getString("TABLE_NAME") + " like "
						+ ydataname + "." + tableRet.getString("TABLE_NAME");
				// 复制表数据
				sql1 = "insert into " + newdataname + "." + tableRet.getString("TABLE_NAME") + " select * from "
						+ ydataname + "." + tableRet.getString("TABLE_NAME");
				// System.out.println(sql);
				stmt1.executeUpdate(sql);
				System.out.println(sql1);
				try {
//					stmt1.executeUpdate(sql1);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("数据重复！");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
