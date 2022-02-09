package com.revature.reimbursement.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.reimbursement.models.Employee;

public class EmployeeDAO {
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<Employee>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String query = "select * from employee";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				allEmployees.add(
						new Employee(
								rs.getInt("employeeId"),
								rs.getString("employeeName"),
								rs.getString("phoneNum")
								)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allEmployees;
	}
	
	public Employee getOneEmployee(int employeeId) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "select * from employee where employeeId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Employee(
						rs.getInt("employeeId"),
						rs.getString("employeeName"),
						rs.getString("phoneNum")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void addEmployee(Employee newEmployee) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "insert into employee (employeeName, phoneNum) values (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newEmployee.getEmployeeName());
			pstmt.setString(2, newEmployee.getPhoneNum());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateEmployee(int employeeId, Employee updateEm ) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "update employee set phonenum = ? where employeeid = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updateEm.getPhoneNum());
			pstmt.setInt(2, employeeId);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteEmployee(int employeeId) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection())
		{
			String query = "delete from employee where employeeId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, employeeId);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


