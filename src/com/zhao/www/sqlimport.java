package com.zhao.www;

import com.zhao.util.ImportData;
import com.zhao.util.QuickImport;

public class sqlimport {

	public static void main(String[] args) {

		//��ṹ��ͬ������ԭ����û�е��ֶα�����Ĭ��ֵ
		ImportData id = new ImportData();
		id.importdate("jdbc:mysql://localhost:3306/cesh", "cesh", "root", "123456", "jdbc:mysql://localhost:3306/test1",
				"test1", "root", "123456");

		// ���ٵ����ṹ�Լ�������
//		long a = System.currentTimeMillis();
//		QuickImport qi = new QuickImport();
//		qi.qimport("jdbc:mysql://localhost:3306/jzsoft", "jzsoft", "root", "123456",
//				"jdbc:mysql://localhost:3306/test1", "test1", "root", "123456");
//		long b = System.currentTimeMillis();
//		System.out.println("���ݿ⵼�����,��ʱ��" + (b - a) + "���롣");

		// ���Ʊ�ṹ
		// fuzhibiaojiegou fz = new fuzhibiaojiegou();
		// fz.fuzhubiao("jdbc:mysql://localhost:3306/jzsoft", "root", "123456",
		// "jdbc:mysql://localhost:3306/test1", "root", "123456");

		// ��ȡ����Ϣ
		// huoqubiaoxinxi xx = new huoqubiaoxinxi();
		// xx.huoqushujukubiao("jdbc:mysql://localhost:3306/jzsoft", "root",
		// "123456");
	}
}
