package com.trackingsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trackingsystem.model.Employee;

//this dao class provides CRUD Database operations for the table employee in the database
public class EmpDao {

	private String jdbcUrl = "jdbc:mysql://localhost:3306/tracksystem";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Sam@123";
																			
	
	private static final String Insert_Emp = "INSERT INTO employee" + "(empno,empname,designation,baselocation,skillset) VALUES " + " (?, ?, ?, ?, ?);";
	private static final String Select_Emp_ById = "select eid,empno,empname,designation,baselocation,skillset from employee where eid = ?";
	private static final String Select_All_Emp = "select * from employee";
	private static final String Delete_Emp = "delete from employee where eid = ?;";
	private static final String Update_Emp = "update employee set empno=?,empname=?,designation=?,baselocation=?,skillset=? where eid = ?;";

	private static final String Search_Emp_ByBaseLocstion = "select distinct baselocation from employee";
	
	
	//create connection here
	protected Connection getConnection() {
		
		Connection con = null;
	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return con;
	}
	
	
	
	//insert employee
	public void insertEmp(Employee Emp) throws SQLException {
		
		try {
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(Insert_Emp);
			//ps.setInt(1, Emp.getEid());
			ps.setString(1, Emp.getEmpno());
			ps.setString(2, Emp.getEmpname());
			ps.setString(3, Emp.getDesignation());
			ps.setString(4, Emp.getBaselocation());
			ps.setString(5, Emp.getSkillset());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//select employee by id
	public Employee selectEmp(int eid) throws SQLException {
		
		Employee emp = null;
		
		//step 1: Establishing Connection
		try( 
			Connection con = getConnection();
			//step 2: Create Statement using connection object
			PreparedStatement ps = con.prepareStatement(Select_Emp_ById);)
		{
			ps.setInt(1, eid);
			System.out.println(ps);
			//step 3: Execute the query or update query
			ResultSet rs = ps.executeQuery();
			
			//step 4: Process the resultset object
			while(rs.next()) {
				String empno = rs.getString("empno");
				String empname = rs.getString("empname");
				String designation = rs.getString("designation");
				String baselocation = rs.getString("baselocation");
				String skillset = rs.getString("skillset");
				
				 emp = new Employee(eid,empno,empname,designation,baselocation,skillset);
			}
			
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return emp;
		}
		
	
		
	
	//select employee
	public List<Employee> selectAllEmp() throws SQLException {
		
		List<Employee> emp = new ArrayList();
		
		//step 1: Establishing Connection
		try( 
			Connection con = getConnection();
			//step 2: Create Statement using connection object
			PreparedStatement ps = con.prepareStatement(Select_All_Emp);)
		{
			System.out.println(ps);
			
			//step 3: Execute the query or update query
			ResultSet rs = ps.executeQuery();
			
			//step 4: Process the resultset object
			while(rs.next()) {
				int eid = rs.getInt("eid");
				String empno = rs.getString("empno");
				String empname = rs.getString("empname");
				String designation = rs.getString("designation");
				String baselocation = rs.getString("baselocation");
				String skillset = rs.getString("skillset");
				
				 emp.add( new Employee(eid,empno,empname,designation,baselocation,skillset));
			}
			
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return emp;
		}
	
	
	
	//delete employee
	public boolean deleteEmp(int id) throws SQLException {
		boolean rowdeleted;
		try( 
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(Delete_Emp);)
		{
			ps.setInt(1, id);
			rowdeleted = ps.executeUpdate() > 0;
		}
		return rowdeleted;
		
	}
	
	
	
	
	//update employee
	public boolean updateEmp(Employee Emp) throws SQLException {
		
		boolean rowUpdated;
		
		try( 
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(Update_Emp);)
		{
			
			ps.setString(1, Emp.getEmpno());
			ps.setString(2, Emp.getEmpname());
			ps.setString(3, Emp.getDesignation());
			ps.setString(4, Emp.getBaselocation());
			ps.setString(5, Emp.getSkillset());
			ps.setInt(6, Emp.getEid());
		
			rowUpdated = ps.executeUpdate() > 0;
			
		} 
		
		return rowUpdated;
	}
	
/*	
	//Search employee by city
	public Employee searchEmp(String baselocation) {
		
	Employee emp = null;
		
		//step 1: Establishing Connection
		try( 
			Connection con = getConnection();
			//step 2: Create Statement using connection object
			PreparedStatement ps = con.prepareStatement(Search_Emp_ByBaseLocstion);)
		{
			ps.setString(1, baselocation);
			System.out.println(ps);
			//step 3: Execute the query or update query
			ResultSet rs = ps.executeQuery();
			
			//step 4: Process the resultset object
			
			while(rs.next()) {
				int eid = rs.getInt("eid");
				String empno = rs.getString("empno");
				String empname = rs.getString("empname");
				String designation = rs.getString("designation");
				String skillset = rs.getString("skillset");
				
				 emp = new Employee(eid,empno,empname,designation,baselocation,skillset);
			}
			
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return emp;
		
	}
*/	
}
