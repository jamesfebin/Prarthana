package com.bangalorediocese.mgocsm.lentapp.services;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

/**
 * Created by user on 04/03/15.
 */
public class PrayersDownloaderService extends DownloaderService {


    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvuVyBl4w+WTQbDvj+WxfpVy4oWqOfuleOGII5I9y0vh13adXW9Xk2FrL7O7IpBlOWiw4ooWf6I1LqnGmqOCrtVocajP6SW/t+4Ig6Cnulm2utihg92u7CrRS8qej35oNDwbb8rGioDmCL6tH8VbLAWr9qcLvbemd6RW5iKlTfTOtPXK3RUNb3ibCangVVaa3FRc2IbgddKMtE+w9Dzpma9bmZUBaeI5OMOM39PWX34vTizYCy09slqtuC4UKZiZhAwupZRmEKvtDLztBk8xido0yxDXlxaK6UwDtA5S59RVaEyR72eSE4qrLnI8d7b9H/YFAwmMfwsWMfnIz9uYAZwIDAQAB";
    // You should also modify this salt
    public static final byte[] SALT = new byte[] { 1, 42, -12, -1, 54, 98,
            -100, -12, 43, 2, -8, -4, 9, 5, -106, -107, -33, 45, -1, 84
    };


    @Override
    public String getPublicKey() {
        return BASE64_PUBLIC_KEY;
    }

    @Override
    public byte[] getSALT() {
        return SALT;
    }

    @Override
    public String getAlarmReceiverClassName() {
        return PrayersDownloaderService.class.getName();
    }
}
