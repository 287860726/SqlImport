package com.zhao.www;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlimport {

	/**
	 * 获取数据库中标的信息
	 * 
	 * @user zhaocm
	 * @date 2017年7月10日
	 * @param conn
	 */
	public static void huoqushujukubiao(Connection conn) {
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

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver"; // 驱动包名称
		// 原数据库相关
		Connection conn = null; // 声明connection对象
		String url = "jdbc:mysql://localhost:3306/jzsoft"; // url指向要访问数据库名jzsoft
		String user = "root"; // 数据库用户名
		String pwd = "123456"; // 数据库密码
		Statement stmt = null;

		// 新数据库相关
		Connection conn1 = null; // 声明connection对象
		String url1 = "jdbc:mysql://localhost:3306/test1"; // url指向要访问数据库名jzsoft
		String user1 = "root"; // 数据库用户名
		String pwd1 = "123456"; // 数据库密码
		Statement stmt1 = null;

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
				// 其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，就可以使用"%"来作为参数了。
				// ResultSet tableRet = m_DBMetaData.getTables(null, "%",
				// m_TableName, new String[] { "TABLE" });
				ResultSet tableRet = dbmata.getTables(null, "%", "%", new String[] { "TABLE" });

				// 提取表的名字
				while (tableRet.next()) {
					sql = "create table if not exists " + tableRet.getString("TABLE_NAME") + "(";
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
						int column_size = colRet.getInt("COLUMN_SIZE");
						String type_name = colRet.getString("TYPE_NAME");
						String nullable = "null";
						// 将tinyint转换成int
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
