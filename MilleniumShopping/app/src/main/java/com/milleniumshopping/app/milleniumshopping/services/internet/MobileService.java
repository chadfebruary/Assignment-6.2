package com.milleniumshopping.app.milleniumshopping.services.internet;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Mobile;

/**
 * Created by cfebruary on 2016/10/31.
 */
public interface MobileService {
    void addInternet(Context context, Mobile internet);
    void updateInternet(Context context, Mobile internet);
}
