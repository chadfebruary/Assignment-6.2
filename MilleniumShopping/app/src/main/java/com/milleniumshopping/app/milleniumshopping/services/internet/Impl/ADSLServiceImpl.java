package com.milleniumshopping.app.milleniumshopping.services.internet.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.internet.ADSL;
import com.milleniumshopping.app.milleniumshopping.repository.internet.ADSLRepository;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.ADSLRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.services.internet.ADSLService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ADSLServiceImpl extends IntentService implements ADSLService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static ADSLServiceImpl service = null;

    private ADSLServiceImpl()
    {
        super("ADSLServiceImpl");
    }

    public static ADSLServiceImpl getInstance()
    {
        if(service == null)
            service = new ADSLServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final ADSL internet = (ADSL) intent.getSerializableExtra(EXTRA_ADD);
                saveInternet(internet);
            } else if (ACTION_UPDATE.equals(action)) {
                final ADSL internet = (ADSL) intent.getSerializableExtra(EXTRA_UPDATE);
                updateInternet(internet);
            }
        }
    }

    private void updateInternet(ADSL adsl)
    {
        //Post and Save local
        ADSLRepository adslRepository = new ADSLRepositoryImpl(getBaseContext());
        adslRepository.update(adsl);
    }

    private void saveInternet(ADSL adsl)
    {
        //Post and Save local
        ADSLRepository adslRepository = new ADSLRepositoryImpl(getBaseContext());
        adslRepository.save(adsl);
    }

    public void deleteAll()
    {
        ADSLRepository adslRepository = new ADSLRepositoryImpl(getBaseContext());

        try
        {
            adslRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addInternet(Context context, ADSL adsl)
    {
        Intent intent = new Intent(context, ADSLServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) adsl);
        context.startService(intent);
    }

    @Override
    public void updateInternet(Context context, ADSL adsl)
    {
        Intent intent = new Intent(context, ADSLServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) adsl);
        context.startService(intent);
    }
}
