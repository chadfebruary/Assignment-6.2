package com.milleniumshopping.app.milleniumshopping.services.internet.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Internet;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.InternetRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.internet.InternetRepository;
import com.milleniumshopping.app.milleniumshopping.services.internet.InternetService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class InternetServiceImpl extends IntentService implements InternetService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static InternetServiceImpl service = null;

    private InternetServiceImpl()
    {
        super("InternetServiceImpl");
    }

    public static InternetServiceImpl getInstance()
    {
        if(service == null)
            service = new InternetServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Internet internet = (Internet) intent.getSerializableExtra(EXTRA_ADD);
                saveInternet(internet);
            } else if (ACTION_UPDATE.equals(action)) {
                final Internet internet = (Internet) intent.getSerializableExtra(EXTRA_UPDATE);
                updateInternet(internet);
            }
        }
    }

    private void updateInternet(Internet internet)
    {
        //Post and Save local
        InternetRepository internetRepository = new InternetRepositoryImpl(getBaseContext());
        internetRepository.update(internet);
    }

    private void saveInternet(Internet internet)
    {
        //Post and Save local
        InternetRepository internetRepository = new InternetRepositoryImpl(getBaseContext());
        internetRepository.save(internet);
    }

    public void deleteAll()
    {
        InternetRepository internetRepository = new InternetRepositoryImpl(getBaseContext());

        try
        {
            internetRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addInternet(Context context, Internet internet)
    {
        Intent intent = new Intent(context, InternetServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) internet);
        context.startService(intent);
    }

    @Override
    public void updateInternet(Context context, Internet internet)
    {
        Intent intent = new Intent(context, InternetServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) internet);
        context.startService(intent);
    }
}
