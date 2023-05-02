package com.employee.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.employee.entity.PartTimeEmployee;

public class PartTimeDao {

	public List<PartTimeEmployee> getdetails(Connection c) {
		List<PartTimeEmployee> list = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = c.prepareStatement(Constants.GET_PARTTIME_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				PartTimeEmployee pe = new PartTimeEmployee(rs.getInt("id"),rs.getString("periodtime"));
				list.add(pe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public PartTimeEmployee adddata(PartTimeEmployee e,Connection c) throws SQLException{
		PreparedStatement preparedstatement = c.prepareStatement(Constants.ADD_PARTTIME_QUERY);
		preparedstatement.setInt(1, e.getId());
		preparedstatement.setString(2, e.getPeriodtime());
		preparedstatement.execute();
		return e;
	}
}

















































//	
//	public PartTimeEmployee getbyid(int id) {
//		PartTimeEmployee pe = null;
//		try(Connection c = getConnection();
//				PreparedStatement preparedStatement = c.prepareStatement(getbyid);){
//			preparedStatement.setInt(1, id);
//			ResultSet rs = preparedStatement.executeQuery();
//			if(rs.next()) {
//				pe = new PartTimeEmployee(rs.getInt("id"),rs.getString("name"),rs.getInt("salary"));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return pe;
//	}
//	

//	public PartTimeEmployee adddata(PartTimeEmployee e) throws SQLException {
//		EmployeeDao edao = new EmployeeDao();
//		Employee e1 = new Employee(e.getId(),e.getName(),e.getSalary(),"PartTimeEmployee");
//		edao.setEmployee(e1);
//		return e;
//	}
//	public String delete(PartTimeEmployee e) {
//		Employee e1 = new Employee(e.getId(),e.getName(),e.getSalary(),"PartTimeEmployee");
//		EmployeeDao edao=new EmployeeDao();
//		if(edao.delete(e1)) {
//			return "Deleted";
//		}
//		return "NotDeleted";
//		
//	}


