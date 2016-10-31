package com.milleniumshopping.app.milleniumshopping.services.shop.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.shop.ShoppingCentre;
import com.milleniumshopping.app.milleniumshopping.repository.shop.Impl.ShoppingCentreRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.shop.ShoppingCentreRepository;
import com.milleniumshopping.app.milleniumshopping.services.shop.ShoppingCentreService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ShoppingCentreServiceImpl extends IntentService implements ShoppingCentreService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.UPDATE";

    private static ShoppingCentreServiceImpl service = null;

    private ShoppingCentreServiceImpl()
    {
        super("ShoppingCentreServiceImpl");
    }

    public static ShoppingCentreServiceImpl getInstance()
    {
        if(service == null)
            service = new ShoppingCentreServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final ShoppingCentre shoppingCentre = (ShoppingCentre) intent.getSerializableExtra(EXTRA_ADD);
                saveShoppingCentre(shoppingCentre);
            } else if (ACTION_UPDATE.equals(action)) {
                final ShoppingCentre shoppingCentre = (ShoppingCentre) intent.getSerializableExtra(EXTRA_UPDATE);
                updateShoppingCentre(shoppingCentre);
            }
        }
    }

    private void updateShoppingCentre(ShoppingCentre shoppingCentre)
    {
        //Post and Save local
        ShoppingCentreRepository shoppingCentreRepository = new ShoppingCentreRepositoryImpl(getBaseContext());
        shoppingCentreRepository.update(shoppingCentre);
    }

    private void saveShoppingCentre(ShoppingCentre shoppingCentre)
    {
        //Post and Save local
        ShoppingCentreRepository shoppingCentreRepository = new ShoppingCentreRepositoryImpl(getBaseContext());
        shoppingCentreRepository.save(shoppingCentre);
    }

    public void deleteAll()
    {
        ShoppingCentreRepository shoppingCentreRepository = new ShoppingCentreRepositoryImpl(getBaseContext());

        try
        {
            shoppingCentreRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addShoppingCentre(Context context, ShoppingCentre shoppingCentre)
    {
        Intent intent = new Intent(context, ShoppingCentreServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) shoppingCentre);
        context.startService(intent);
    }

    @Override
    public void updateShoppingCentre(Context context, ShoppingCentre shoppingCentre)
    {
        Intent intent = new Intent(context, ShoppingCentreServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) shoppingCentre);
        context.startService(intent);
    }
}
