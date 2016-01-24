package com.hs.jdbc;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class Dao {
	public void update(String sql,Object...params) throws SQLException {
		QueryRunner qRunner= new TQueryRunner();
		qRunner.update(sql, params);
	}
}	
