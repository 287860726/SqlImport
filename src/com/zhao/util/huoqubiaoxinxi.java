/**
 * @ahthor zhaocm
 * @date 2017年7月12日
 * @time 下午9:00:50
 */
package com.zhao.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhaocm
 * @data 2017年7月12日
 * @time 下午9:00:50
 */
public class huoqubiaoxinxi extends Base {
	/**
	 * 获取数据库中标的信息
	 * 
	 * @user zhaocm
	 * @date 2017年7月10日
	 * @param conn
	 */
	public void huoqushujukubiao(String dataurl, String user, String pwd) {
		this.url = dataurl;
		this.user = user;
		this.pwd = pwd;
		// 遍历查询结果集
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, user, pwd);
			if (!conn.isClosed()) {
				System.out.println("数据库连接成功！");
			} else {

				System.out.println("数据库连接失败！");
			}
			stmt = conn.createStatement();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("连接异常，检查填写数据是否有误！");
		}
		// 获取表信息
		DatabaseMetaData dbmata;
		try {
			dbmata = conn.getMetaData();
			// 其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，就可以使用"%"来作为参数了。
			// ResultSet tableRet = m_DBMetaData.getTables(null, "%",
			// m_TableName, new String[] { "TABLE" });
			ResultSet tableRet = dbmata.getTables(null, "%", "%", new String[] { "TABLE" });

			// 提取表的名字
			while (tableRet.next()) {
				System.out.println("数据库名称----" + tableRet.getString("TABLE_NAME"));
				System.out.println("字段名称" + "\t" + "字段类型" + "\t" + "字段大小" + "\t" + "十进制" + "\t" + "是否可为空");

				// 提取表内的字段的名字和类型
				// JDBC里面通过getColumns的接口，实现对字段的查询。跟getTables一样，"%"表示所有任意的（字段），而m_TableName就是数据表的名字。
				// getColumns的返回也是将所有的字段放到一个类似的内存中的表，而COLUMN_NAME就是字段的名字，TYPE_NAME就是数据类型，
				// 比如"int","int
				// unsigned"等等，COLUMN_SIZE返回整数，就是字段的长度，比如定义的int(8)的字段，返回就是8，
				// 最后NULLABLE，返回1就表示可以是Null,而0就表示Not Null。
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
