package com.employee.dao;

public class Constants {
	
	protected static final String FIND_BY_ID = "select e.id,e.name,e.salary,e.type from employee e where id = ?";
	protected static final String FIND_BY_NAME = "select e.id,e.name,e.salary,e.type from employee e where name = ? ";
	protected static final String FILTERING = "select e.id,e.name,e.salary,e.type from employee e where type=?";
	protected static final String FILTERING_BY_NAME = "select e.id,e.name,e.salary,e.type from employee e where name = ? and type = ? ";
	protected static final String ADD_EMPLOYEE="insert into employee (id,name,salary,type,createtime)  values(?, ?, ?, ?,?)";
	protected static final String DELETE_QUERY ="delete from employee where id = ?";
	protected static final String UPDATE_QUERY="update employee set name= ? ,salary = ?,type = ?, createtime=?, updatetime=? where id = ?;";
	protected static final String GET_ALL = "select e.id,e.name,e.salary,e.type from employee e";
	protected static final String DELETE_CONTRACT = "delete from contractemployee where id = ?";
	protected static final String DELETE_PARTTIME="delete from parttimeemployee where id =?";
	protected static final String DELETE_PERMENENT ="delete from permenentemployee where id =?";
	protected static final String GET_CREATETIME = "select employee.createtime from employee where id = ?";
	
	
	protected static final String GET_CONTRACT_QUERY="select ce.id,ce.periodtime from contractemployee ce"; 
	protected static final  String ADD_CONTRACT_QUERY = "insert into contractemployee values(?,?)";
	
	
	protected static final String GET_PERMENENT_QUERY="select pe.id,pe.periodtime from permenentemployee pe"; 
	protected static final String ADD_PERMENENT_QUERY="insert into permenentemployee values(?,?)";
	
	
	protected static final String GET_PARTTIME_QUERY="select pt.id,pt.periodtime from parttimeemployee pt"; 
	protected static final String ADD_PARTTIME_QUERY="insert into parttimeemployee values(?,?)";
	
	protected static final String EMPLOYEE_ID = "id";
	protected static final String EMPLOYEE_NAME = "name";
	protected static final String EMPLOYEE_SALARY = "salary";
	protected static final String EMPLOYEE_TYPE = "type";
	
	protected static final String CONTRACT_EMPLOYEE = "ContractEmployee";
	protected static final String PART_TIME_EMPLOYEE = "PartTimeEmployee";
	protected static final String PERMENENT_EMPLOYEE = "PermenentEmployee";
	
	
	

}
