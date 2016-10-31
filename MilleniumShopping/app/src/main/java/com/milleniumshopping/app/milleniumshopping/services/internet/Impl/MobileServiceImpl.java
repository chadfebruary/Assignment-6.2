package com.milleniumshopping.app.milleniumshopping.services.internet.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Mobile;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.MobileRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.internet.MobileRepository;
import com.milleniumshopping.app.milleniumshopping.services.internet.MobileService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class MobileServiceImpl extends IntentService implements MobileService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static MobileServiceImpl service = null;

    private MobileServiceImpl()
    {
        super("MobileServiceImpl");
    }

    public static MobileServiceImpl getInstance()
    {
        if(service == null)
            service = new MobileServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Mobile internet = (Mobile) intent.getSerializableExtra(EXTRA_ADD);
                saveInternet(internet);
            } else if (ACTION_UPDATE.equals(action)) {
                final Mobile internet = (Mobile) intent.getSerializableExtra(EXTRA_UPDATE);
                updateInternet(internet);
            }
        }
    }

    private void updateInternet(Mobile mobile)
    {
        //Post and Save local
        MobileRepository mobileRepository = new MobileRepositoryImpl(getBaseContext());
        mobileRepository.update(mobile);
    }

    private void saveInternet(Mobile mobile)
    {
        //Post and Save local
        MobileRepository mobileRepository = new MobileRepositoryImpl(getBaseContext());
        mobileRepository.save(mobile);
    }

    public void deleteAll()
    {
        MobileRepository mobileRepository = new MobileRepositoryImpl(getBaseContext());

        try
        {
            mobileRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addInternet(Context context, Mobile mobile)
    {
        Intent intent = new Intent(context, MobileServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) mobile);
        context.startService(intent);
    }

    @Override
    public void updateInternet(Context context, Mobile mobile)
    {
        Intent intent = new Intent(context, MobileServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) mobile);
        context.startService(intent);
    }
}
