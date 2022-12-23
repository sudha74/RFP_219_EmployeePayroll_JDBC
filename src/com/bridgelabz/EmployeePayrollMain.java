package com.bridgelabz;

import java.sql.SQLException;

public class EmployeePayrollMain {
    public static void main(String[] args) throws EmployeePayrollException, SQLException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();

        System.out.println("\nretrieved data: ");
        employeePayrollService.retrieveData().forEach((employeePayrollData -> System.out.println(employeePayrollData)));

        System.out.println("\nupdate salary: ");
        employeePayrollService.updateSalary("Mark", 300000.00);

        System.out.println("\nsalary between range: ");
        employeePayrollService.getEmployeeBetweenSalaryRange(200000.00, 400000.00);
    }
}