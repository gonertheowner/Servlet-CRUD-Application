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
        PrintWriter out=response.getWriter();
        out.println("<a href='index.html'>Add New Employee</a>");
        out.println("<h1>Employees List</h1>");

        List<Employee> list = EmployeeDAO.getAllEmployees();

        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");

        for (Employee employee : list){
            out.print("<tr><td>" + employee.getId() + "</td><td>" + employee.getName() + "</td><td>" + employee.getPassword() + "</td><td>"
                    + employee.getEmail() + "</td><td>" + employee.getCountry() + "</td><td><a href='edit?id="
                    + employee.getId() + "'>edit</a></td><td><a href='delete?id=" + employee.getId() + "'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}
