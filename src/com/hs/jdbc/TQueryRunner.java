package com.hs.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class TQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection con = JdbcUtil.getCon();
		int[] result =  super.batch(con,sql, params);
		JdbcUtil.realseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection con = JdbcUtil.getCon();
		T result =  super.query(con,sql, rsh, params);
		JdbcUtil.realseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtil.getCon();
		T result = super.query(con,sql, rsh);
		JdbcUtil.realseConnection(con);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection con = JdbcUtil.getCon();
		int result = super.update(con,sql);
		JdbcUtil.realseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = JdbcUtil.getCon();
		int result = super.update(con,sql, param);
		JdbcUtil.realseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = JdbcUtil.getCon();
		int result = super.update(con,sql, params);
		JdbcUtil.realseConnection(con);
		return result;
	}

}
