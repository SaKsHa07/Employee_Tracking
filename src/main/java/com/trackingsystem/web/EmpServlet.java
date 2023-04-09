package com.trackingsystem.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.trackingsystem.dao.EmpDao;
import com.trackingsystem.model.Employee;


@WebServlet("/")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EmpDao empdao;
	
  
    public EmpServlet() {
       
       this.empdao = new EmpDao();
    }
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
		
		case "/new": 
			showNewForm(request,response);
			break;
		
		case "/insert": 
		{
			try {
				insertEmp(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		case "/delete": 
		{
			try {
				deleteEmp(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		case "/edit": 
		{
			try {
				showEditForm(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		case "/update": 
		{
			try {
				updateEmp(request,response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	
		
		default:
			//handle list
			try {
				listEmp(request,response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("emp-form.jsp");
		dispatcher.forward(request, response);
				
	}
	
	private void insertEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		
		 String empno = request.getParameter("empno");
		 String empname = request.getParameter("empname");
		 String designation = request.getParameter("designation");
		 String baselocation = request.getParameter("baselocation");
		 String skillset = request.getParameter("skillset");
		 
		 Employee e = new Employee(empno,empname,designation,baselocation,skillset);
		 
		 empdao.insertEmp(e);
		 response.sendRedirect("list");
	}
	
	private void deleteEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		empdao.deleteEmp(id);
		response.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Employee existingEmp = empdao.selectEmp(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("emp-form.jsp");
		request.setAttribute("emp", existingEmp);
		dispatcher.forward(request, response);
	}
	
	private void updateEmp(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		int eid = Integer.parseInt(request.getParameter("eid"));
		 String empno = request.getParameter("empno");
		 String empname = request.getParameter("empname");
		 String designation = request.getParameter("designation");
		 String baselocation = request.getParameter("baselocation");
		 String skillset = request.getParameter("skillset");
		 
		 Employee e = new Employee(empno,empname,designation,baselocation,skillset);
		 
		 empdao.updateEmp(e);
		 response.sendRedirect("list");
		 
	}
	
	private void listEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		List<Employee> listEmp = empdao.selectAllEmp();
		request.setAttribute("listEmp", listEmp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("emp-list.jsp");
		dispatcher.forward(request, response);
	}
	
	/*
	private void searchEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String baselocation = request.getParameter("baselocation");
		Employee getEmp = empdao.searchEmp(baselocation);
		RequestDispatcher dispatcher = request.getRequestDispatcher("emp-search.jsp");
		request.setAttribute("emp", getEmp);
		dispatcher.forward(request, response);
	}
	*/
}
