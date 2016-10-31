package com.milleniumshopping.app.milleniumshopping.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.employee.SalesRepresentative;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.SalesRepresentativeRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.employee.SalesRepresentativeRepository;
import com.milleniumshopping.app.milleniumshopping.services.employee.SalesRepresentativeService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class SalesRepresentativeServiceImpl extends IntentService implements SalesRepresentativeService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.employee.Impl.action.UPDATE";

    private static SalesRepresentativeServiceImpl service = null;

    private SalesRepresentativeServiceImpl()
    {
        super("SalesRepresentativeServiceImpl");
    }

    public static SalesRepresentativeServiceImpl getInstance()
    {
        if(service == null)
            service = new SalesRepresentativeServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final SalesRepresentative employee = (SalesRepresentative) intent.getSerializableExtra(EXTRA_ADD);
                saveEmployee(employee);
            } else if (ACTION_UPDATE.equals(action)) {
                final SalesRepresentative employee = (SalesRepresentative) intent.getSerializableExtra(EXTRA_UPDATE);
                updateEmployee(employee);
            }
        }
    }

    private void updateEmployee(SalesRepresentative employee)
    {
        //Post and Save local
        SalesRepresentativeRepository salesRepresentativeRepository = new SalesRepresentativeRepositoryImpl(getBaseContext());
        salesRepresentativeRepository.update(employee);
    }

    private void saveEmployee(SalesRepresentative employee)
    {
        //Post and Save local
        SalesRepresentativeRepository salesRepresentativeRepository = new SalesRepresentativeRepositoryImpl(getBaseContext());
        salesRepresentativeRepository.save(employee);
    }

    public void deleteAll()
    {
        SalesRepresentativeRepository salesRepresentativeRepository = new SalesRepresentativeRepositoryImpl(getBaseContext());

        try
        {
            salesRepresentativeRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployee(Context context, SalesRepresentative employee)
    {
        Intent intent = new Intent(context, CleanerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) employee);
        context.startService(intent);
    }

    @Override
    public void updateEmployee(Context context, SalesRepresentative employee)
    {
        Intent intent = new Intent(context, CleanerServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) employee);
        context.startService(intent);
    }
}
