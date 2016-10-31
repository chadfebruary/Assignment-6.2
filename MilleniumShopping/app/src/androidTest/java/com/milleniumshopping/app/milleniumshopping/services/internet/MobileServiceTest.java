package com.milleniumshopping.app.milleniumshopping.services.internet;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Mobile;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.MobileRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.internet.MobileRepository;
import com.milleniumshopping.app.milleniumshopping.services.internet.Impl.MobileServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class MobileServiceTest extends AndroidTestCase{
    private static final String TAG_Internet = "Mobile Test";

    public void testMobileIntentService() throws Exception
    {
        MobileRepository internetRepository = new MobileRepositoryImpl(this.getContext());
        MobileService internetService = MobileServiceImpl.getInstance();

        Mobile internet = new Mobile.Builder()
                .ipAddress("789")
                .ISP("T")
                .planName("G")
                .price("R300")
                .type("Mobile")
                .dataAllowance("3GB")
                .build();

        internetService.addInternet(this.mContext, internet);
        Assert.assertNotNull(internet);

        Mobile updateInternet= new Mobile.Builder()
                .copy(internet)
                .ISP("New")
                .build();

        internetService.updateInternet(this.mContext, updateInternet);

        Assert.assertNotNull(updateInternet);

        Thread.sleep(5000);
        Set<Mobile> internetSet = internetRepository.findAll();
        Assert.assertTrue(TAG_Internet + "READ ALL", internetSet.size() > 0);
    }
}
