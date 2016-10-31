package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Manager;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.ManagerRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.employee.ManagerRepository;
import com.milleniumshopping.app.milleniumshopping.services.employee.Impl.ManagerServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ManagerServiceTest extends AndroidTestCase{
    private static final String TAG_Employee = "Manager Test";

    public void testEmployeeIntentService() throws Exception {
        ManagerRepository managerRepository = new ManagerRepositoryImpl(this.getContext());
        ManagerService managerService = ManagerServiceImpl.getInstance();

        Manager employee = new Manager.Builder()
                .employeeID("789")
                .name("T")
                .surname("G")
                .dateOfBirth("01/01/2001")
                .role("Cleaner")
                .build();

        managerService.addEmployee(this.mContext, employee);
        Assert.assertNotNull(employee);

        Manager updateEmployee = new Manager.Builder()
                .copy(employee)
                .name("New")
                .build();

        managerService.updateEmployee(this.mContext, updateEmployee);

        Assert.assertNotNull(updateEmployee);

        Thread.sleep(5000);
        Set<Manager> employeeSet = managerRepository.findAll();
        Assert.assertTrue(TAG_Employee + "READ ALL", employeeSet.size() > 0);
    }
}
