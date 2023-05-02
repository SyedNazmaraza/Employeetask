package com.employee.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.ContractDao;
import com.employee.entity.ContractEmployee;
import com.google.gson.Gson;

import Listeners.Dbclass;

public class ContractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContractDao dao;
	private Gson gson = new Gson();
	
	public void init(ServletConfig config) throws ServletException {
		dao=new ContractDao();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ServletContext cxt = request.getServletContext();
		Dbclass db = (Dbclass) cxt.getAttribute("dbmanager");
		Connection c = db.getConnection();
		try {
			List<ContractEmployee> list = dao.getdetails(c);
			String s = gson.toJson(list);
			PrintWriter out = null;
			try {
				out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(s);
				out.flush();
			}
			finally {
				out.close();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
}
