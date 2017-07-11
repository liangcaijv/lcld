package org.lanqiao.sql.util1;

interface Config {
	public String getDriverName();

	public String getUrl();

	public String getUser();

	public String getPwd();

	public String getName();

	public int getMaxActive();

	public int getMaxIde();

	public long getMaxWait();
}
