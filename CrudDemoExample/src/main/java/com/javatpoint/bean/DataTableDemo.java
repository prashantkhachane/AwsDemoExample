package com.javatpoint.bean;

import java.util.List;

public class DataTableDemo {
	Long iTotalRecords;

	Long iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<Employee> aaData;

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<Employee> getAaData() {
		return aaData;
	}

	public void setAaData(List<Employee> aaData) {
		this.aaData = aaData;
	}

}
