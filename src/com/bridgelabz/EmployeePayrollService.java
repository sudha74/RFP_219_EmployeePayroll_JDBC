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
        preparedStatement.setDouble(1,salary);
        preparedStatement.setString(2,empName);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("salary updated successfully!");
        }
        return rowsAffected;
    }
}
