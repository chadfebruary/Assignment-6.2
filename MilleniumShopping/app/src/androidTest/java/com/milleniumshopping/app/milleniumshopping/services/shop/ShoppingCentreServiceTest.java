package com.milleniumshopping.app.milleniumshopping.services.shop;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.shop.ShoppingCentre;
import com.milleniumshopping.app.milleniumshopping.repository.shop.Impl.ShoppingCentreRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.shop.ShoppingCentreRepository;
import com.milleniumshopping.app.milleniumshopping.services.shop.Impl.ShoppingCentreServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ShoppingCentreServiceTest extends AndroidTestCase{
    private static final String TAG_Shop = "Shop Test";

    public void testShoppingCentreIntentService() throws Exception
    {
        ShoppingCentreRepository shoppingCentreRepository = new ShoppingCentreRepositoryImpl(this.getContext());
        ShoppingCentreService shoppingCentreService = ShoppingCentreServiceImpl.getInstance();

        ShoppingCentre shoppingCentre = new ShoppingCentre.Builder()
                .centreName("Century City")
                .centreCode("654123")
                .portfolio("Vukile")
                .address("PO box 50")
                .build();

        shoppingCentreService.addShoppingCentre(this.mContext, shoppingCentre);
        Assert.assertNotNull(shoppingCentre);

        ShoppingCentre updateShoppingCentre= new ShoppingCentre.Builder()
                .copy(shoppingCentre)
                .centreName("New centre")
                .build();

        shoppingCentreService.updateShoppingCentre(this.mContext, updateShoppingCentre);

        Assert.assertNotNull(updateShoppingCentre);

        Thread.sleep(5000);
        Set<ShoppingCentre> shopSet = shoppingCentreRepository.findAll();
        Assert.assertTrue(TAG_Shop + "READ ALL", shopSet.size() > 0);
    }
}
