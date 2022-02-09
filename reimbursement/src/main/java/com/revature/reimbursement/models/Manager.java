package com.revature.reimbursement.models;

public class Manager {
	private int managerId;
	private String managerName;
	
	public Manager(int managerId, String managerName) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
	}

	public Manager() {
		
	}

	public int getManagerId() {
		return managerId;
	}


	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	
}
