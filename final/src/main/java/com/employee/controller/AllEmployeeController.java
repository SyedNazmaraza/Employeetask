package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.AllEmployeeDao;
import com.employee.entity.AllEmployee;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Listeners.Dbclass;

/**
 * Servlet implementation class GetEmployeeController
 */
public class AllEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  Gson gson = new Gson();
	private  AllEmployeeDao employeedao;
	 
	public void init() {
		employeedao=new AllEmployeeDao();
	}
	
	protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		ServletContext cxt = request.getServletContext();
		Dbclass db = (Dbclass) cxt.getAttribute("dbmanager");
		Connection c = db.getConnection();
		try {
			AllEmployee jsontoObject = gson.fromJson(request.getReader(), AllEmployee.class);
			if(jsontoObject==null){
					List<Object> e = employeedao.getall(c);
					String employeeJsonString = gson.toJson(e);
					PrintWriter out = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					out.print(employeeJsonString);
					out.flush();	
			}
			else if(jsontoObject!=null && jsontoObject.getName()==null && !jsontoObject.getFilter().isEmpty()) {
				List<AllEmployee> l1 = new ArrayList<>();
				l1 = employeedao.getEmployeeinfilterd(jsontoObject.getFilter(),c);
				String employeeJsonString = gson.toJson(l1); 
				PrintWriter out = response.getWriter();
				response.setContentType("appllication/json");
				response.setCharacterEncoding("UTF-8");
				out.print(employeeJsonString);
				out.flush();
			}
			else if(jsontoObject!=null && jsontoObject.getName()!=null ) {
				List<AllEmployee> l1 = new ArrayList<>();
				l1 = employeedao.getEmployeeByName(jsontoObject.getName().toString(),jsontoObject.getFilter(),c);
				String employeeJsonString = gson.toJson(l1); 
				PrintWriter out = response.getWriter();
				response.setContentType("appllication/json");
				response.setCharacterEncoding("UTF-8");
				out.print(employeeJsonString);
				out.flush();
			}
//			else if(jsontoObject!=null && jsontoObject!=null && !jsontoObject.getFilter().isEmpty()) {
//				List<AllEmployee> l1 = new ArrayList<>();
//				l1 = employeedao.
//				
//			}
			
			
		} catch (JsonSyntaxException | JsonIOException | IOException e1) {
			e1.printStackTrace();
		}
		finally {
			db.closeConnection();
		}
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		ServletContext cxt = request.getServletContext();
		Dbclass db = (Dbclass) cxt.getAttribute("dbmanager");
		Connection c = db.getConnection();
		try {
			AllEmployee jsontoobject = gson.fromJson(request.getReader(), AllEmployee.class);
			AllEmployeeDao edao= new AllEmployeeDao();
			boolean e = edao.setEmployee(jsontoobject,c);
			if(e==true) {
				String employeeJsonString = gson.toJson(jsontoobject);
				String action = request.getServletPath();
				System.out.println(action);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8") ;
				out.print(employeeJsonString);
				out.flush();
			}
			else {
				PrintWriter out = response.getWriter();
				out.println("NotAdded");	
			}
		} 
		catch (JsonSyntaxException | JsonIOException | IOException e1) {
			e1.printStackTrace();
		}
		finally {
			db.closeConnection();
		}

	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		ServletContext cxt = request.getServletContext();
		Dbclass db = (Dbclass) cxt.getAttribute("dbmanager");
		Connection c = db.getConnection();
		try {
			AllEmployee jsontoobject = gson.fromJson(request.getReader(), AllEmployee.class);
			AllEmployeeDao edao= new AllEmployeeDao();
			boolean b = edao.update(jsontoobject,c);
			if(b==true) {
				String employeetojson = gson.toJson(jsontoobject);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(employeetojson);
				out.flush();
			}
			else {
				PrintWriter out = response.getWriter();
				out.print("NotAdded");
			}
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		ServletContext cxt = request.getServletContext();
		Dbclass db = (Dbclass) cxt.getAttribute("dbmanager");
		Connection c = db.getConnection();
		try {
			AllEmployee jsontoobject = gson.fromJson(request.getReader(), AllEmployee.class);
			boolean e = employeedao.delete(jsontoobject,c);
			System.out.println(e);
			if(e) {
				PrintWriter out = response.getWriter();
				out.println("deleted");
			}
			else {
				PrintWriter out = response.getWriter();
				out.println("NotDeleted");
			}
		} 
		catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}
	}
	
	protected void doLock(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PrintWriter out = response.getWriter();
			out.println("ASD");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
