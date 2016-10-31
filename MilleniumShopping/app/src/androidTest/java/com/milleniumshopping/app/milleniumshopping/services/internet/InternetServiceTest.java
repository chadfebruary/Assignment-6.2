package com.milleniumshopping.app.milleniumshopping.services.internet;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.internet.Internet;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.InternetRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.internet.InternetRepository;
import com.milleniumshopping.app.milleniumshopping.services.internet.Impl.InternetServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Chad on 5/8/2016.
 */
public class InternetServiceTest extends AndroidTestCase{
    private static final String TAG_Internet = "Internet Test";

    public void testInternetIntentService() throws Exception
    {
        InternetRepository internetRepository = new InternetRepositoryImpl(this.getContext());
        InternetService internetService = InternetServiceImpl.getInstance();

        Internet internet = new Internet.Builder()
                .ipAddress("789")
                .ISP("T")
                .planName("G")
                .price("R300")
                .type("Generic")
                .dataAllowance("3GB")
                .build();

        internetService.addInternet(this.mContext, internet);
        Assert.assertNotNull(internet);

        Internet updateInternet= new Internet.Builder()
                .copy(internet)
                .ISP("New")
                .build();

        internetService.updateInternet(this.mContext, updateInternet);

        Assert.assertNotNull(updateInternet);

        Thread.sleep(5000);
        Set<Internet> internetSet = internetRepository.findAll();
        Assert.assertTrue(TAG_Internet + "READ ALL", internetSet.size() > 0);
    }
}
