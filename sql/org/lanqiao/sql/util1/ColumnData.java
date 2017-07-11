package org.lanqiao.sql.util1;

public class ColumnData {
	private String[] columnsName;
	private String[] columnsType;

	public ColumnData(String[] columnsName, String[] columnsType) {
		this.columnsName = columnsName;
		this.columnsType = columnsType;
	}

	public String[] getColumnsName() {
		return this.columnsName;
	}

	public void setColumnsName(String[] columnsName) {
		this.columnsName = columnsName;
	}

	public String[] getColumnsType() {
		return this.columnsType;
	}

	public void setColumnsType(String[] columnsType) {
		this.columnsType = columnsType;
	}
}
