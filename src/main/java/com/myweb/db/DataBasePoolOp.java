package com.myweb.db;

import java.sql.Connection;

/***
 * 数据库连接池 相关操作
 * @author e7691
 *
 */
public interface DataBasePoolOp {
    Connection getConnection() ;
}
