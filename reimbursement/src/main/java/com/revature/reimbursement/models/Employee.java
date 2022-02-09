package com.revature.reimbursement.models;


public class Employee {
	private int employeeId;
	private String employeeName;
	private String phoneNum;

	
	public Employee(int employeeId, String employeeName, String phoneNum) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.phoneNum = phoneNum;
	}
	
	public Employee() {
		
	}

	public int getEmployeeId() {
		return employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	
}
