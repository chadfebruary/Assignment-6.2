package com.milleniumshopping.app.milleniumshopping.services.internet;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Fibre;

/**
 * Created by cfebruary on 2016/10/31.
 */
public interface FibreService {
    void addInternet(Context context, Fibre internet);
    void updateInternet(Context context, Fibre internet);
}
