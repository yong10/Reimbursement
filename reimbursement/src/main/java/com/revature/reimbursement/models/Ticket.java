package com.revature.reimbursement.models;

import java.time.LocalDateTime;

public class Ticket {
	private int ticketId;
	private TicketType ticketType;
	private double amount;
	private String description;
	private TicketStatus status;
	private LocalDateTime createdAt;
	private int employeeId;
	
	public Ticket(int ticketId, TicketType ticketType, double amount, String description, TicketStatus status,
			LocalDateTime createdAt, int employeeId) {
		super();
		this.ticketId = ticketId;
		this.ticketType = ticketType;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.employeeId = employeeId;
	}
	
	public Ticket() {
		
	}

	public int getTicketId() {
		return ticketId;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
