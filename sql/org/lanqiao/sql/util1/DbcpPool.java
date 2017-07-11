package org.lanqiao.sql.util1;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


enum DbcpPool implements DbPool {
  me;
	private Config config = DbcpConfig.me;
	private static DataSource ds;

	public Connection createConnection() throws SQLException {
		if(ds==null)
			getDataSource();
		return ds.getConnection();
	}

	@Override
	public DataSource getDataSource() {
		if(ds==null){
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(this.config.getDriverName());
			dataSource.setUsername(this.config.getUser());
			dataSource.setPassword(this.config.getPwd());
			dataSource.setUrl(this.config.getUrl());
			dataSource.setMaxIdle(this.config.getMaxIde());
			ds=dataSource;
		}
		return ds;

	}
}
