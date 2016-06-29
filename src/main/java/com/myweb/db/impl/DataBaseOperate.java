package com.myweb.db.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.bean.AdminBean;
import com.myweb.db.DataBasePoolOp;

/**
 * 数据库操作
 * 
 * @author e7691
 * 
 */
public class DataBaseOperate {
	/***
	 * 单例
	 */
	private static DataBaseOperate baseOperate=new DataBaseOperate();
	private DataBasePoolOp basePoolOp=TomcatSelfDataPool.getInstance();
	private DataBaseOperate(){}
	public static DataBaseOperate getInstance(){
		return baseOperate;
	}
	/***
	 * 执行 select 语句
	 * @param sql sql语句
	 * @return 查找到的数据集
	 */
	public ResultSet select(String sql){
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = basePoolOp.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/***
	 * 
	 * @param sql sql语句
	 * @return true:执行成功;
	 *         false:执行失败，数据库链接异常 或 sql语句异常
	 */
	public Boolean execute(String sql) {
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = basePoolOp.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	
//	// 数据库连接池 管理类
//	// private static DBConnectionManager
//	// dbConnectionManager=DBConnectionManager.getInstance();
//	private static boolean initial = false;
//	private static String configPath = "./source/db/ds_Config.xml";// 默认初始化
//																	// 配置文件路径
//
//	/***
//	 * 
//	 * @param path
//	 *            数据库配置文件路径及文件
//	 */
//	public static void init(String path) {
//		// if(!initial){
//		// File file=new File(".");
//		// String curPath=file.getAbsolutePath();
//		//
//		// System.out.println(curPath);
//		// dbConnectionManager.configFilePath(path);
//		// initial=true;
//		// }
//	}
//
//	/**
//	 * 
//	 * @return 数据库链接实例
//	 * @throws DataBaseOperateException
//	 */
//	public static synchronized Connection getConnection() {
//		Connection conn = null;
//		try {
//			Context initContext = new InitialContext();
//			DataSource ds = (DataSource) initContext
//					.lookup("java:/comp/env/jdbc/MySQLDS");
//			conn = ds.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// if(initial){
//		// return dbConnectionManager.getConnection();
//		// }
//		// if(configPath==null){
//		// throw new
//		// DataBaseOperateException(DataBaseOperateException.UNINITIAL_TO_CONFIGFILE);
//		// }else{
//		// init(configPath);
//		// return dbConnectionManager.getConnection();
//		// }
//		return conn;
//	}
//
//	/**
//	 * 回收 链接 实例
//	 * 
//	 * @param con
//	 *            要回收的实例
//	 */
//	public static synchronized void realse(Connection con) {
//		//
//		// if(initial){
//		// dbConnectionManager.freeConnection(con);
//		// }
//	}
//
//	/***
//	 * 关闭数据库 即释放 所有连接池 链接实例
//	 */
//	public static synchronized void closeDataBase() {
//		// if(initial){
//		// dbConnectionManager.release();
//		// initial=false;
//		// }
//	}
}
