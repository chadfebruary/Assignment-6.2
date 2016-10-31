package com.milleniumshopping.app.milleniumshopping.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Cleaner;
import com.milleniumshopping.app.milleniumshopping.repository.employee.CleanerRepository;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.CleanerRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.services.employee.CleanerService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class CleanerServiceImpl extends IntentService implements CleanerService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.UPDATE";

    private static CleanerServiceImpl service = null;

    private CleanerServiceImpl()
    {
        super("CleanerServiceImpl");
    }

    public static CleanerServiceImpl getInstance()
    {
        if(service == null)
            service = new CleanerServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Cleaner employee = (Cleaner) intent.getSerializableExtra(EXTRA_ADD);
                saveEmployee(employee);
            } else if (ACTION_UPDATE.equals(action)) {
                final Cleaner employee = (Cleaner) intent.getSerializableExtra(EXTRA_UPDATE);
                updateEmployee(employee);
            }
        }
    }

    private void updateEmployee(Cleaner employee)
    {
        //Post and Save local
        CleanerRepository cleanerRepository = new CleanerRepositoryImpl(getBaseContext());
        cleanerRepository.update(employee);
    }

    private void saveEmployee(Cleaner employee)
    {
        //Post and Save local
        CleanerRepository cleanerRepository = new CleanerRepositoryImpl(getBaseContext());
        cleanerRepository.save(employee);
    }

    public void deleteAll()
    {
        CleanerRepository cleanerRepository = new CleanerRepositoryImpl(getBaseContext());

        try
        {
            cleanerRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployee(Context context, Cleaner employee)
    {
        Intent intent = new Intent(context, CleanerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) employee);
        context.startService(intent);
    }

    @Override
    public void updateEmployee(Context context, Cleaner employee)
    {
        Intent intent = new Intent(context, CleanerServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) employee);
        context.startService(intent);
    }
}
