package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Cleaner;

/**
 * Created by cfebruary on 2016/10/31.
 */
public interface CleanerService {
    void addEmployee(Context context, Cleaner cleaner);
    void updateEmployee(Context context, Cleaner cleaner);
}
