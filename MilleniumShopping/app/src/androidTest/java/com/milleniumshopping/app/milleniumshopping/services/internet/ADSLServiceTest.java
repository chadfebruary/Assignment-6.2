package com.milleniumshopping.app.milleniumshopping.services.internet;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.internet.ADSL;
import com.milleniumshopping.app.milleniumshopping.repository.internet.ADSLRepository;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.ADSLRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.services.internet.Impl.ADSLServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ADSLServiceTest extends AndroidTestCase{
    private static final String TAG_Internet = "Internet Test";

    public void testADSLIntentService() throws Exception
    {
        ADSLRepository adslRepository = new ADSLRepositoryImpl(this.getContext());
        ADSLService adslService = ADSLServiceImpl.getInstance();

        ADSL adsl = new ADSL.Builder()
                .ipAddress("789")
                .ISP("T")
                .planName("G")
                .price("R300")
                .type("Generic")
                .dataAllowance("3GB")
                .build();

        adslService.addInternet(this.mContext, adsl);
        Assert.assertNotNull(adsl);

        ADSL updateInternet= new ADSL.Builder()
                .copy(adsl)
                .ISP("New")
                .build();

        adslService.updateInternet(this.mContext, updateInternet);

        Assert.assertNotNull(updateInternet);

        Thread.sleep(5000);
        Set<ADSL> internetSet = adslRepository.findAll();
        Assert.assertTrue(TAG_Internet + "READ ALL", internetSet.size() > 0);
    }
}
