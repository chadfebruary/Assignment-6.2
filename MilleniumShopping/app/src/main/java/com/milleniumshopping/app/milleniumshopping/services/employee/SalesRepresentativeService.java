package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.employee.SalesRepresentative;

/**
 * Created by cfebruary on 2016/10/31.
 */
public interface SalesRepresentativeService {
    void addEmployee(Context context, SalesRepresentative employee);
    void updateEmployee(Context context, SalesRepresentative employee);
}
