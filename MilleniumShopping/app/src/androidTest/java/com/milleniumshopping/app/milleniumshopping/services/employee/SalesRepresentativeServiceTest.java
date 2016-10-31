package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.SalesRepresentative;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.SalesRepresentativeRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.employee.SalesRepresentativeRepository;
import com.milleniumshopping.app.milleniumshopping.services.employee.Impl.SalesRepresentativeServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class SalesRepresentativeServiceTest extends AndroidTestCase{
    private static final String TAG_Employee = "SalesRepresentative Test";

    public void testEmployeeIntentService() throws Exception {
        SalesRepresentativeRepository salesRepresentativeRepository = new SalesRepresentativeRepositoryImpl(this.getContext());
        SalesRepresentativeService salesRepresentativeService = SalesRepresentativeServiceImpl.getInstance();

        SalesRepresentative employee = new SalesRepresentative.Builder()
                .employeeID("789")
                .name("T")
                .surname("G")
                .dateOfBirth("01/01/2001")
                .role("Cleaner")
                .build();

        salesRepresentativeService.addEmployee(this.mContext, employee);
        Assert.assertNotNull(employee);

        SalesRepresentative updateEmployee = new SalesRepresentative.Builder()
                .copy(employee)
                .name("New")
                .build();

        salesRepresentativeService.updateEmployee(this.mContext, updateEmployee);

        Assert.assertNotNull(updateEmployee);

        Thread.sleep(5000);
        Set<SalesRepresentative> employeeSet = salesRepresentativeRepository.findAll();
        Assert.assertTrue(TAG_Employee + "READ ALL", employeeSet.size() > 0);
    }
}
