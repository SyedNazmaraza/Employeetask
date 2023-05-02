package com.employee.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employee.entity.ContractEmployee;


public class ContractDao {

	public List<ContractEmployee> getdetails(Connection c) {
		List<ContractEmployee> list = new ArrayList<>();
		try{
			PreparedStatement preparedStatement = c.prepareStatement(Constants.GET_CONTRACT_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				ContractEmployee ce = new ContractEmployee(rs.getInt("id"),rs.getString("periodtime"));
				list.add(ce);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ContractEmployee adddata(ContractEmployee e,Connection c) throws SQLException{
		PreparedStatement preparedstatement = c.prepareStatement(Constants.ADD_CONTRACT_QUERY);
		preparedstatement.setInt(1, e.getId());
		preparedstatement.setString(2, e.getPeriodtime());
		preparedstatement.execute();
		return e;
	}
}








































//	public ContractEmployee getbyid(int id) {
//		ContractEmployee ce = null;
//		try(Connection c = getConnection();
//				PreparedStatement preparedStatement = c.prepareStatement(getbyid);){
//			preparedStatement.setInt(1, id);
//			ResultSet rs = preparedStatement.executeQuery();
//			if(rs.next()) {
//				ce = new ContractEmployee(rs.getInt("id"),rs.getString("name"),rs.getInt("salary"));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ce;
//	}
	

	
//	public ContractEmployee adddata(ContractEmployee e) throws SQLException {
//		Employee e1 = new Employee(e.getId(),e.getName(),e.getSalary(),"ContractEmployee");
//		EmployeeDao edao=new EmployeeDao();
//		edao.setEmployee(e1);
//		return e;

//	}
	
//	public String delete(ContractEmployee e) {
//		Employee e1 = new Employee(e.getId(),e.getName(),e.getSalary(),"ContractEmployee");
//		EmployeeDao edao=new EmployeeDao();
//		if(edao.delete(e1)) {
//			return "Deleted";
//		}
//		return "NotDeleted";
//		
//	}

	
	


