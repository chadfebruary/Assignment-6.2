package com.milleniumshopping.app.milleniumshopping.services.shop.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.shop.Shop;
import com.milleniumshopping.app.milleniumshopping.repository.shop.Impl.ShopRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.shop.ShopRepository;
import com.milleniumshopping.app.milleniumshopping.services.shop.ShopService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ShopServiceImpl extends IntentService implements ShopService {
    private static final String ACTION_ADD = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniumshopping.app.milleniumshopping.services.shop.Impl.action.UPDATE";

    private static ShopServiceImpl service = null;

    private ShopServiceImpl()
    {
        super("ShopServiceImpl");
    }

    public static ShopServiceImpl getInstance()
    {
        if(service == null)
            service = new ShopServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Shop shop = (Shop) intent.getSerializableExtra(EXTRA_ADD);
                saveShop(shop);
            } else if (ACTION_UPDATE.equals(action)) {
                final Shop shop = (Shop) intent.getSerializableExtra(EXTRA_UPDATE);
                updateShop(shop);
            }
        }
    }

    private void updateShop(Shop shop)
    {
        //Post and Save local
        ShopRepository shopRepository = new ShopRepositoryImpl(getBaseContext());
        shopRepository.update(shop);
    }

    private void saveShop(Shop shop)
    {
        //Post and Save local
        ShopRepository shopRepository = new ShopRepositoryImpl(getBaseContext());
        shopRepository.save(shop);
    }

    public void deleteAll()
    {
        ShopRepository shopRepository = new ShopRepositoryImpl(getBaseContext());

        try
        {
            shopRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addShop(Context context, Shop shop)
    {
        Intent intent = new Intent(context, ShopServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) shop);
        context.startService(intent);
    }

    @Override
    public void updateShop(Context context, Shop shop)
    {
        Intent intent = new Intent(context, ShopServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) shop);
        context.startService(intent);
    }
}
