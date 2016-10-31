package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;
import com.milleniumshopping.app.milleniumshopping.repository.employee.EmployeeRepository;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.EmployeeRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.services.employee.Impl.EmployeeServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Chad on 5/8/2016.
 */
public class EmployeeServiceTest extends AndroidTestCase{

    private static final String TAG_Employee = "Employee Test";

    public void testEmployeeIntentService() throws Exception
    {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(this.getContext());
        EmployeeService employeeService = EmployeeServiceImpl.getInstance();

        Employee employee = new Employee.Builder()
                .employeeID("789")
                .name("T")
                .surname("G")
                .dateOfBirth("01/01/2001")
                .role("Employee")
                .build();

        employeeService.addEmployee(this.mContext, employee);
        Assert.assertNotNull(employee);

        Employee updateEmployee = new Employee.Builder()
                .copy(employee)
                .name("New")
                .build();

        employeeService.updateEmployee(this.mContext, updateEmployee);

        Assert.assertNotNull(updateEmployee);

        Thread.sleep(5000);
        Set<Employee> employeeSet = employeeRepository.findAll();
        Assert.assertTrue(TAG_Employee + " READ ALL", employeeSet.size() > 0);
    }
}
