package com.employee.entity;

public class PartTimeEmployee {
	private int id;
	private String periodtime;
	
	public PartTimeEmployee() {
		super();
	}
	public PartTimeEmployee(int id, String periodtime) {
		super();
		this.id = id;
		this.periodtime = periodtime;
	}
	@Override
	public String toString() {
		return "PartTimeEmployee [id=" + id + ", periodtime=" + periodtime + "]";
	}
	public String getPeriodtime() {
		return periodtime;
	}
	public void setPeriodtime(String periodtime) {
		this.periodtime = periodtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
