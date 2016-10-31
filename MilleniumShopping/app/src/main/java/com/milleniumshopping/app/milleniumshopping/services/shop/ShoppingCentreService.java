package com.milleniumshopping.app.milleniumshopping.services.shop;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.shop.ShoppingCentre;

/**
 * Created by cfebruary on 2016/10/31.
 */
public interface ShoppingCentreService {
    void addShoppingCentre(Context context, ShoppingCentre shoppingCentre);
    void updateShoppingCentre(Context context, ShoppingCentre shoppingCentre);
}
