package com.employee.entity;

import java.util.List;

public class AllEmployee {
	private int id;
	private String name;
	private int salary;
	private String type;
	private List<String> filter;
	private String createtime;
	private String updatetime;
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public List<String> getFilter() {
		return filter;
	}
	public void setFilter(List<String> filter) {
		this.filter = filter;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getSalary() {
		return salary;
	}
	public String getType() {
		return type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AllEmployee [id=" + id + ", name=" + name + ", salary=" + salary + ", type=" + type + "]";
	}
	public AllEmployee(int id, String name, int salary, String type) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.type = type;
	}
	public AllEmployee() {
		super();
	}

	
	

}
