package dao;

import beans.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String url = "jdbc:postgresql://localhost:5433/servlet_crud";
    private static final String user = "postgres";
    private static final String password = "t055nv";

    private static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static int save(Employee employee) {
        int status = 0;
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee(employee_name, employee_password, employee_email, employee_country) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getCountry());

            status = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int update(Employee employee) {
        int status = 0;
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET employee_name = ?, employee_password = ?, employee_email = ?, employee_country = ? WHERE id = ?");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getCountry());
            preparedStatement.setInt(5, employee.getId());

            status = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int delete(int id) {
        int status = 0;
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);

            status = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static Employee getEmployeeById(int id) {
        Employee employee = new Employee();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setPassword(resultSet.getString(3));
                employee.setEmail(resultSet.getString(4));
                employee.setCountry(resultSet.getString(5));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<Employee>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setPassword(resultSet.getString(3));
                employee.setEmail(resultSet.getString(4));
                employee.setCountry(resultSet.getString(5));
                employeeList.add(employee);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public static List<Employee> getRecords(int limit, int offset) {
        List<Employee> employeeList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee LIMIT ? OFFSET ?");
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setPassword(resultSet.getString(3));
                employee.setEmail(resultSet.getString(4));
                employee.setCountry(resultSet.getString(5));
                employeeList.add(employee);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }
}
