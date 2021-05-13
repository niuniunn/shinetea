package com.niuniu.shinetea.utils;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class KeyUtil {

    public static String getCouponKey() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

    public static String getCode() {
        return RandomStringUtils.randomNumeric(4);
    }

    public static synchronized String getUniqueKey() {
        Random random = new Random();
        //生成六位
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
