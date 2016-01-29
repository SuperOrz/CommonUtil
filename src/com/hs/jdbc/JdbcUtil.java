package com.hs.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	private static DataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	public static DataSource getDs() {
		return ds;
	}

	public static Connection getCon() throws SQLException {
		Connection con = threadLocal.get();
		if(con != null) return con; //存在事务con，则返回事务con
		return ds.getConnection();//不存在，就从连接池中拿一个返回
	}
	/**
	 * 开启事务
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException {
		Connection con = threadLocal.get();//从threadlocal中获取本线程的con
		if(con != null) throw new SQLException("已经开启事务，无法再次开启");
		con = ds.getConnection();
		con.setAutoCommit(false);//关闭自动提交
		threadLocal.set(con);//把con放入threadlocal中
	}
	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException{
		Connection con = threadLocal.get();//从threadlocal中获取本线程的con
		if(con == null) throw new SQLException("没有事务，无法提交");
		con.commit();
		con.close();
		threadLocal.remove();//从threadlocal中删去con
	}
	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException{
		Connection con = threadLocal.get();//从threadlocal中获取本线程的con
		if(con == null) throw new SQLException("没有事务，无法回滚");
		con.rollback();
		con.close();
		threadLocal.remove();//从threadlocal中删去con
	}
	/**
	 * 关闭con
	 * @param connection
	 * @throws SQLException
	 */
	public static void realseConnection(Connection connection) throws SQLException{
		Connection con = threadLocal.get();
		if(con!=connection){
			if(connection != null && !connection.isClosed()) connection.close();
		}
	}
}
