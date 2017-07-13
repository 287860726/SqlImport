/**
 * @ahthor zhaocm
 * @date 2017年7月13日
 * @time 下午8:05:01
 */
package com.zhao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhaocm
 * @data 2017年7月13日
 * @time 下午8:05:01 针对表结构不同，复制表数据
 */
public class ImportData extends Base {

	public void importdate(String ydataurl, String ydataname, String yuser, String ypwd, String newdataurl,
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
				String column = "";
				sql1 = "insert into " + newdataname + "." + tableRet.getString("TABLE_NAME") + "(";
				// 复制表数据
				ResultSet colRet = dbmata.getColumns(null, "%", tableRet.getString("TABLE_NAME"), "%");
				while (colRet.next()) {
					column = column + colRet.getString("COLUMN_NAME") + ",";
				}
				int b = column.lastIndexOf(",");
				column = column.substring(0, b);
//				System.out.println(column);
				sql1 = sql1 + column + ")" + " select " + column + " from " + ydataname + "." + tableRet.getString("TABLE_NAME");
				System.out.println(sql1);
				stmt1.executeUpdate(sql1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
