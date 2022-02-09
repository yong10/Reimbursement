
package com.revature.reimbursement;

import com.revature.reimbursement.models.Employee;
import com.revature.reimbursement.models.Ticket;
import com.revature.reimbursement.storage.EmployeeDAO;
import com.revature.reimbursement.storage.TicketDAO;


import io.javalin.Javalin;

public class HelloWorld {
  public static void main(String[] args) {
	EmployeeDAO employeeDAO = new EmployeeDAO();
	TicketDAO ticketDAO = new TicketDAO();
    Javalin app = Javalin.create().start(7000);
    app.get("/", ctx -> ctx.result("Hello World"));
    //get all Employees
    app.get("/employees", ctx -> ctx.jsonStream(employeeDAO.getAllEmployees()));
    
    //get one employee by Employee Id
    app.get("/employees/{employeeId}", ctx -> {
    	ctx.jsonStream(employeeDAO.getOneEmployee(Integer.parseInt(ctx.pathParam("employeeId"))));
    });
    
    //add new Employee
    app.post("/employees", ctx -> employeeDAO.addEmployee(ctx.bodyStreamAsClass(Employee.class)));
    
    //update Employee info by Employee Id
    app.put("/employees/{employeeId}", ctx -> {
    	employeeDAO.updateEmployee(Integer.parseInt(ctx.pathParam("employeeId")),
    			ctx.bodyStreamAsClass(Employee.class)
    			);
    });
    
    //delete one employee by Employee Id
    app.delete("/employees/{employeeId}", ctx -> 
		employeeDAO.deleteEmployee(Integer.parseInt(ctx.pathParam("employeeId")))
    );
    
    //get all Tickets
    app.get("/tickets", ctx -> ctx.jsonStream(ticketDAO.getAllTickets()));
    
    
    //get all Tickets of one Employee by Employee Id
    app.get("/employees/{employeeId}/tickets", ctx -> {
    	ctx.jsonStream(ticketDAO.getTickestsByEmId(Integer.parseInt(ctx.pathParam("employeeId"))));
    });
    
    
    //get all tickets by Ticket status
    app.get("/tickets/status/{status}", ctx -> {
    	ctx.jsonStream(ticketDAO.getTicketsByStatus(ctx.pathParam("status")));
    });
    
    //get on ticket by Ticket Id
    app.get("/tickets/{ticketId}", ctx -> {
    	ctx.jsonStream(ticketDAO.getOneTicket(Integer.parseInt(ctx.pathParam("ticketId"))));
    });
    
    //add new Ticket
    app.post("/tickets", ctx -> ticketDAO.addTicket(ctx.bodyStreamAsClass(Ticket.class)));
    
    //update Ticket status by Ticket Id
    app.put("/tickets/{ticketId}", ctx -> {
    	ticketDAO.updateTicket(Integer.parseInt(ctx.pathParam("ticketId")),
    			ctx.bodyStreamAsClass(Ticket.class)
    			);
    });
    
    //delete one ticket by Ticket Id
    app.delete("/tickets/{ticketId}", ctx -> 
    	ticketDAO.deleteTicket(Integer.parseInt(ctx.pathParam("ticketId")))
    );
    
  
    //    employeeDAO.addEmployee(new Employee(0,"Peter Parker", "123-456-7891"));
   
    
//    TicketDAO ticketDAO = new TicketDAO();
//    for(Ticket ticket:ticketDAO.getAllTickets())
//    {
//    	System.out.println(ticket);
//    }
//    
//    EmployeeDAO employeeDAO = new EmployeeDAO();
//    for(Employee employee:employeeDAO.getAllEmployees())
//    {
//    	System.out.println(employee);
//    }
  }
}
