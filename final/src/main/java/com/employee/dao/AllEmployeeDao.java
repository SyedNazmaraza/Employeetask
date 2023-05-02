package com.employee.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.employee.entity.AllEmployee;
import com.employee.entity.ContractEmployee;
import com.employee.entity.PartTimeEmployee;
import com.employee.entity.PermenentEmployee;

public class AllEmployeeDao {

	private ContractDao contractdao = new ContractDao();
	private PartTimeDao parttimedao = new PartTimeDao();
	private PermenentDao permenentdao = new PermenentDao();
	private FileHandler fh ;   
	private Logger l = Logger.getLogger("AllEmployeeDao");

	
	public AllEmployee getEmployee(int id,Connection c) {
		AllEmployee e = new AllEmployee();
		try {
			PreparedStatement preparedstatement = c.prepareStatement(Constants.FIND_BY_ID);
			preparedstatement.setInt(1,id);
			try {
				ResultSet rs = preparedstatement.executeQuery();
				try {
					if(rs.next()) {
						e.setId(rs.getInt(Constants.EMPLOYEE_ID));
						e.setName(rs.getString(Constants.EMPLOYEE_NAME));
						e.setSalary(rs.getInt(Constants.EMPLOYEE_SALARY));
						e.setType(rs.getString(Constants.EMPLOYEE_TYPE));
					}
				}
				catch(Exception ccc) {
					ccc.printStackTrace();
				}
				finally {
					rs.close();
				}
			}
			catch(Exception cc) {
				cc.printStackTrace();
			}
			finally {
				preparedstatement.close();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		

		}
		return e;
	}
	public List<AllEmployee> getEmployeeinfilterd(List<String> list,Connection c){
		List<AllEmployee> l1 = new ArrayList<AllEmployee>();
		try {
			for(String i:list) {
				PreparedStatement preparedstatement = c.prepareStatement(Constants.FILTERING);
				preparedstatement.setString(1, i);
				try {
					ResultSet rs = preparedstatement.executeQuery();
					try {
						while(rs.next()) {
							AllEmployee e1 = new AllEmployee(rs.getInt(Constants.EMPLOYEE_ID),rs.getString(Constants.EMPLOYEE_NAME),rs.getInt(Constants.EMPLOYEE_SALARY),rs.getString(Constants.EMPLOYEE_TYPE));
							l1.add(e1);
		
						}
					}
					finally {
						rs.close();
					}
				}
				finally {
					preparedstatement.close();
				}
				
				
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		return l1;
	}
	
	public List<AllEmployee> getEmployeeByName(String name, List<String> list,Connection c) {
		List<AllEmployee> l1 = new ArrayList<AllEmployee>();
		try {
			for(String i:list) {
				PreparedStatement preparedstatement = c.prepareStatement(Constants.FILTERING_BY_NAME);
				preparedstatement.setString(1, name);
				preparedstatement.setString(2, i);
				try {
					ResultSet rs = preparedstatement.executeQuery();
					try {
						while(rs.next()) {
							AllEmployee e1 = new AllEmployee(rs.getInt(Constants.EMPLOYEE_ID),rs.getString(Constants.EMPLOYEE_NAME),rs.getInt(Constants.EMPLOYEE_SALARY),rs.getString(Constants.EMPLOYEE_TYPE));
							l1.add(e1);
						}	
					}
					finally {
						rs.close();
					}
				}
				finally {
					preparedstatement.close();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return l1;
	}
	
	public List<Object> getall(Connection c) throws SecurityException, IOException{
		List<Object> list = new ArrayList<>();
		try {
			PreparedStatement preparedstatement = c.prepareStatement(Constants.GET_ALL);
			try {
				ResultSet rs = preparedstatement.executeQuery();
				try {
					while(rs.next()) {
						AllEmployee e = new AllEmployee(rs.getInt(Constants.EMPLOYEE_ID),rs.getString(Constants.EMPLOYEE_NAME),rs.getInt(Constants.EMPLOYEE_SALARY),rs.getString(Constants.EMPLOYEE_TYPE));
						list.add(e);
					}
					
					fh =  new FileHandler("E:\\filewriter.txt");
					l.addHandler(fh);
					SimpleFormatter formatter = new SimpleFormatter();  
			        fh.setFormatter(formatter);  
			        l.info(rs.toString());
					}
				
				finally {
					rs.close();
				}
			}
			finally {
				preparedstatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
			
		
	}
	
	
	public boolean setEmployee(AllEmployee e,Connection c) {
		boolean b = false;

		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			PreparedStatement preparedstatement = c.prepareStatement(Constants.ADD_EMPLOYEE);
			preparedstatement.setInt(1, e.getId());
			preparedstatement.setString(2,e.getName());
			preparedstatement.setInt(3, e.getSalary());
			preparedstatement.setString(4, e.getType());
			preparedstatement.setString(5, dtf.format(now).toString());
			try {
				String s =preparedstatement.toString();
				
				fh =  new FileHandler("E:\\filewriter.txt");
				l.addHandler(fh);
				SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  
		        l.info(s);  
		
				b = preparedstatement.executeUpdate()>0;
				if(b&&e.getType().equals(Constants.CONTRACT_EMPLOYEE)) {
					ContractEmployee ce = new ContractEmployee(e.getId(),"2023");
					contractdao.adddata(ce, c);
				}
				else if(b&&e.getType().equals(Constants.PART_TIME_EMPLOYEE)) {
					PartTimeEmployee ce = new PartTimeEmployee(e.getId(),"2023");
					parttimedao.adddata(ce, c);
				}
				else if(b&&e.getType().equals(Constants.PERMENENT_EMPLOYEE)) {
					PermenentEmployee ce = new PermenentEmployee(e.getId(),"2023");
					permenentdao.adddata(ce, c);
				}
			}
			finally {
				preparedstatement.close();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	public String getcreatetime(int id,Connection c) {
		String s = null;
		try {
			PreparedStatement preparedstatement = c.prepareStatement(Constants.GET_CREATETIME);
			preparedstatement.setInt(1, id);
			try {
				ResultSet rs = preparedstatement.executeQuery();
				try {
					if(rs.next()) {
						s = rs.getString("createtime");
					}
				}
				finally {
					rs.close();
				}
			}
			finally {
				preparedstatement.close();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public boolean update(AllEmployee e,Connection c) throws SecurityException, IOException {
		boolean b = false;
		AllEmployee emp = getEmployee(e.getId(),c);
		String s1 = getcreatetime(e.getId(),c);
		String s = emp.getType();
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now(); 
			PreparedStatement preparedstatement = c.prepareStatement(Constants.UPDATE_QUERY);
			preparedstatement.setString(1, e.getName());
			preparedstatement.setInt(2, e.getSalary());
			preparedstatement.setString(3, e.getType());
			preparedstatement.setString(4,s1);
			preparedstatement.setString(5, dtf.format(now).toString());
			preparedstatement.setInt(6, e.getId());
			try {
				fh = new FileHandler("E:\\filewriter.txt");
				l.addHandler(fh);
				fh.setFormatter(new SimpleFormatter());
				l.info(preparedstatement.toString());
				
				
				b = preparedstatement.executeUpdate()>0;
				if(s.equals(Constants.CONTRACT_EMPLOYEE)) {
					PreparedStatement prepared = c.prepareStatement(Constants.DELETE_CONTRACT);
					prepared.setInt(1, e.getId());
					prepared.executeUpdate();
				}
				else if(s.equals(Constants.PART_TIME_EMPLOYEE)) {
					PreparedStatement prepared = c.prepareStatement(Constants.DELETE_PARTTIME);
					prepared.setInt(1, e.getId());
					prepared.executeUpdate();
				}
				else if(s.equals(Constants.PERMENENT_EMPLOYEE)) {
					PreparedStatement prepared = c.prepareStatement(Constants.DELETE_PERMENENT);
					prepared.setInt(1, e.getId());
					prepared.executeUpdate();
				}
				updatetype(e,c);
				}
			finally {
				preparedstatement.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return b;
	}
	
	public void updatetype(AllEmployee e,Connection c) throws SQLException {
		String s = e.getType();
		if(s.equals(Constants.CONTRACT_EMPLOYEE)) {
			ContractEmployee ce = new ContractEmployee(e.getId(),"2023");
			contractdao.adddata(ce, c);
		}
		else if(s.equals(Constants.PART_TIME_EMPLOYEE)) {
			PartTimeEmployee ce = new PartTimeEmployee(e.getId(),"2023");
			parttimedao.adddata(ce, c);
		}
		else if(s.equals(Constants.PERMENENT_EMPLOYEE)) {
			PermenentEmployee ce = new PermenentEmployee(e.getId(),"2023");
			permenentdao.adddata(ce, c);
		}
	}
	
	public boolean delete(AllEmployee e,Connection c) {
		boolean a = false;
		try {
			PreparedStatement preparedStatement = c.prepareStatement(Constants.DELETE_QUERY);
			preparedStatement.setInt(1, e.getId());
			try {
				a = preparedStatement.executeUpdate()>0;
				if(a&&e.getType().equals(Constants.CONTRACT_EMPLOYEE)) {
					PreparedStatement p1 = c.prepareStatement(Constants.DELETE_CONTRACT);
					p1.setInt(1, e.getId());
					p1.executeUpdate();
				}
				else if(a&&e.getType().equals(Constants.PART_TIME_EMPLOYEE)) {
					PreparedStatement p1 = c.prepareStatement(Constants.DELETE_PARTTIME);
					p1.setInt(1, e.getId());
					p1.executeUpdate();
				}
				else if(a&&e.getType().equals(Constants.PERMENENT_EMPLOYEE)) {
					PreparedStatement p1 = c.prepareStatement(Constants.DELETE_PERMENENT);
					p1.setInt(1, e.getId());
					p1.executeUpdate();
				}
			}
			finally {
				preparedStatement.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return a;
	}
}
