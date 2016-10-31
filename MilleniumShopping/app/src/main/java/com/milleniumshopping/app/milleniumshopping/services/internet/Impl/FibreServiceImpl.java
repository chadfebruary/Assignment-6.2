package com.milleniumshopping.app.milleniumshopping.services.internet.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Fibre;
import com.milleniumshopping.app.milleniumshopping.repository.internet.FibreRepository;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.FibreRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.services.internet.FibreService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class FibreServiceImpl extends IntentService implements FibreService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.internet.Impl.action.UPDATE";

    private static FibreServiceImpl service = null;

    private FibreServiceImpl()
    {
        super("ADSLServiceImpl");
    }

    public static FibreServiceImpl getInstance()
    {
        if(service == null)
            service = new FibreServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Fibre internet = (Fibre) intent.getSerializableExtra(EXTRA_ADD);
                saveInternet(internet);
            } else if (ACTION_UPDATE.equals(action)) {
                final Fibre internet = (Fibre) intent.getSerializableExtra(EXTRA_UPDATE);
                updateInternet(internet);
            }
        }
    }

    private void updateInternet(Fibre fibre)
    {
        //Post and Save local
        FibreRepository fibreRepository = new FibreRepositoryImpl(getBaseContext());
        fibreRepository.update(fibre);
    }

    private void saveInternet(Fibre fibre)
    {
        //Post and Save local
        FibreRepository fibreRepository = new FibreRepositoryImpl(getBaseContext());
        fibreRepository.save(fibre);
    }

    public void deleteAll()
    {
        FibreRepository fibreRepository = new FibreRepositoryImpl(getBaseContext());

        try
        {
            fibreRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addInternet(Context context, Fibre fibre)
    {
        Intent intent = new Intent(context, FibreServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) fibre);
        context.startService(intent);
    }

    @Override
    public void updateInternet(Context context, Fibre fibre)
    {
        Intent intent = new Intent(context, FibreServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) fibre);
        context.startService(intent);
    }
}
