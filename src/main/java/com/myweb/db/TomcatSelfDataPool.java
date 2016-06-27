package com.myweb.db;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.db.impl.DataBasePoolOp;
/***
 * tomcat 自带 数据库连接池操作
 * @author e7691
 *
 */
public class TomcatSelfDataPool implements DataBasePoolOp{
    private String poolPath="java:/comp/env/jdbc/MySQLDS";
	private static TomcatSelfDataPool tomcatPool=new TomcatSelfDataPool();
	private TomcatSelfDataPool(){}
	public static TomcatSelfDataPool getInstance(){
		return tomcatPool;
	}
	/***
	 * 从tomcat 数据库连接池中获取 链接实例
	 */
	@Override
	public  Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup(poolPath);
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
