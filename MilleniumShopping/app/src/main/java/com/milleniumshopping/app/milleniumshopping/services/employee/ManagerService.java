package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Manager;

/**
 * Created by cfebruary on 2016/10/31.
 */
public interface ManagerService {
    void addEmployee(Context context, Manager employee);
    void updateEmployee(Context context, Manager employee);
}
