package com.cjh.eshop.util.excel;

public abstract class DataAdapter {
	
	private static final String DEFAULT_TIME_PATTERN = "yyy-MM-dd";
	
	public abstract String getTitleString(int position);
	public abstract int getColumnCount();
	public abstract int getRowCount();
	public abstract Object getValueAt(int row, int column);
	
	public String getTimePattern() {
		return DEFAULT_TIME_PATTERN;
	}
}
