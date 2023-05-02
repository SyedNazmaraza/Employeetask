package com.employee.entity;


public class ContractEmployee {
	private int id;
	private String periodtime;
	
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
	@Override
	public String toString() {
		return "ContractEmployee [id=" + id + ", periodtime=" + periodtime + "]";
	}
	public ContractEmployee(int id, String periodtime) {
		super();
		this.id = id;
		this.periodtime = periodtime;
	}
	public ContractEmployee() {
		super();
	}
	
	

}