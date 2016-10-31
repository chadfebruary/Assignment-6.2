package com.milleniumshopping.app.milleniumshopping.services.shop;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.shop.Shop;
import com.milleniumshopping.app.milleniumshopping.repository.shop.Impl.ShopRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.shop.ShopRepository;
import com.milleniumshopping.app.milleniumshopping.services.shop.Impl.ShopServiceImpl;

import junit.framework.Assert;

import java.util.Set;


/**
 * Created by Chad on 5/8/2016.
 */
public class ShopServiceTest extends AndroidTestCase {
    private static final String TAG_Shop = "Shop Test";

    public void testShopIntentService() throws Exception
    {
        ShopRepository shopRepository = new ShopRepositoryImpl(this.getContext());
        ShopService shopService = ShopServiceImpl.getInstance();

        Shop shop = new Shop.Builder()
                .shopNumber("789")
                .shopName("T")
                .shopOwner("G")
                .shopPhoneNumber("R300")
                .build();

        shopService.addShop(this.mContext, shop);
        Assert.assertNotNull(shop);

        Shop updateShop= new Shop.Builder()
                .copy(shop)
                .shopName("New")
                .build();

        shopService.updateShop(this.mContext, updateShop);

        Assert.assertNotNull(updateShop);

        Thread.sleep(5000);
        Set<Shop> shopSet = shopRepository.findAll();
        Assert.assertTrue(TAG_Shop + "READ ALL", shopSet.size() > 0);
    }
}
