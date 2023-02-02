import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Employee employee = new Employee();
        employee.setName(name);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setCountry(country);

        int status = EmployeeDAO.save(employee);
        if (status > 0) {
            out.print("<p>Employee saved successfully!</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        } else {
            out.println("Unable to save employee");
        }

        out.close();
    }
}