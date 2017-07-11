package org.lanqiao.sql.util1;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public interface DbPool {
	public Connection createConnection() throws SQLException;
	public DataSource getDataSource();
}
