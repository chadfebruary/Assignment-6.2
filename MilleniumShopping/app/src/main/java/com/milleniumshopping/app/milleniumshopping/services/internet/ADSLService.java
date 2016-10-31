package com.milleniumshopping.app.milleniumshopping.services.internet;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.internet.ADSL;

/**
 * Created by cfebruary on 2016/10/31.
 */
public interface ADSLService {
    void addInternet(Context context, ADSL internet);
    void updateInternet(Context context, ADSL internet);
}
