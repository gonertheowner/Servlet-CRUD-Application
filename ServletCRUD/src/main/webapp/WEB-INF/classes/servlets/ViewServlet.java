package servlets;

import beans.Employee;
import dao.EmployeeDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='index.html'>Add New Employee</a>");
        out.println("<h1>Employees List</h1>");

        int pageId = Integer.parseInt(request.getParameter("page"));
        int limit = 5;
        int offset = limit * (pageId - 1);
        List<Employee> list = EmployeeDAO.getRecords(limit, offset);

        out.print("<h2>Page No: " + request.getParameter("page") + "<h2>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");

        for (Employee employee : list){
            out.print("<tr><td>" + employee.getId() + "</td><td>" + employee.getName() + "</td><td>" + employee.getPassword() + "</td><td>"
                    + employee.getEmail() + "</td><td>" + employee.getCountry() + "</td><td><a href='edit?id="
                    + employee.getId() + "'>edit</a></td><td><a href='delete?id=" + employee.getId() + "'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.print("<a href='view?page=1'>1</a> ");
        out.print("<a href='view?page=2'>2</a> ");
        out.print("<a href='view?page=3'>3</a> ");

        out.close();
    }
}
