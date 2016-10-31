package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Cleaner;
import com.milleniumshopping.app.milleniumshopping.repository.employee.CleanerRepository;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.CleanerRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.services.employee.Impl.CleanerServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class CleanerServiceTest extends AndroidTestCase{
    private static final String TAG_Employee = "Cleaner Test";

    public void testEmployeeIntentService() throws Exception
    {
        CleanerRepository cleanerRepository = new CleanerRepositoryImpl(this.getContext());
        CleanerService cleanerService = CleanerServiceImpl.getInstance();

        Cleaner employee = new Cleaner.Builder()
                .employeeID("789")
                .name("T")
                .surname("G")
                .dateOfBirth("01/01/2001")
                .role("Cleaner")
                .build();

        cleanerService.addEmployee(this.mContext, employee);
        Assert.assertNotNull(employee);

        Cleaner updateEmployee = new Cleaner.Builder()
                .copy(employee)
                .name("New")
                .build();

        cleanerService.updateEmployee(this.mContext, updateEmployee);

        Assert.assertNotNull(updateEmployee);

        Thread.sleep(5000);
        Set<Cleaner> employeeSet = cleanerRepository.findAll();
        Assert.assertTrue(TAG_Employee + "READ ALL", employeeSet.size() > 0);
    }
}
