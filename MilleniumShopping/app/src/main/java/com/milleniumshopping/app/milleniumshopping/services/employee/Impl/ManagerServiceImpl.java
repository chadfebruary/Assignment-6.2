package com.milleniumshopping.app.milleniumshopping.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Manager;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.ManagerRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.employee.ManagerRepository;
import com.milleniumshopping.app.milleniumshopping.services.employee.ManagerService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ManagerServiceImpl extends IntentService implements ManagerService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.UPDATE";

    private static ManagerServiceImpl service = null;

    private ManagerServiceImpl()
    {
        super("ManagerServiceImpl");
    }

    public static ManagerServiceImpl getInstance()
    {
        if(service == null)
            service = new ManagerServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Manager employee = (Manager) intent.getSerializableExtra(EXTRA_ADD);
                saveEmployee(employee);
            } else if (ACTION_UPDATE.equals(action)) {
                final Manager employee = (Manager) intent.getSerializableExtra(EXTRA_UPDATE);
                updateEmployee(employee);
            }
        }
    }

    private void updateEmployee(Manager employee)
    {
        //Post and Save local
        ManagerRepository managerRepository = new ManagerRepositoryImpl(getBaseContext());
        managerRepository.update(employee);
    }

    private void saveEmployee(Manager employee)
    {
        //Post and Save local
        ManagerRepository managerRepository = new ManagerRepositoryImpl(getBaseContext());
        managerRepository.save(employee);
    }

    public void deleteAll()
    {
        ManagerRepository managerRepository = new ManagerRepositoryImpl(getBaseContext());

        try
        {
            managerRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployee(Context context, Manager employee)
    {
        Intent intent = new Intent(context, ManagerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) employee);
        context.startService(intent);
    }

    @Override
    public void updateEmployee(Context context, Manager employee)
    {
        Intent intent = new Intent(context, ManagerServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) employee);
        context.startService(intent);
    }
}
