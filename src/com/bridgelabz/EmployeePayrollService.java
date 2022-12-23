package com.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {
    public List<EmployeePayrollData> retrieveData() throws EmployeePayrollException {
        try {
            List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
            Connection connection = JDBCConnection.connectToDatabase();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee_payroll");
            while (resultSet.next()) {
                employeePayrollDataList.add(new EmployeePayrollData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDate(5)));
            }
            connection.close();
            return employeePayrollDataList;
        } catch (Exception e) {
            throw new EmployeePayrollException();
        }
    }

    public int updateSalary(String empName, double salary) throws SQLException {
        Connection connection = JDBCConnection.connectToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("update employee_payroll set salary = ? where name = ?");
        preparedStatement.setDouble(1, salary);
        preparedStatement.setString(2, empName);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("salary updated successfully!");
        }
        return rowsAffected;
    }

    public void getEmployeeBetweenSalaryRange(double minSalary, double maxSalary) throws SQLException {
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        Connection connection = JDBCConnection.connectToDatabase();

        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(("select * from employee_payroll where salary between ? and ?"));
        preparedStatement.setDouble(1, minSalary);
        preparedStatement.setDouble(2, maxSalary);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            employeePayrollDataList.add(new EmployeePayrollData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDate(5)));
        }
        employeePayrollDataList.forEach(data -> System.out.println(data));
        connection.close();
    }

    public void getSumOfSalaryByMaleAndFemale() throws SQLException {
        Connection connection = JDBCConnection.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT gender,count(*),SUM(salary) FROM employee_payroll GROUP BY gender;");
        System.out.println("gender count SUM(salary)");
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString(1) + "\t"
                            + resultSet.getInt(2) + "\t"
                            + resultSet.getDouble(3)
            );
        }
    }
}