package com.trackingsystem.model;

public class Employee {

	private int eid;
	private String empno;
	private String empname;
	private String designation;
	private String baselocation;
	private String skillset;
	
	
	public Employee() {
		super();
	}


	public Employee(int eid, String empno, String empname, String designation, String baselocation, String skillset) {
		super();
		this.eid = eid;
		this.empno = empno;
		this.empname = empname;
		this.designation = designation;
		this.baselocation = baselocation;
		this.skillset = skillset;
	}


	
	public Employee(String empno, String empname, String designation, String baselocation, String skillset) {
		super();
		this.empno = empno;
		this.empname = empname;
		this.designation = designation;
		this.baselocation = baselocation;
		this.skillset = skillset;
	}


	public int getEid() {
		return eid;
	}


	public void setEid(int eid) {
		this.eid = eid;
	}


	public String getEmpno() {
		return empno;
	}


	public void setEmpno(String empno) {
		this.empno = empno;
	}


	public String getEmpname() {
		return empname;
	}


	public void setEmpname(String empname) {
		this.empname = empname;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getBaselocation() {
		return baselocation;
	}


	public void setBaselocation(String baselocation) {
		this.baselocation = baselocation;
	}


	public String getSkillset() {
		return skillset;
	}


	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}


	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", empno=" + empno + ", empname=" + empname + ", designation=" + designation
				+ ", baselocation=" + baselocation + ", skillset=" + skillset + "]";
	}
	
	
	
	
	
	
}
