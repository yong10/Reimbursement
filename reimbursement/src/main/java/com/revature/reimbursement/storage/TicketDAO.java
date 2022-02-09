package com.revature.reimbursement.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.reimbursement.models.Ticket;
import com.revature.reimbursement.models.TicketStatus;
import com.revature.reimbursement.models.TicketType;

public class TicketDAO {
	//get all tickets
	public List<Ticket> getAllTickets() {
		List<Ticket> allTickets = new ArrayList<Ticket>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String query = "select * from ticket order by createdAt desc";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				allTickets.add(
						new Ticket(
								rs.getInt("ticketId"),
								TicketType.valueOf(rs.getString("ticketType")),
								rs.getDouble("amount"),
								rs.getString("description"),
								TicketStatus.valueOf(rs.getString("status")),
								rs.getTimestamp("createdAt").toLocalDateTime(),
								rs.getInt("employeeId")
								)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allTickets;
	}

	
	//get all tickets related to one employee
	public List<Ticket> getTickestsByEmId(int employeeId) {
		List<Ticket> allTickets = new ArrayList<Ticket>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from ticket where employeeId = ? order by createdAt desc";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				allTickets.add(
						new Ticket(
								rs.getInt("ticketId"),
								TicketType.valueOf(rs.getString("ticketType")),
								rs.getDouble("amount"),
								rs.getString("description"),
								TicketStatus.valueOf(rs.getString("status")),
								rs.getTimestamp("createdAt").toLocalDateTime(),
								rs.getInt("employeeId")
								)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTickets;
	}
	
	//get all tickets by status(pending, approved, rejected)
	public List<Ticket> getTicketsByStatus(String status) {
		List<Ticket> allTickets = new ArrayList<Ticket>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from ticket where status = CAST(? as TicketStatus) order by createdAt desc";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				allTickets.add(
						new Ticket(
								rs.getInt("ticketId"),
								TicketType.valueOf(rs.getString("ticketType")),
								rs.getDouble("amount"),
								rs.getString("description"),
								TicketStatus.valueOf(rs.getString("status")),
								rs.getTimestamp("createdAt").toLocalDateTime(),
								rs.getInt("employeeId")
								)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allTickets;
	}
	
	//get one Ticket
	public Ticket getOneTicket(int ticketId) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "select * from ticket where ticketId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ticketId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Ticket(
						rs.getInt("ticketId"),
						TicketType.valueOf(rs.getString("ticketType")),
						rs.getDouble("amount"),
						rs.getString("description"),
						TicketStatus.valueOf(rs.getString("status")),
						rs.getTimestamp("createdAt").toLocalDateTime(),
						rs.getInt("employeeId")
						);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void addTicket(Ticket newTicket) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "insert into ticket (ticketType, amount, description, status, employeeId) values (CAST(? as TicketType),?,?,CAST(? as TicketStatus),?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newTicket.getTicketType().toString());
			pstmt.setDouble(2, newTicket.getAmount());
			pstmt.setString(3, newTicket.getDescription());
			pstmt.setString(4, newTicket.getStatus().toString());
			pstmt.setInt(5, newTicket.getEmployeeId());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateTicket(int ticketId, Ticket updateTi) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "update ticket set status = CAST(? as TicketStatus) where ticketId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updateTi.getStatus().toString());
			pstmt.setInt(2, ticketId);
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteTicket(int ticketId) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "delete from ticket where ticketId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ticketId);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


