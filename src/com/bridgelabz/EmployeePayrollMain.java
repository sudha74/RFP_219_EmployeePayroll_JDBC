package com.bridgelabz;

import java.sql.SQLException;

public class EmployeePayrollMain {
    public static void main(String[] args) throws EmployeePayrollException, SQLException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();

        employeePayrollService.retrieveData().forEach((employeePayrollData -> System.out.println(employeePayrollData)));

        employeePayrollService.updateSalary("Mark", 300000.00);

    }
}